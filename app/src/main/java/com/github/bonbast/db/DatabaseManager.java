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

package com.github.bonbast.db;

import android.content.Context;
import android.util.Log;

import com.orhanobut.hawk.Hawk;
import com.github.bonbast.model.FavoriteModel;
import com.github.bonbast.model.PriceModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
  private static final String KEY_RAW_DATA = "raw_data_list";
  private static final String KEY_EXCHANGE = "exchange_list";
  private static final String KEY_FAVORITE = "favorite_list";
  private static final String KEY_BONBAST = "bonbast";
  private static DatabaseManager databaseManager;

  public static DatabaseManager getInstance() {
    if (databaseManager == null) {
      databaseManager = new DatabaseManager();
    }
    return databaseManager;
  }

  public void init(Context context) {
    Hawk.init(context.getApplicationContext()).build();
  }

  public void deleteRawData() {
    Hawk.delete(KEY_RAW_DATA);
  }

  public void setRawData(String rawData) {
    boolean status = Hawk.put(KEY_RAW_DATA, rawData);
  }

  public String getRawData() {
    return Hawk.get(KEY_RAW_DATA);
  }

  public void setBonbastData(String rawData) {
    boolean status = Hawk.put(KEY_BONBAST, rawData);
  }

  public String getBonbastData() {
    return Hawk.get(KEY_BONBAST);
  }

  public boolean isRawDataAvailable() {
    String rawData = getRawData();
    return rawData != null && !rawData.isEmpty();
  }

  public void deletePriceList() {
    Hawk.delete(KEY_EXCHANGE);
  }

  public void setPriceList(List<PriceModel> priceList) {
    boolean status = Hawk.put(KEY_EXCHANGE, priceList);
  }

  public List<PriceModel> getPriceList() {
    return Hawk.get(KEY_EXCHANGE);
  }

  public boolean isPriceListAvailable() {
    List<PriceModel> priceList = getPriceList();
    return priceList != null && !priceList.isEmpty();
  }

  public void deleteFavoriteList() {
    Hawk.delete(KEY_FAVORITE);
  }

  public void deleteFromFavoriteList(FavoriteModel item) {
    ArrayList<FavoriteModel> favoriteList = getFavoriteList();
    favoriteList.remove(item);
    setFavoriteList(favoriteList);
  }

  public void addToFavoriteList(FavoriteModel item) {
    ArrayList<FavoriteModel> favoriteList = getFavoriteList();
    if (favoriteList == null)
      favoriteList = new ArrayList<>();
    favoriteList.add(item);
    setFavoriteList(favoriteList);
    Log.e("⭐️ FAVORITE LIST:", String.valueOf(favoriteList));
  }

  public void setFavoriteList(ArrayList<FavoriteModel> favoriteList) {
    boolean status = Hawk.put(KEY_FAVORITE, favoriteList);
  }

  public ArrayList<FavoriteModel> getFavoriteList() {
    return Hawk.get(KEY_FAVORITE);
  }

  public boolean isFavoriteListAvailable() {
    ArrayList<FavoriteModel> favoriteList = getFavoriteList();
    return favoriteList != null && !favoriteList.isEmpty();
  }
}
