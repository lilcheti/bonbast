
/*
 *     This file is part of Sarrafi.
 *
 *     Sarrafi is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Sarrafi is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Sarrafi.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.bonbast.activity;

import static java.sql.DriverManager.println;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.preference.PreferenceManager;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.github.bonbast.BuildConfig;
import com.github.bonbast.R;
import com.github.bonbast.db.DatabaseManager;
import com.github.bonbast.fragment.CurrencyListFragment;
import com.github.bonbast.fragment.DigitalCurrencyListFragment;
import com.github.bonbast.fragment.FavoriteListFragment;
import com.github.bonbast.fragment.GoldListFragment;
import com.github.bonbast.fragment.OilListFragment;
import com.github.bonbast.fragment.PricePagerAdapter;
import com.github.bonbast.utils.ActivityHelper;
import com.github.bonbast.utils.animatedTabLayout.AnimatedTabLayout;
import com.shreyaspatil.material.navigationview.MaterialNavigationView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.Objects;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainTabActivity extends AppCompatActivity {

  MaterialNavigationView navigationView;
  DrawerLayout drawerLayout;
  ActionBarDrawerToggle toggle;
  View status_layout;
  LottieAnimationView status_animation;
  TextView status_text;
  Button status_button;
  Toolbar toolbar;
  ViewPager viewPager;
  AnimatedTabLayout tabs;

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }

  static {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
  }

  String mainUrl = "https://call3.tgju.org/ajax.json";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    ViewPump.init(ViewPump.builder().addInterceptor(new CalligraphyInterceptor(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Shabnam-FD.ttf").setFontAttrId(R.attr.fontPath).build())).build());
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_navigation);
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    preferences.edit().putBoolean("toman",true);

    drawerLayout = findViewById(R.id.drawer_layout);
    toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
    navigationView = findViewById(R.id.nav_view);
    viewPager = findViewById(R.id.view_pager);
    tabs = findViewById(R.id.tabs);

    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    status_layout = findViewById(R.id.status_layout);
    status_animation = findViewById(R.id.status_animation);
    status_text = findViewById(R.id.status_text);
    status_button = findViewById(R.id.status_button);

    navigationView.getMenu().findItem(R.id.nav_rate).setVisible(!BuildConfig.showUpdater);
    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();
    Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(null);
    checkConnection();

    status_button.setOnClickListener(view -> {
      status_button.setVisibility(View.GONE);
      status_animation.setAnimation("loading_animation.json");
      status_animation.playAnimation();
      status_text.setText("");
      checkConnection();
    });

    navigationView.setNavigationItemSelectedListener(item -> {
      if (drawerLayout != null) {
        drawerLayout.closeDrawers();
      }

      switch(item.getItemId()) {
        case R.id.nav_calculator:
          Intent intent = new Intent(getApplicationContext(), CalculatorActivity.class);
          startActivity(intent);
          return false;
        case R.id.nav_settings:
          intent = new Intent(getApplicationContext(), SettingsActivity.class);
          startActivity(intent);
          return false;
        case R.id.nav_about:
          intent = new Intent(getApplicationContext(), AboutActivity.class);
          startActivity(intent);
          return false;
        case R.id.nav_rate:
          ActivityHelper.rateUS(this, getBaseContext());
          return false;
        default:
          return false;
      }

    });

    ActivityHelper.checkUpdate(this, getBaseContext());
  }

  public void checkConnection() {
    if (ActivityHelper.checkConnection(getBaseContext())) {
      getData();
    } else {
      showProblem(getResources().getString(R.string.no_network));
    }
  }

  public void getData() {
    AndroidNetworking
            .get("https://raw.githubusercontent.com/tokhmiX/bonbast/master/price.json")
            .setPriority(Priority.HIGH)
            .doNotCacheResponse()
            .build()
            .getAsJSONObject(new JSONObjectRequestListener() {
              @Override
              public void onResponse(JSONObject response) {
                DatabaseManager.getInstance().setBonbastData(response.toString());
                AndroidNetworking
                        .get(mainUrl)
                        .setPriority(Priority.HIGH)
                        .doNotCacheResponse()
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                          @Override
                          public void onResponse(JSONObject response) {
                            status_layout.setVisibility(View.GONE);

                            DatabaseManager.getInstance().setRawData(response.toString());

                            PricePagerAdapter pricePagerAdapter = new PricePagerAdapter(getBaseContext(), getSupportFragmentManager());
                            pricePagerAdapter.addFragment(new FavoriteListFragment());
                            pricePagerAdapter.addFragment(new CurrencyListFragment());
                            pricePagerAdapter.addFragment(new GoldListFragment());
                            pricePagerAdapter.addFragment(new OilListFragment());
                            pricePagerAdapter.addFragment(new DigitalCurrencyListFragment());
                            viewPager.setAdapter(pricePagerAdapter);
                            tabs.setupViewPager(viewPager);
                            if (!DatabaseManager.getInstance().isFavoriteListAvailable())
                              viewPager.setCurrentItem(1);
                            tabs.setVisibility(View.VISIBLE);
                            viewPager.setVisibility(View.VISIBLE);

                            if (response.toString().equals("")) {
                              status_layout.setVisibility(View.VISIBLE);
                              status_animation.setAnimation("empty_box.json");
                              status_animation.playAnimation();
                              status_text.setText(getResources().getString(R.string.empty_list));
                            }
                          }
                          @Override
                          public void onError(ANError error) {
                            Log.e("🔴ERROR3" , String.valueOf(error));
                            showProblem(getResources().getString(R.string.error_loading));
                          }
                        });
              }

              @Override
              public void onError(ANError error) {
                Log.e("🔴ERROR3" , String.valueOf(error));
                showProblem(getResources().getString(R.string.error_loading));
              }
            });

  }

  public void showProblem(String error) {
    viewPager.setVisibility(View.GONE);
    status_layout.setVisibility(View.VISIBLE);
    status_animation.setAnimation("no_internet_connection.json");
    status_animation.playAnimation();
    status_text.setText(error);
    status_button.setVisibility(View.VISIBLE);
  }

  @Override
  public boolean onOptionsItemSelected(@NotNull MenuItem item) {
    if(toggle.onOptionsItemSelected(item))
      return true;

    return super.onOptionsItemSelected(item);
  }

}