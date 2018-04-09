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

package com.github.tddts.jet.model.client.esi.sso;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class CharacterInfo {

  private String ExpiresOn;
  private long CharacterID;
  private String CharacterOwnerHash;
  private String Scopes;
  private String TokenType;
  private String CharacterName;

  public CharacterInfo() {
  }

  public String getExpiresOn() {
    return ExpiresOn;
  }

  public void setExpiresOn(String expiresOn) {
    this.ExpiresOn = expiresOn;
  }

  public long getCharacterID() {
    return CharacterID;
  }

  public void setCharacterID(long characterID) {
    this.CharacterID = characterID;
  }

  public String getCharacterOwnerHash() {
    return CharacterOwnerHash;
  }

  public void setCharacterOwnerHash(String characterOwnerHash) {
    this.CharacterOwnerHash = characterOwnerHash;
  }

  public String getScopes() {
    return Scopes;
  }

  public void setScopes(String scopes) {
    this.Scopes = scopes;
  }

  public String getTokenType() {
    return TokenType;
  }

  public void setTokenType(String tokenType) {
    this.TokenType = tokenType;
  }

  public String getCharacterName() {
    return CharacterName;
  }

  public void setCharacterName(String characterName) {
    this.CharacterName = characterName;
  }
}
