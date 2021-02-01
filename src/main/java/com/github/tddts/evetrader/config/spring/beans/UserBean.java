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

package com.github.tddts.evetrader.config.spring.beans;

import com.github.tddts.evetrader.consts.AuthorizationType;
import com.github.tddts.evetrader.consts.RestDataSource;
import com.github.tddts.evetrader.model.client.esi.sso.AccessToken;
import com.github.tddts.evetrader.model.client.esi.sso.CharacterInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * {@code UserBean} is a bean that contains all User data required for authentication and authorization.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@Getter
@Setter
public class UserBean {

  private String clientId;
  private String sercretKey;
  private AccessToken accessToken;
  private CharacterInfo characterInfo;

  private AuthorizationType authorizationType = AuthorizationType.IMPLICIT;
  private RestDataSource restDataSource = RestDataSource.TRANQULITY;

  public UserBean() {
  }

  public boolean isTokenRefreshable() {
    return accessToken.getRefresh_token() != null;
  }
}
