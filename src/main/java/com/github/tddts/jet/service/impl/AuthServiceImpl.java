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

package com.github.tddts.jet.service.impl;

import com.github.tddts.jet.config.spring.beans.UserBean;
import com.github.tddts.jet.context.events.AuthorizationEvent;
import com.github.tddts.jet.model.client.esi.sso.AccessToken;
import com.github.tddts.jet.model.client.esi.sso.CharacterInfo;
import com.github.tddts.jet.oauth.QueryParser;
import com.github.tddts.jet.rest.RestResponse;
import com.github.tddts.jet.rest.client.esi.AuthClient;
import com.github.tddts.jet.service.AuthService;
import com.google.common.eventbus.EventBus;
import org.apache.http.NameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Service
public class AuthServiceImpl implements AuthService {

  private static final String CODE = "code";
  private static final String TOKEN = "token";

  private final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

  @Value("${login.url.authorize}")
  private String loginAuthURL;

  @Value("${login.redirect.uri}")
  private String redirectURI;

  @Value("${login.realm}")
  private String realm;

  @Value("${login.client.id}")
  private String defaultClientId;

  @Value("${login.scopes}")
  private String scopes;

  @Value("${login.state}")
  private String state;

  @Autowired
  private QueryParser queryParser;

  @Autowired
  private UserBean userBean;

  @Autowired
  private EventBus eventBus;

  @Autowired
  private AuthClient authClient;


  @Override
  public String getLoginPage(String clientId) {
    return getLoginPageURI(clientId).toString();
  }

  @Override
  public URI getLoginPageURI(String clientId) {
    return getLoginPageURI(clientId, CODE);
  }

  @Override
  public String getLoginPage() {
    return getLoginPageURI().toString();
  }

  @Override
  public URI getLoginPageURI() {
    return getLoginPageURI(defaultClientId, TOKEN);
  }

  private URI getLoginPageURI(String clientId, String responseType) {
    return UriComponentsBuilder.fromHttpUrl(loginAuthURL)
        .queryParam("response_type", responseType)
        .queryParam("redirect_uri", redirectURI)
        .queryParam("realm", realm)
        .queryParam("client_id", clientId)
        .queryParam("scope", scopes)
        .queryParam("state", state)
        .build().toUri();
  }

  @Override
  public void processAccessToken(String query) {
    processAccessToken(queryParser.parseQuery(query));
  }

  @Override
  public HttpStatus processAuthorizationCode(String query) {
    return processAuthorizationCode(queryParser.parseQuery(query));
  }

  @Override
  public void processAccessToken(Map<String, String> params) {
    AccessToken token = buildAccessToken(params);
    userBean.setAccessToken(token);
    updateCharacterInfo();
    eventBus.post(AuthorizationEvent.AUTHORIZED);
  }

  @Override
  public HttpStatus processAuthorizationCode(Map<String, String> params) {
    RestResponse<AccessToken> response = authClient.getToken(params.get(CODE));

    if (response.isSuccessful()) {
      userBean.setAccessToken(response.getObject());
      updateCharacterInfo();
      eventBus.post(AuthorizationEvent.AUTHORIZED);
    }

    return response.getStatus();
  }

  @Override
  public void refreshAccessToken() {
    RestResponse<AccessToken> response = authClient.refreshToken();

    if (response.isSuccessful()) {
      userBean.setAccessToken(response.getObject());
      updateCharacterInfo();
      eventBus.post(AuthorizationEvent.REFRESHED);
    }
  }

  private void updateCharacterInfo() {
    RestResponse<CharacterInfo> characterInfo = authClient.getCharacterInfo(userBean.getAccessToken());
    if (characterInfo.isSuccessful()) userBean.setCharacterInfo(characterInfo.getObject());
  }


  @Override
  public HttpStatus processAuthorization(String query) {
    return processAuthorization(queryParser.parseQuery(query));
  }

  @Override
  public HttpStatus processAuthorization(List<NameValuePair> params) {
    Map<String, String> paramsMap = params.stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
    return processAuthorization(paramsMap);
  }

  @Override
  public HttpStatus processAuthorization(Map<String, String> params) {
    if (params.containsKey(CODE)) {
      return processAuthorizationCode(params);
    }
    else {
      processAccessToken(params);
      return HttpStatus.OK;
    }
  }

  @Override
  public AccessToken buildAccessToken(Map<String, String> params) {
    AccessToken accessToken = new AccessToken();

    accessToken.setAccess_token(params.get("access_token"));
    accessToken.setToken_type(params.get("token_type"));
    accessToken.setExpires_in(Long.parseLong(params.get("expires_in")));

    return accessToken;
  }

}
