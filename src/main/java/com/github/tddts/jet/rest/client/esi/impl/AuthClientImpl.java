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

package com.github.tddts.jet.rest.client.esi.impl;

import com.github.tddts.jet.rest.RestResponse;
import com.github.tddts.jet.config.spring.annotations.RestClient;
import com.github.tddts.jet.config.spring.beans.UserBean;
import com.github.tddts.jet.model.client.esi.sso.AccessToken;
import com.github.tddts.jet.rest.client.esi.AuthClient;
import com.github.tddts.jet.rest.provider.RestClientProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestClient
public class AuthClientImpl implements AuthClient {

  @Value("${path.auth.authorize}")
  private String addressAuthorize;

  @Value("${path.auth.token}")
  private String addressToken;

  @Autowired
  private UserBean userBean;

  @Autowired
  private RestClientProvider client;

  @Override
  public RestResponse<AccessToken> getToken(String authCode) {
    URI uri = client.authUriBuilder(addressAuthorize)
        .queryParam("grant_type", "authorization_code")
        .queryParam("code", authCode)
        .build().toUri();

    return getToken(uri);
  }

  @Override
  public RestResponse<AccessToken> refreshToken() {
    URI uri = client.authUriBuilder(addressToken)
        .queryParam("grant_type", "refresh_token")
        .queryParam("refresh_token", userBean.getAccessToken().getRefresh_token())
        .build().toUri();

    return getToken(uri);
  }

  private RestResponse<AccessToken> getToken(URI uri) {
    return new RestResponse<>(client.restOperations().exchange(uri, HttpMethod.POST, basicAuthEntity(), AccessToken.class));
  }

  private HttpEntity<?> basicAuthEntity() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    String authHeader = userBean.getClientId() + ":" + userBean.getSercretKey();
    String base64EncodedHeader = new String(Base64.getEncoder().encode(authHeader.getBytes()), Charset.forName("UTF-8"));
    headers.set("Authorization", "Basic " + base64EncodedHeader);
    return new HttpEntity<>(null, headers);
  }
}
