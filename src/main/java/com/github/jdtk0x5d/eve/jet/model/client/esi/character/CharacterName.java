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

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class CharacterName {

  private long character_id;
  private String character_name;

  public long getCharacter_id() {
    return character_id;
  }

  public void setCharacter_id(long character_id) {
    this.character_id = character_id;
  }

  public String getCharacter_name() {
    return character_name;
  }

  public void setCharacter_name(String character_name) {
    this.character_name = character_name;
  }

  @Override
  public String toString() {
    return "CharacterName{" + "character_id=[" + character_id + "], character_name=[" + character_name + "]}";
  }
}
