/*
 * Copyright 2017 Tigran Dadaiants
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tddts.jet.model.client.esi.market;

/**
 * {@code MarketPrice} represents a market price object from EVE Swagger Interface (an OpenAPI for EVE Online).
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class MarketPrice {

  private double average_price;
  private double adjusted_price;
  private double type_id;

  public double getAverage_price() {
    return average_price;
  }

  public void setAverage_price(double average_price) {
    this.average_price = average_price;
  }

  public double getAdjusted_price() {
    return adjusted_price;
  }

  public void setAdjusted_price(double adjusted_price) {
    this.adjusted_price = adjusted_price;
  }

  public double getType_id() {
    return type_id;
  }

  public void setType_id(double type_id) {
    this.type_id = type_id;
  }

  @Override
  public String toString() {
    return "MarketPrice{" + "average_price=[" + average_price + "], adjusted_price=[" + adjusted_price + "], type_id=[" + type_id + "]}";
  }
}
