
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

package com.github.bonbast.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.github.bonbast.R;
import com.github.bonbast.activity.EditFavoriteListActivity;
import com.github.bonbast.activity.MainTabActivity;
import com.github.bonbast.adapter.PriceAdapter;
import com.github.bonbast.db.DatabaseManager;
import com.github.bonbast.model.FavoriteModel;
import com.github.bonbast.model.PriceModel;
import com.github.bonbast.utils.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FavoriteListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

  private static FavoriteListFragment instance = null;

  ArrayList<PriceModel> list;
  PriceAdapter adapter;
  RecyclerView recycler_view;
  SwipeRefreshLayout swipeRefreshLayout;

  View status_layout;
  LottieAnimationView status_animation;
  TextView status_text;

  public static FavoriteListFragment getInstance() {
    return instance;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    instance = this;
    list = new ArrayList<>();
    list.clear();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_overview, container, false);
    recycler_view = root.findViewById(R.id.price_rcv);
    recycler_view.setHasFixedSize(true);

    swipeRefreshLayout = root.findViewById(R.id.main_page_refresh);
    swipeRefreshLayout.setOnRefreshListener(this);
    swipeRefreshLayout.setColorSchemeResources(
            R.color.purple,
            R.color.blue,
            R.color.light_green);

    status_layout = root.findViewById(R.id.status_layout);
    status_animation = root.findViewById(R.id.status_animation);
    status_text = root.findViewById(R.id.status_text);

    adapter = new PriceAdapter(list, getContext());
    loadList();

    return root;
  }

  @Override
  public void onRefresh() {
    MainTabActivity activity = (MainTabActivity) getActivity();
    activity.checkConnection();
    swipeRefreshLayout.setRefreshing(false);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.favorite_menu, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.menu_edit_favorite) {
      Intent i = new Intent(getContext(), EditFavoriteListActivity.class);
      startActivity(i);
    }
    return false;
  }

  public void loadList() {
    try {
      list.clear();
      JSONObject response = new JSONObject(DatabaseManager.getInstance().getRawData());
      ArrayList<PriceModel> allItems = JSONParser.priceList(response, "", getContext());
      ArrayList<FavoriteModel> favoriteItems = DatabaseManager.getInstance().getFavoriteList();
      if (favoriteItems != null)
        for (FavoriteModel fItems: favoriteItems) {
          for (PriceModel item: allItems) {
            if (item.getObjName().equals(fItems.getObjName())) {
              list.add(item);
              break;
            }
          }
        }

      recycler_view.setAdapter(adapter);

      if (list.isEmpty()) {
        recycler_view.setVisibility(View.GONE);
        status_layout.setVisibility(View.VISIBLE);
        status_animation.setAnimation("empty_box.json");
        status_animation.playAnimation();
        status_text.setText(getResources().getString(R.string.empty_list));
      } else {
        recycler_view.setVisibility(View.VISIBLE);
        status_layout.setVisibility(View.GONE);
        status_animation.pauseAnimation();
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}