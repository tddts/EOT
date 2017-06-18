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

package com.github.jdtk0x5d.eve.jet.config.spring.beans;

import com.github.jdtk0x5d.eve.jet.consts.AuthorizationType;
import com.github.jdtk0x5d.eve.jet.consts.RestDataSource;
import com.github.jdtk0x5d.eve.jet.model.client.esi.sso.AccessToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * {@code UserBean} is a bean that contains all User data required for authentication and authorization.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class UserBean {

  private final Logger logger = LogManager.getLogger(UserBean.class);

  private long character_id;
  private String clientId;
  private String sercretKey;
  private AccessToken accessToken;

  private AuthorizationType authorizationType = AuthorizationType.IMPLICIT;
  private RestDataSource restDataSource = RestDataSource.TRANQULITY;

  public UserBean() {
  }

  public boolean isTokenRefreshable() {
    return accessToken.getRefresh_token() != null;
  }

  public long getCharacter_id() {
    return character_id;
  }

  public void setCharacter_id(long character_id) {
    this.character_id = character_id;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getSercretKey() {
    return sercretKey;
  }

  public void setSercretKey(String sercretKey) {
    this.sercretKey = sercretKey;
  }

  public AccessToken getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(AccessToken accessToken) {
    logger.debug("New access token: " + accessToken);
    this.accessToken = accessToken;
  }

  public AuthorizationType getAuthorizationType() {
    return authorizationType;
  }

  public void setAuthorizationType(AuthorizationType authorizationType) {
    this.authorizationType = authorizationType;
  }

  public Logger getLogger() {
    return logger;
  }

  public RestDataSource getRestDataSource() {
    return restDataSource;
  }

  public void setRestDataSource(RestDataSource restDataSource) {
    this.restDataSource = restDataSource;
  }
}
