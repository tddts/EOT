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
 * {@code UniverseType} represents a universal type object from OpenAPI for EVE Online.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class UniverseType {

  private DogmaEffect[] dogma_effects;

  private String group_id;

  private String portion_size;

  private String type_id;

  private String mass;

  private String graphic_id;

  private String description;

  private String name;

  private String volume;

  private String capacity;

  private DogmaAttribute[] dogma_attributes;

  private String radius;

  private String published;

  public UniverseType() {
  }

  public DogmaEffect[] getDogma_effects() {
    return dogma_effects;
  }

  public void setDogma_effects(DogmaEffect[] dogma_effects) {
    this.dogma_effects = dogma_effects;
  }

  public String getGroup_id() {
    return group_id;
  }

  public void setGroup_id(String group_id) {
    this.group_id = group_id;
  }

  public String getPortion_size() {
    return portion_size;
  }

  public void setPortion_size(String portion_size) {
    this.portion_size = portion_size;
  }

  public String getType_id() {
    return type_id;
  }

  public void setType_id(String type_id) {
    this.type_id = type_id;
  }

  public String getMass() {
    return mass;
  }

  public void setMass(String mass) {
    this.mass = mass;
  }

  public String getGraphic_id() {
    return graphic_id;
  }

  public void setGraphic_id(String graphic_id) {
    this.graphic_id = graphic_id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVolume() {
    return volume;
  }

  public void setVolume(String volume) {
    this.volume = volume;
  }

  public String getCapacity() {
    return capacity;
  }

  public void setCapacity(String capacity) {
    this.capacity = capacity;
  }

  public DogmaAttribute[] getDogma_attributes() {
    return dogma_attributes;
  }

  public void setDogma_attributes(DogmaAttribute[] dogma_attributes) {
    this.dogma_attributes = dogma_attributes;
  }

  public String getRadius() {
    return radius;
  }

  public void setRadius(String radius) {
    this.radius = radius;
  }

  public String getPublished() {
    return published;
  }

  public void setPublished(String published) {
    this.published = published;
  }
}
