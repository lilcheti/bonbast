
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

package com.github.bonbast.utils;

import static java.sql.DriverManager.println;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.github.bonbast.R;
import com.github.bonbast.db.DatabaseManager;
import com.github.bonbast.model.PriceModel;
import com.github.bonbast.model.PriceTableModel;
import com.github.bonbast.model.UnitItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class JSONParser {
  public static ArrayList<PriceModel> priceList(JSONObject response,JSONObject bonbast, String checkedFilter, Context context) throws JSONException {
    ArrayList<PriceModel> list = new ArrayList<>();

    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
    boolean tomanConvert = true;
    String iran_currency = tomanConvert ? "تومان" : "ریال";
    JSONObject jsonData = response.optJSONObject("current");
    if (jsonData != null) {
      if(bonbast!=null) {
        try {
          jsonData.getJSONObject("price_dollar_soleymani").put("p", bonbast.getJSONObject("US Dollar").getString("sell") + "0");
          jsonData.getJSONObject("price_eur").put("p", bonbast.getJSONObject("Euro").getString("sell") + "0");
          jsonData.getJSONObject("price_gbp").put("p", bonbast.getJSONObject("British Pound").getString("sell") + "0");
          jsonData.getJSONObject("price_chf").put("p", bonbast.getJSONObject("Swiss Franc").getString("sell") + "0");
          jsonData.getJSONObject("price_cad").put("p", bonbast.getJSONObject("Canadian Dollar").getString("sell") + "0");
          jsonData.getJSONObject("price_aud").put("p", bonbast.getJSONObject("Australian Dollar").getString("sell") + "0");
          jsonData.getJSONObject("price_sek").put("p", bonbast.getJSONObject("Swedish Krona").getString("sell") + "0");
          jsonData.getJSONObject("price_rub").put("p", bonbast.getJSONObject("Russian Ruble").getString("sell") + "0");
          jsonData.getJSONObject("price_sgd").put("p", bonbast.getJSONObject("Singapore Dollar").getString("sell") + "0");
          jsonData.getJSONObject("price_aed").put("p", bonbast.getJSONObject("UAE Dirham").getString("sell") + "0");
          jsonData.getJSONObject("price_jpy").put("p", bonbast.getJSONObject("Japanese Yen").getString("sell") + "0");
          jsonData.getJSONObject("price_try").put("p", bonbast.getJSONObject("Turkish Lira").getString("sell") + "0");
          jsonData.getJSONObject("price_cny").put("p", bonbast.getJSONObject("Chinese Yuan").getString("sell") + "0");
          jsonData.getJSONObject("price_inr").put("p", bonbast.getJSONObject("Indian Rupee").getString("sell") + "0");
          jsonData.getJSONObject("price_myr").put("p", bonbast.getJSONObject("Ringgit").getString("sell") + "0");
          jsonData.getJSONObject("price_afn").put("p", bonbast.getJSONObject("Afghan Afghani").getString("sell") + "0");
          jsonData.getJSONObject("price_iqd").put("p", bonbast.getJSONObject("Iraqi Dinar").getString("sell") + "0");

          jsonData.getJSONObject("price_dollar_soleymani").put("l", bonbast.getJSONObject("US Dollar").getString("buy") + "0");
          jsonData.getJSONObject("price_eur").put("l", bonbast.getJSONObject("Euro").getString("buy") + "0");
          jsonData.getJSONObject("price_gbp").put("l", bonbast.getJSONObject("British Pound").getString("buy") + "0");
          jsonData.getJSONObject("price_chf").put("l", bonbast.getJSONObject("Swiss Franc").getString("buy") + "0");
          jsonData.getJSONObject("price_cad").put("l", bonbast.getJSONObject("Canadian Dollar").getString("buy") + "0");
          jsonData.getJSONObject("price_aud").put("l", bonbast.getJSONObject("Australian Dollar").getString("buy") + "0");
          jsonData.getJSONObject("price_sek").put("l", bonbast.getJSONObject("Swedish Krona").getString("buy") + "0");
          jsonData.getJSONObject("price_rub").put("l", bonbast.getJSONObject("Russian Ruble").getString("buy") + "0");
          jsonData.getJSONObject("price_sgd").put("l", bonbast.getJSONObject("Singapore Dollar").getString("buy") + "0");
          jsonData.getJSONObject("price_aed").put("l", bonbast.getJSONObject("UAE Dirham").getString("buy") + "0");
          jsonData.getJSONObject("price_jpy").put("l", bonbast.getJSONObject("Japanese Yen").getString("buy") + "0");
          jsonData.getJSONObject("price_try").put("l", bonbast.getJSONObject("Turkish Lira").getString("buy") + "0");
          jsonData.getJSONObject("price_cny").put("l", bonbast.getJSONObject("Chinese Yuan").getString("buy") + "0");
          jsonData.getJSONObject("price_inr").put("l", bonbast.getJSONObject("Indian Rupee").getString("buy") + "0");
          jsonData.getJSONObject("price_myr").put("l", bonbast.getJSONObject("Ringgit").getString("buy") + "0");
          jsonData.getJSONObject("price_afn").put("l", bonbast.getJSONObject("Afghan Afghani").getString("buy") + "0");
          jsonData.getJSONObject("price_iqd").put("l", bonbast.getJSONObject("Iraqi Dinar").getString("buy") + "0");

        jsonData.getJSONObject("sekeb").put("p",bonbast.getJSONObject("Azadi").getString("sell")+"0");
        jsonData.getJSONObject("sekee").put("p",bonbast.getJSONObject("Emami").getString("sell")+"0");
        jsonData.getJSONObject("nim").put("p",bonbast.getJSONObject("\u00bd Azadi").getString("sell")+"0");
        jsonData.getJSONObject("rob").put("p",bonbast.getJSONObject("\u00bc Azadi").getString("sell")+"0");
        jsonData.getJSONObject("gerami").put("p",bonbast.getJSONObject("Gerami").getString("sell")+"0");
        jsonData.getJSONObject("mesghal").put("p",bonbast.getJSONObject("Gold Mithqal").getString("sell")+"0");
        jsonData.getJSONObject("ons").put("p",bonbast.getJSONObject("Gold Ounce").getString("sell"));


        jsonData.getJSONObject("crypto-bitcoin").put("p",bonbast.getJSONObject("Bitcoin").getString("sell"));
        }catch (Exception e){
          Log.e("fuck",e.toString());
        }
      }
      JSONObject price_dollar_rl = jsonData.getJSONObject("price_dollar_rl");
      JSONObject price_eur              = jsonData.getJSONObject("price_eur");
      JSONObject price_dollar_soleymani = jsonData.getJSONObject("price_dollar_soleymani");
      JSONObject price_cad              = jsonData.getJSONObject("price_cad");
      JSONObject price_aud              = jsonData.getJSONObject("price_aud");
      JSONObject price_nzd              = jsonData.getJSONObject("price_nzd");
      JSONObject price_sgd              = jsonData.getJSONObject("price_sgd");
      JSONObject price_gbp              = jsonData.getJSONObject("price_gbp");
      JSONObject price_aed              = jsonData.getJSONObject("price_aed");
      JSONObject price_try              = jsonData.getJSONObject("price_try");
      JSONObject price_chf              = jsonData.getJSONObject("price_chf");
      JSONObject price_cny              = jsonData.getJSONObject("price_cny");
      JSONObject price_jpy              = jsonData.getJSONObject("price_jpy");
      JSONObject price_afn              = jsonData.getJSONObject("price_afn");
      JSONObject price_inr              = jsonData.getJSONObject("price_inr");
      JSONObject price_iqd              = jsonData.getJSONObject("price_iqd");
      JSONObject price_sek              = jsonData.getJSONObject("price_sek");
      JSONObject price_myr              = jsonData.getJSONObject("price_myr");
      JSONObject price_rub              = jsonData.getJSONObject("price_rub");

      JSONObject sekee            = jsonData.getJSONObject("sekee");
      JSONObject sekeb            = jsonData.getJSONObject("sekeb");
      JSONObject nim              = jsonData.getJSONObject("nim");
      JSONObject rob              = jsonData.getJSONObject("rob");
      JSONObject geram24          = jsonData.getJSONObject("geram24");
      JSONObject geram18          = jsonData.getJSONObject("geram18");
      JSONObject mesghal          = jsonData.getJSONObject("mesghal");
      JSONObject gerami           = jsonData.getJSONObject("gerami");
      JSONObject ons              = jsonData.getJSONObject("ons");
      JSONObject silver           = jsonData.getJSONObject("silver");
      JSONObject gold_mini_size   = jsonData.getJSONObject("gold_mini_size");

      JSONObject oil        = jsonData.getJSONObject("oil");
      JSONObject oil_brent  = jsonData.getJSONObject("oil_brent");
      JSONObject oil_opec   = jsonData.getJSONObject("oil_opec");
      JSONObject general_9  = jsonData.getJSONObject("general_9");
      JSONObject general_10  = jsonData.getJSONObject("general_10");
      JSONObject general_11  = jsonData.getJSONObject("general_11");

      JSONObject crypto_bitcoin = jsonData.getJSONObject("crypto-bitcoin");
      JSONObject crypto_ethereum = jsonData.getJSONObject("crypto-ethereum");
      JSONObject crypto_ripple = jsonData.getJSONObject("crypto-ripple");
      JSONObject crypto_dash = jsonData.getJSONObject("crypto-dash");
      JSONObject crypto_litecoin = jsonData.getJSONObject("crypto-litecoin");
      JSONObject crypto_stellar = jsonData.getJSONObject("crypto-stellar");

      if (checkedFilter.equals("currency") || checkedFilter.equals("")) {
        list.add(addObject("price_dollar_rl",context.getResources().getString(R.string.dollar), price_dollar_rl, iran_currency, tomanConvert ));
        list.add(addObject("price_dollar_soleymani",context.getResources().getString(R.string.dollar_bonbast), price_dollar_soleymani, iran_currency, tomanConvert ));
        list.add(addObject("price_eur",context.getResources().getString(R.string.euro), price_eur, iran_currency, tomanConvert ));
        list.add(addObject("price_cad",context.getResources().getString(R.string.dollar_canada), price_cad, iran_currency, tomanConvert ));
        list.add(addObject("price_aud",context.getResources().getString(R.string.dollar_australia), price_aud, iran_currency, tomanConvert ));
        list.add(addObject("price_nzd",context.getResources().getString(R.string.dollar_new_zealand), price_nzd, iran_currency, tomanConvert ));
        list.add(addObject("price_sgd",context.getResources().getString(R.string.dollar_singapore), price_sgd, iran_currency, tomanConvert ));
        list.add(addObject("price_gbp",context.getResources().getString(R.string.pound), price_gbp, iran_currency, tomanConvert ));
        list.add(addObject("price_aed",context.getResources().getString(R.string.dirham), price_aed, iran_currency, tomanConvert ));
        list.add(addObject("price_try",context.getResources().getString(R.string.lira), price_try, iran_currency, tomanConvert ));
        list.add(addObject("price_try",context.getResources().getString(R.string.frank), price_chf, iran_currency, tomanConvert ));
        list.add(addObject("price_cny",context.getResources().getString(R.string.yuan), price_cny, iran_currency, tomanConvert ));
        list.add(addObject("price_jpy",context.getResources().getString(R.string.yen), price_jpy, iran_currency, tomanConvert ));
        list.add(addObject("price_afn",context.getResources().getString(R.string.afghani), price_afn, iran_currency, tomanConvert ));
        list.add(addObject("price_inr",context.getResources().getString(R.string.rupee_india), price_inr, iran_currency, tomanConvert ));
        list.add(addObject("price_iqd",context.getResources().getString(R.string.dinar_iraq), price_iqd, iran_currency, tomanConvert ));
        list.add(addObject("price_sek",context.getResources().getString(R.string.krona_sweden), price_sek, iran_currency, tomanConvert ));
        list.add(addObject("price_myr",context.getResources().getString(R.string.ringgit_malaysian), price_myr, iran_currency, tomanConvert ));
        list.add(addObject("price_rub",context.getResources().getString(R.string.rouble), price_rub, iran_currency, tomanConvert ));
      }

      if (checkedFilter.equals("gold") || checkedFilter.equals("")) {
        list.add(addObject("sekee",context.getResources().getString(R.string.sekee), sekee, iran_currency, tomanConvert ));
        list.add(addObject("sekeb",context.getResources().getString(R.string.sekeb), sekeb, iran_currency, tomanConvert ));
        list.add(addObject("nim",context.getResources().getString(R.string.nim_seke), nim, iran_currency, tomanConvert ));
        list.add(addObject("rob",context.getResources().getString(R.string.rob_seke), rob, iran_currency, tomanConvert ));
        list.add(addObject("geram24",context.getResources().getString(R.string.gold_24), geram24, iran_currency, tomanConvert ));
        list.add(addObject("geram18",context.getResources().getString(R.string.gold_18), geram18, iran_currency, tomanConvert ));
        list.add(addObject("mesghal",context.getResources().getString(R.string.mesghal), mesghal, iran_currency, tomanConvert ));
        list.add(addObject("gerami",context.getResources().getString(R.string.seke_gram), gerami, iran_currency, tomanConvert ));
        list.add(addObject("ons",context.getResources().getString(R.string.gold_ons), ons, "دلار", false ));
        list.add(addObject("silver",context.getResources().getString(R.string.silver_ons), silver, "دلار", false ));
        list.add(addObject("gold_mini_size",context.getResources().getString(R.string.gold_mini_size), gold_mini_size, iran_currency, tomanConvert ));
      }

      if (checkedFilter.equals("oil") || checkedFilter.equals("")) {
        list.add(addObject("oil",context.getResources().getString(R.string.oil_usa), oil, "دلار", false ));
        list.add(addObject("oil_brent",context.getResources().getString(R.string.oil_brent), oil_brent, "دلار", false ));
        list.add(addObject("oil_opec",context.getResources().getString(R.string.oil_opec), oil_opec, "دلار", false ));
        list.add(addObject("general_9",context.getResources().getString(R.string.gas_usa), general_9, "دلار", false ));
        list.add(addObject("general_10",context.getResources().getString(R.string.gas_natural_usa), general_10, "دلار", false ));
        list.add(addObject("general_11",context.getResources().getString(R.string.gasoline_uk), general_11, "دلار", false ));
      }

      if (checkedFilter.equals("digital_currency") || checkedFilter.equals("")) {
        list.add(addObject("crypto-bitcoin",context.getResources().getString(R.string.bitcoin), crypto_bitcoin, "دلار", false ));
        list.add(addObject("crypto-ethereum",context.getResources().getString(R.string.ethereum), crypto_ethereum, "دلار", false ));
        list.add(addObject("crypto-ripple",context.getResources().getString(R.string.ripple), crypto_ripple,  "دلار", false ));
        list.add(addObject("crypto-dash",context.getResources().getString(R.string.dash), crypto_dash,  "دلار", false ));
        list.add(addObject("crypto-litecoin",context.getResources().getString(R.string.litecoin), crypto_litecoin,  "دلار", false ));
        list.add(addObject("crypto-stellar",context.getResources().getString(R.string.stellar), crypto_stellar,  "دلار", false ));
      }
    }

    return list;
  }

  public static PriceModel addObject(String objectName, String name, JSONObject object, String toCurrency, boolean tomanConvert) {
    PriceModel priceModel = null;
    try {
      String price = object.getString("p");
      String priceHigh = object.getString("p");
      String priceLow = object.getString("l");
      String priceChange = object.getString("d");

      // Change price if toman is selected in settings
      if (tomanConvert) {
        price = changeToToman(price, true);
        priceHigh = changeToToman(priceHigh, true);
        priceLow = changeToToman(priceLow, true);
        priceChange = changeToToman(priceChange, true);
      }

      priceModel = new PriceModel(
              objectName,
              name,
              toCurrency,
              price,
              priceHigh,
              priceLow,
              priceChange,
              object.getDouble("dp"),
              object.getString("dt"),
              object.getString("t"));
    } catch (JSONException e) {
      Log.e("ERROR EXCEPTION", String.valueOf(e));
    }
    return priceModel;
  }

  public static PriceTableModel priceTableList(JSONObject response, boolean isToman) throws JSONException {
    PriceTableModel model = new PriceTableModel();

    List<String> dateDaily = new ArrayList<>();
    List<Float> pricesDaily = new ArrayList<>();

    List<String> dateMonthly = new ArrayList<>();
    List<Float> pricesMonthly = new ArrayList<>();

    List<String> dateThreeMonths = new ArrayList<>();
    List<Float> pricesThreeMonths = new ArrayList<>();

    List<String> dateSixMonths = new ArrayList<>();
    List<Float> pricesSixMonths = new ArrayList<>();

    List<String> dateAllMonths = new ArrayList<>();
    List<Float> pricesAllMonths = new ArrayList<>();

    // Get Today Chart
    JSONArray today_table = response.getJSONArray("today_table");
    for (int i = today_table.length() - 1; i >= 0; i--) {
      try {
        JSONObject jo = today_table.getJSONObject(i);
        String time = jo.getString("time");
        String priceValue = jo.getString("price").replace(",", "");
        if (isToman)
          priceValue = changeToToman(priceValue, false);
        float price = Float.parseFloat(priceValue);
        dateDaily.add(time);
        pricesDaily.add(price);
      } catch (JSONException e) {
        Log.e("🔴ERROR Parse Daily", String.valueOf(e));
      }
    }

    // Get Monthly Chart
    String chart_1 = "[" + response.getString("chart_1").replaceFirst(".$","") + "]";
    JSONArray chart_1_Array = new JSONArray(chart_1);

    for (int i = 0; i < chart_1_Array.length(); i++) {
      try {
        JSONObject jo = chart_1_Array.getJSONObject(i);
        String date_string = jo.getString("jdate");
        date_string = date_string.substring(date_string.indexOf("/") + 1);
        String priceValue = String.valueOf(jo.getDouble("value"));
        if (isToman)
          priceValue = changeToToman(priceValue, false);
        float price = Float.parseFloat(priceValue);
        dateMonthly.add(date_string);
        pricesMonthly.add(price);
      } catch (JSONException e) {
        Log.e("🔴ERROR Parse Monthly", String.valueOf(e));
      }
    }

    // Get 3 Months Chart
    String chart_3 = "[" + response.getString("chart_3").replaceFirst(".$","") + "]";
    JSONArray chart_3_Array = new JSONArray(chart_3);

    for (int i = 0; i < chart_3_Array.length(); i++) {
      try {
        JSONObject jo = chart_3_Array.getJSONObject(i);
        String date_string = jo.getString("jdate");
        date_string = date_string.substring(date_string.indexOf("/") + 1);
        String priceValue = String.valueOf(jo.getDouble("value"));
        if (isToman)
          priceValue = changeToToman(priceValue, false);
        float price = Float.parseFloat(priceValue);
        dateThreeMonths.add(date_string);
        pricesThreeMonths.add(price);
      } catch (JSONException e) {
        Log.e("🔴ERROR Parse 3 Months", String.valueOf(e));
      }
    }

    // Get 6 Months Chart
    String chart_6 = "[" + response.getString("chart_6").replaceFirst(".$","") + "]";
    JSONArray chart_6_Array = new JSONArray(chart_6);

    for (int i = 0; i < chart_6_Array.length(); i++) {
      try {
        JSONObject jo = chart_6_Array.getJSONObject(i);
        String date_string = jo.getString("jdate");
        date_string = date_string.substring(date_string.indexOf("/") + 1);
        String priceValue = String.valueOf(jo.getDouble("value"));
        if (isToman)
          priceValue = changeToToman(priceValue, false);
        float price = Float.parseFloat(priceValue);
        dateSixMonths.add(date_string);
        pricesSixMonths.add(price);
      } catch (JSONException e) {
        Log.e("🔴ERROR Parse 6 Months", String.valueOf(e));
      }
    }

    // Get Chart Summery
    String chart_summary = "[" + response.getString("chart_summary") + "]";
    JSONArray chart_summary_Array = new JSONArray(chart_summary);

    for (int i = 0; i < chart_summary_Array.length(); i++) {
      try {
        JSONObject jo = chart_summary_Array.getJSONObject(i);
        String date_string = jo.getString("jdate");
        int index = date_string.lastIndexOf('/');
        String priceValue = String.valueOf(jo.getDouble("value"));
        if (isToman)
          priceValue = changeToToman(priceValue, false);
        float price = Float.parseFloat(priceValue);
        dateAllMonths.add(date_string.substring(0,index));
        pricesAllMonths.add(price);
      } catch (JSONException e) {
        Log.e("🔴ERROR Parse All", String.valueOf(e));
      }
    }

    model.setDateDaily(dateDaily);
    model.setPricesDaily(pricesDaily);
    model.setDateMonthly(dateMonthly);
    model.setPricesMonthly(pricesMonthly);
    model.setDateThreeMonths(dateThreeMonths);
    model.setPricesThreeMonths(pricesThreeMonths);
    model.setDateSixMonths(dateSixMonths);
    model.setPricesSixMonths(pricesSixMonths);
    model.setDateAllMonths(dateAllMonths);
    model.setPricesAllMonths(pricesAllMonths);

    return model;
  }

  public static ArrayList<UnitItem> priceConverterList(JSONObject response,JSONObject bonbast, Context context) throws JSONException {
    ArrayList<UnitItem> unitItems = new ArrayList<>();
    JSONObject jsonData = response.optJSONObject("current");
    if (jsonData != null) {
      if(bonbast!=null) {
        try {
          jsonData.getJSONObject("price_dollar_soleymani").put("p", bonbast.getJSONObject("US Dollar").getString("sell") + "0");
          jsonData.getJSONObject("price_eur").put("p", bonbast.getJSONObject("Euro").getString("sell") + "0");
          jsonData.getJSONObject("price_gbp").put("p", bonbast.getJSONObject("British Pound").getString("sell") + "0");
          jsonData.getJSONObject("price_chf").put("p", bonbast.getJSONObject("Swiss Franc").getString("sell") + "0");
          jsonData.getJSONObject("price_cad").put("p", bonbast.getJSONObject("Canadian Dollar").getString("sell") + "0");
          jsonData.getJSONObject("price_aud").put("p", bonbast.getJSONObject("Australian Dollar").getString("sell") + "0");
          jsonData.getJSONObject("price_sek").put("p", bonbast.getJSONObject("Swedish Krona").getString("sell") + "0");
          jsonData.getJSONObject("price_rub").put("p", bonbast.getJSONObject("Russian Ruble").getString("sell") + "0");
          jsonData.getJSONObject("price_sgd").put("p", bonbast.getJSONObject("Singapore Dollar").getString("sell") + "0");
          jsonData.getJSONObject("price_aed").put("p", bonbast.getJSONObject("UAE Dirham").getString("sell") + "0");
          jsonData.getJSONObject("price_jpy").put("p", bonbast.getJSONObject("Japanese Yen").getString("sell") + "0");
          jsonData.getJSONObject("price_try").put("p", bonbast.getJSONObject("Turkish Lira").getString("sell") + "0");
          jsonData.getJSONObject("price_cny").put("p", bonbast.getJSONObject("Chinese Yuan").getString("sell") + "0");
          jsonData.getJSONObject("price_inr").put("p", bonbast.getJSONObject("Indian Rupee").getString("sell") + "0");
          jsonData.getJSONObject("price_myr").put("p", bonbast.getJSONObject("Ringgit").getString("sell") + "0");
          jsonData.getJSONObject("price_afn").put("p", bonbast.getJSONObject("Afghan Afghani").getString("sell") + "0");
          jsonData.getJSONObject("price_iqd").put("p", bonbast.getJSONObject("Iraqi Dinar").getString("sell") + "0");


          jsonData.getJSONObject("sekeb").put("p",bonbast.getJSONObject("Azadi").getString("sell")+"0");
          jsonData.getJSONObject("sekee").put("p",bonbast.getJSONObject("Emami").getString("sell")+"0");
          jsonData.getJSONObject("nim").put("p",bonbast.getJSONObject("\u00bd Azadi").getString("sell")+"0");
          jsonData.getJSONObject("rob").put("p",bonbast.getJSONObject("\u00bc Azadi").getString("sell")+"0");
          jsonData.getJSONObject("gerami").put("p",bonbast.getJSONObject("Gerami").getString("sell")+"0");
          jsonData.getJSONObject("mesghal").put("p",bonbast.getJSONObject("Gold Mithqal").getString("sell")+"0");
          jsonData.getJSONObject("ons").put("p",bonbast.getJSONObject("Gold Ounce").getString("sell"));


          jsonData.getJSONObject("crypto-bitcoin").put("p",bonbast.getJSONObject("Bitcoin").getString("sell"));
        }catch (Exception e){
          Log.e("fuck",e.toString());
        }
      }
      double rial = 1.0;
      double dollar = Double.parseDouble(jsonData.getJSONObject("price_dollar_rl").getString("p").replace(",", ""));
      double dollar_soleymani = Double.parseDouble(jsonData.getJSONObject("price_dollar_soleymani").getString("p").replace(",", ""));
      double euro = Double.parseDouble(jsonData.getJSONObject("price_eur").getString("p").replace(",", ""));
      double dollar_canada = Double.parseDouble(jsonData.getJSONObject("price_cad").getString("p").replace(",", ""));
      double dollar_australia = Double.parseDouble(jsonData.getJSONObject("price_aud").getString("p").replace(",", ""));
      double dollar_new_zealand = Double.parseDouble(jsonData.getJSONObject("price_nzd").getString("p").replace(",", ""));
      double dollar_singapore = Double.parseDouble(jsonData.getJSONObject("price_sgd").getString("p").replace(",", ""));
      double pound = Double.parseDouble(jsonData.getJSONObject("price_gbp").getString("p").replace(",", ""));
      double dirham = Double.parseDouble(jsonData.getJSONObject("price_aed").getString("p").replace(",", ""));
      double lira = Double.parseDouble(jsonData.getJSONObject("price_try").getString("p").replace(",", ""));
      double frank = Double.parseDouble(jsonData.getJSONObject("price_chf").getString("p").replace(",", ""));
      double yuan = Double.parseDouble(jsonData.getJSONObject("price_cny").getString("p").replace(",", ""));
      double yen = Double.parseDouble(jsonData.getJSONObject("price_jpy").getString("p").replace(",", ""));
      double afghani = Double.parseDouble(jsonData.getJSONObject("price_afn").getString("p").replace(",", ""));
      double rupee_india = Double.parseDouble(jsonData.getJSONObject("price_inr").getString("p").replace(",", ""));
      double dinar = Double.parseDouble(jsonData.getJSONObject("price_iqd").getString("p").replace(",", ""));
      double krona_sweden = Double.parseDouble(jsonData.getJSONObject("price_sek").getString("p").replace(",", ""));
      double ringgit = Double.parseDouble(jsonData.getJSONObject("price_myr").getString("p").replace(",", ""));
      double rouble = Double.parseDouble(jsonData.getJSONObject("price_rub").getString("p").replace(",", ""));

      unitItems.add(new UnitItem(context.getResources().getString(R.string.rial), R.drawable.flag_ir, rial));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.dollar), R.drawable.flag_us, dollar));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.dollar_bonbast), R.drawable.flag_us, dollar_soleymani));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.euro), R.drawable.flag_eu, euro));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.dollar_canada), R.drawable.flag_ca, dollar_canada));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.dollar_australia), R.drawable.flag_au, dollar_australia));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.dollar_new_zealand), R.drawable.flag_nz, dollar_new_zealand));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.dollar_singapore), R.drawable.flag_sg, dollar_singapore));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.pound), R.drawable.flag_uk, pound));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.dirham), R.drawable.flag_ae, dirham));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.lira), R.drawable.flag_tr, lira));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.frank), R.drawable.flag_ch, frank));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.yuan), R.drawable.flag_cn, yuan));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.yen), R.drawable.flag_jp, yen));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.afghani), R.drawable.flag_af, afghani));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.rupee_india), R.drawable.flag_in, rupee_india));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.dinar_iraq), R.drawable.flag_iq, dinar));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.krona_sweden), R.drawable.flag_se, krona_sweden));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.ringgit_malaysian), R.drawable.flag_my, ringgit));
      unitItems.add(new UnitItem(context.getResources().getString(R.string.rouble), R.drawable.flag_ru, rouble));
    }
    return unitItems;
  }

  public static String changeToToman(String price, boolean commaNeeded) {
    price = price.replace(",", "");
    double priceValue = Double.parseDouble(price) / 10;

    NumberFormat numberFormat = NumberFormat.getInstance(new Locale("en","US"));
    numberFormat.setGroupingUsed(false);
    price = numberFormat.format(priceValue);

    if (commaNeeded)
      price = addComma(price);

    return price;
  }

  public static String addComma(String str) {
    int floatPos = str.contains(".") ? str.length() - str.indexOf(".") : 0;
    int nGroups= (str.length()-floatPos-1-(str.contains("-") ?1:0))/3;
    for(int i=0; i<nGroups; i++){
      int commaPos = str.length() - i * 4 - 3 - floatPos;
      str = str.substring(0,commaPos) + "," + str.substring(commaPos);
    }
    return str;
  }
}
