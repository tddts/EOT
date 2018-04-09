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

package com.github.tddts.jet.service;

import com.github.tddts.jet.model.client.esi.sso.AccessToken;
import org.apache.http.NameValuePair;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * {@code AuthService} represents a service providing access to EVE Swagger Interface
 * (an OpenAPI for EVE Online) authorization.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface AuthService {

  /**
   * Get EVE Online login page URL.
   *
   * @return login page URL as String.
   */
  String getLoginPage();

  /**
   * Get EVE Online login page URL for given client id.
   *
   * @param clientId client id
   * @return login page URL as String.
   */
  String getLoginPage(String clientId);

  /**
   * Get EVE Online login page URI.
   *
   * @return login page URI.
   */
  URI getLoginPageURI();

  /**
   * Get EVE Online login page URI for given client id.
   *
   * @param clientId client id
   * @return login page URI.
   */
  URI getLoginPageURI(String clientId);

  /**
   * Process access token in given URI quiery
   *
   * @param query URI query
   */
  void processAccessToken(String query);

  /**
   * Process authorization code in given URI quiery
   *
   * @param query URI query
   */
  HttpStatus processAuthorizationCode(String query);

  /**
   * Process access token in given URI parameters
   *
   * @param params URI parameters
   */
  void processAccessToken(Map<String, String> params);

  /**
   * Process authorization code in given URI parameters
   *
   * @param params URI query
   */
  HttpStatus processAuthorizationCode(Map<String, String> params);

  /**
   * Refresh existing access token.
   */
  void refreshAccessToken();

  /**
   * Process either access code or authorization code from given query depending on it's content.
   *
   * @param query URI query
   */
  HttpStatus processAuthorization(String query);

  /**
   * Process either access code or authorization code from given parameters.
   *
   * @param params URI parameters
   */
  HttpStatus processAuthorization(List<NameValuePair> params);

  /**
   * Process either access code or authorization code from given parameters.
   *
   * @param params URI parameters
   */
  HttpStatus processAuthorization(Map<String, String> params);

  /**
   * Build access token object from given parameters.
   *
   * @param params URI parameters
   * @return access token
   */
  AccessToken buildAccessToken(Map<String, String> params);
}
