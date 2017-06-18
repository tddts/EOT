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

package com.github.jdtk0x5d.eve.jet.model.client.esi.location;

/**
 * {@code Location} represents a location object from EVE Swagger Interface (an OpenAPI for EVE Online).
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Location {

  private long station_id;
  private long solar_system_id;

  public long getStation_id() {
    return station_id;
  }

  public void setStation_id(long station_id) {
    this.station_id = station_id;
  }

  public long getSolar_system_id() {
    return solar_system_id;
  }

  public void setSolar_system_id(long solar_system_id) {
    this.solar_system_id = solar_system_id;
  }

  @Override
  public String toString() {
    return "Location{" + "station_id=[" + station_id + "], solar_system_id=[" + solar_system_id + "]}";
  }
}
