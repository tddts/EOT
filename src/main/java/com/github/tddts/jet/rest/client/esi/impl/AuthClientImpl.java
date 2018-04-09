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

package com.github.tddts.jet.rest.client.esi.impl;

import com.github.tddts.jet.model.client.esi.sso.CharacterInfo;
import com.github.tddts.jet.rest.RestResponse;
import com.github.tddts.jet.config.spring.annotations.RestClient;
import com.github.tddts.jet.config.spring.beans.UserBean;
import com.github.tddts.jet.model.client.esi.sso.AccessToken;
import com.github.tddts.jet.rest.client.esi.AuthClient;
import com.github.tddts.jet.rest.provider.RestClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestClient
public class AuthClientImpl implements AuthClient {

  @Value("${path.auth.authorize}")
  private String pathAuthorize;

  @Value("${path.auth.token}")
  private String pathToken;

  @Value("${path.auth.verify}")
  private String pathVerify;

  @Autowired
  private UserBean userBean;

  @Autowired
  private RestClientTemplate client;

  @Override
  public RestResponse<AccessToken> getToken(String authCode) {
    URI uri = client.authUriBuilder(pathAuthorize)
        .queryParam("grant_type", "authorization_code")
        .queryParam("code", authCode)
        .build().toUri();

    return getToken(uri);
  }

  @Override
  public RestResponse<AccessToken> refreshToken() {
    URI uri = client.authUriBuilder(pathToken)
        .queryParam("grant_type", "refresh_token")
        .queryParam("refresh_token", userBean.getAccessToken().getRefresh_token())
        .build().toUri();

    return getToken(uri);
  }

  @Override
  public RestResponse<CharacterInfo> getCharacterInfo(AccessToken accessToken) {
    URI uri = client.authUriBuilder(pathVerify).build().toUri();
    return new RestResponse<>(client.restOperations().exchange(
        uri, HttpMethod.GET, client.bearerAuthorizedEntity(), CharacterInfo.class));
  }

  private RestResponse<AccessToken> getToken(URI uri) {
    return new RestResponse<>(client.restOperations().exchange(
        uri, HttpMethod.POST, client.basicAuthorizedEntity(), AccessToken.class));
  }
}
