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

package com.github.jdtk0x5d.eve.jet.model.client.esi.character;

import java.time.LocalDateTime;

/**
 * {@code CharacterPublicInfo} represents a character public information object from EVE Swagger Interface (an OpenAPI for EVE Online).
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class CharacterPublicInfo {

  private LocalDateTime birthday;
  private String description;
  private String name;
  private String gender;
  private long corporation_id;
  private int ancestry_id;
  private int bloodline_id;
  private int race_id;


  public LocalDateTime getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDateTime birthday) {
    this.birthday = birthday;
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public long getCorporation_id() {
    return corporation_id;
  }

  public void setCorporation_id(long corporation_id) {
    this.corporation_id = corporation_id;
  }

  public int getAncestry_id() {
    return ancestry_id;
  }

  public void setAncestry_id(int ancestry_id) {
    this.ancestry_id = ancestry_id;
  }

  public int getBloodline_id() {
    return bloodline_id;
  }

  public void setBloodline_id(int bloodline_id) {
    this.bloodline_id = bloodline_id;
  }

  public int getRace_id() {
    return race_id;
  }

  public void setRace_id(int race_id) {
    this.race_id = race_id;
  }

  @Override
  public String toString() {
    return "CharacterPublicInfo{" + "birthday=[" + birthday + "], description=[" + description
        + "], name=[" + name + "], gender=[" + gender + "], corporation_id=[" + corporation_id
        + "], ancestry_id=[" + ancestry_id + "], bloodline_id=[" + bloodline_id + "], race_id=[" + race_id + "]}";
  }
}
