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
 * {@code CharacterPortrait} represents a portrait object for character from EVE Swagger Interface (an OpenAPI for EVE Online).
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class CharacterPortrait {

  private String px256x256;
  private String px64x64;
  private String px512x512;
  private String px128x128;

  public String getPx256x256() {
    return px256x256;
  }

  public void setPx256x256(String px256x256) {
    this.px256x256 = px256x256;
  }

  public String getPx64x64() {
    return px64x64;
  }

  public void setPx64x64(String px64x64) {
    this.px64x64 = px64x64;
  }

  public String getPx512x512() {
    return px512x512;
  }

  public void setPx512x512(String px512x512) {
    this.px512x512 = px512x512;
  }

  public String getPx128x128() {
    return px128x128;
  }

  public void setPx128x128(String px128x128) {
    this.px128x128 = px128x128;
  }

  @Override
  public String toString() {
    return "CharacterPortrait{" + "px256x256=[" + px256x256 + "], px64x64=[" + px64x64 + "], px512x512=[" + px512x512 + "], px128x128=[" + px128x128 + "]}";
  }
}
