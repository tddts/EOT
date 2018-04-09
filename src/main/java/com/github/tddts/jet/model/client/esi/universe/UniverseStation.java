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

package com.github.tddts.jet.model.client.esi.universe;

/**
 * {@code UniverseStation} represents a universal station object from OpenAPI for EVE Online.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class UniverseStation {

  private String station_name;
  private long solar_system_id;

  public String getStation_name() {
    return station_name;
  }

  public void setStation_name(String station_name) {
    this.station_name = station_name;
  }

  public long getSolar_system_id() {
    return solar_system_id;
  }

  public void setSolar_system_id(long solar_system_id) {
    this.solar_system_id = solar_system_id;
  }

  @Override
  public String toString() {
    return "UniverseStation{" + "station_name=[" + station_name + "], solar_system_id=[" + solar_system_id + "]}";
  }
}
