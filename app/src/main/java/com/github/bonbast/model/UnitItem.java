
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

package com.github.bonbast.model;

public class UnitItem {
  private String unitName;
  private int unitFlagImage;
  private double unitPrice;

  public String getUnitName() {
    return unitName;
  }

  public int getUnitFlagImage() {
    return unitFlagImage;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public UnitItem(String unitName, int unitFlagImage, double unitPrice) {
    this.unitName = unitName;
    this.unitFlagImage = unitFlagImage;
    this.unitPrice = unitPrice;
  }
}
