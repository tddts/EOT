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

package com.github.tddts.jet.model.client.esi.sso;

/**
 * {@code AccessToken} represents a access token object from EVE Swagger Interface (an OpenAPI for EVE Online).
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class AccessToken {

  private String access_token;
  private String refresh_token;
  private String token_type;
  private long expires_in;

  public AccessToken() {
  }

  public String getFullValue() {
    return token_type + " " + access_token;
  }

  public String getAccess_token() {
    return access_token;
  }

  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }

  public String getRefresh_token() {
    return refresh_token;
  }

  public void setRefresh_token(String refresh_token) {
    this.refresh_token = refresh_token;
  }

  public String getToken_type() {
    return token_type;
  }

  public void setToken_type(String token_type) {
    this.token_type = token_type;
  }

  public long getExpires_in() {
    return expires_in;
  }

  public void setExpires_in(long expires_in) {
    this.expires_in = expires_in;
  }

  @Override
  public String toString() {
    return "AccessToken{" + "access_token=[" + access_token + "], refresh_token=[" + refresh_token + "], token_type=["
        + token_type + "], expires_in=[" + expires_in + "]}";
  }
}
