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
 * {@code DogmaEffect} represents a dogma effect object from OpenAPI for EVE Online.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DogmaEffect {

  private String effect_id;
  private String is_default;


  public DogmaEffect() {
  }

  public DogmaEffect(String effect_id, String is_default) {
    this.effect_id = effect_id;
    this.is_default = is_default;
  }

  public String getEffect_id() {
    return effect_id;
  }

  public void setEffect_id(String effect_id) {
    this.effect_id = effect_id;
  }

  public String getIs_default() {
    return is_default;
  }

  public void setIs_default(String is_default) {
    this.is_default = is_default;
  }

  @Override
  public String toString() {
    return "DogmaEffect{" + "effect_id=[" + effect_id + "], is_default=[" + is_default + "]}";
  }
}
