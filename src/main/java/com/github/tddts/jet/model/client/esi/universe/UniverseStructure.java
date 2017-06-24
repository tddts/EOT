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

package com.github.tddts.jet.model.client.esi.universe;

/**
 * {@code UniverseStructure} represents a universal structure object from EVE Swagger Interface (an OpenAPI for EVE Online).
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class UniverseStructure {

  private String name;
  private long solar_system_id;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getSolar_system_id() {
    return solar_system_id;
  }

  public void setSolar_system_id(long solar_system_id) {
    this.solar_system_id = solar_system_id;
  }

  @Override
  public String toString() {
    return "UniverseStructure{" + "name=[" + name + "], solar_system_id=[" + solar_system_id + "]}";
  }
}