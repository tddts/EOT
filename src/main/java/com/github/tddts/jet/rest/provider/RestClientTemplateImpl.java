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

package com.github.tddts.jet.rest.provider;

import com.github.tddts.jet.config.spring.beans.UserBean;
import com.github.tddts.jet.consts.RestDataSource;
import com.github.tddts.jet.model.client.esi.sso.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class RestClientTemplateImpl implements RestClientTemplate {

  private static final String EMPTY_TOKEN = "";

  @Value("${url.root}")
  private String rootUrl;

  @Value("${url.auth}")
  private String authUrl;

  @Value("${header.authorization}")
  private String authHeader;

  @Value("${query.param.datasource}")
  private String dataSourceQueryParam;

  @Autowired
  private RestOperations restOperations;
  @Autowired
  private UserBean userBean;

  @Override
  public RestOperations restOperations() {
    return restOperations;
  }

  @Override
  public String apiUrl(String resourceAddress) {
    UriComponentsBuilder builder = apiUriBuilder(resourceAddress);
    return builder.build().toString();
  }

  @Override
  public UriComponentsBuilder apiUriBuilder(String resourceAddress) {
    return setDefaultParameters(UriComponentsBuilder.fromHttpUrl(rootUrl + resourceAddress));
  }


  @Override
  public UriComponentsBuilder authUriBuilder(String resourceAddress) {
    return UriComponentsBuilder.fromHttpUrl(authUrl + resourceAddress);
  }

  @Override
  public HttpEntity<?> jsonEntity(Object body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<>(body, headers);
  }

  @Override
  public HttpEntity<?> authorizedEntity() {
    return authorizedEntity(null);
  }

  @Override
  public HttpEntity<?> authorizedEntity(Object body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    AccessToken token = userBean.getAccessToken();
    String authValue = token == null ? EMPTY_TOKEN : token.getFullValue();
    headers.set(authHeader, authValue);
    return new HttpEntity<>(body, headers);
  }

  @Override
  public HttpEntity<?> basicAuthorizedEntity() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    String authHeaderValue = userBean.getClientId() + ":" + userBean.getSercretKey();
    String base64EncodedHeader = new String(Base64.getEncoder().encode(authHeaderValue.getBytes()), StandardCharsets.UTF_8);
    headers.set(authHeader, "Basic " + base64EncodedHeader);
    return new HttpEntity<>(null, headers);
  }

  @Override
  public HttpEntity<?> bearerAuthorizedEntity() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    AccessToken token = userBean.getAccessToken();
    String authValue = token == null ? EMPTY_TOKEN : token.getAccess_token();
    headers.set(authHeader, "Bearer " + authValue);
    return new HttpEntity<>(null, headers);
  }

  private UriComponentsBuilder setDefaultParameters(UriComponentsBuilder builder) {
    RestDataSource restDataSource = userBean.getRestDataSource();
    if (restDataSource != null) builder.queryParam(dataSourceQueryParam, restDataSource.getValue());
    return builder;
  }
}
