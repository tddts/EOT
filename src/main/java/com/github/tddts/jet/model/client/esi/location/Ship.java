/*
 * Copyright 2018 Tigran Dadaiants
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

package com.github.tddts.jet.model.client.esi.location;

/**
 * {@code Ship} represents a ship object from OpenAPI for EVE Online.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Ship {

  private long ship_type_id;
  private long ship_item_id;
  private String ship_name;

  public long getShip_type_id() {
    return ship_type_id;
  }

  public void setShip_type_id(long ship_type_id) {
    this.ship_type_id = ship_type_id;
  }

  public String getShip_name() {
    return ship_name;
  }

  public void setShip_name(String ship_name) {
    this.ship_name = ship_name;
  }

  public long getShip_item_id() {
    return ship_item_id;
  }

  public void setShip_item_id(long ship_item_id) {
    this.ship_item_id = ship_item_id;
  }

  @Override
  public String toString() {
    return "Ship{" + "ship_type_id=[" + ship_type_id + "], ship_item_id=[" + ship_item_id + "], ship_name=[" + ship_name + "]}";
  }
}
