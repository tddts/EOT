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
 * {@code DogmaAttribute} represents a dogma attribute object from OpenAPI for EVE Online.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DogmaAttribute {

  private String value;
  private String attribute_id;

  public DogmaAttribute() {
  }

  public DogmaAttribute(String value, String attribute_id) {
    this.value = value;
    this.attribute_id = attribute_id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getAttribute_id() {
    return attribute_id;
  }

  public void setAttribute_id(String attribute_id) {
    this.attribute_id = attribute_id;
  }

  @Override
  public String toString() {
    return "DogmaAttribute{" + "value=[" + value + "], attribute_id=[" + attribute_id + "]}";
  }
}
