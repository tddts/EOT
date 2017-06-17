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

package com.github.jdtk0x5d.eve.jet.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;

import java.net.URI;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface AuthService {

  String getLoginPage();

  URI getLoginPageURI();

  String getLoginPage(String clientId);

  URI getLoginPageURI(String clientId);

  void processAccessToken(String query);

  HttpStatus processAuthorizationCode(String query) throws RestClientException;

  void refreshAccessToken() throws RestClientException;

  HttpStatus processAuthorization(String query) throws RestClientException;
}
