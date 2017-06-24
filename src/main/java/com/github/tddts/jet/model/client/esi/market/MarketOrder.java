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

import java.time.LocalDateTime;

/**
 * {@code MarketOrder} represents a market order object from EVE Swagger Interface (an OpenAPI for EVE Online).
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class MarketOrder {

  private boolean is_buy_order;
  private int duration;
  private double price;
  private String range;

  private LocalDateTime issued;
  private long order_id;
  private int type_id;
  private long location_id;

  private long min_volume;
  private long volume_remain;
  private long volume_total;

  public boolean getIs_buy_order() {
    return is_buy_order;
  }

  public void setIs_buy_order(boolean is_buy_order) {
    this.is_buy_order = is_buy_order;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public long getVolume_remain() {
    return volume_remain;
  }

  public void setVolume_remain(long volume_remain) {
    this.volume_remain = volume_remain;
  }

  public String getRange() {
    return range;
  }

  public void setRange(String range) {
    this.range = range;
  }

  public long getMin_volume() {
    return min_volume;
  }

  public void setMin_volume(long min_volume) {
    this.min_volume = min_volume;
  }

  public LocalDateTime getIssued() {
    return issued;
  }

  public void setIssued(LocalDateTime issued) {
    this.issued = issued;
  }

  public long getOrder_id() {
    return order_id;
  }

  public void setOrder_id(long order_id) {
    this.order_id = order_id;
  }

  public long getVolume_total() {
    return volume_total;
  }

  public void setVolume_total(long volume_total) {
    this.volume_total = volume_total;
  }

  public int getType_id() {
    return type_id;
  }

  public void setType_id(int type_id) {
    this.type_id = type_id;
  }

  public long getLocation_id() {
    return location_id;
  }

  public void setLocation_id(long location_id) {
    this.location_id = location_id;
  }

  @Override
  public String toString() {
    return "MarketOrder{" + "is_buy_order=[" + is_buy_order + "], duration=[" + duration + "], price=["
        + price + "], range=[" + range + "], issued=[" + issued + "], order_id=[" + order_id + "], type_id=["
        + type_id + "], location_id=[" + location_id + "], min_volume=[" + min_volume + "], volume_remain=["
        + volume_remain + "], volume_total=[" + volume_total + "]}";
  }
}