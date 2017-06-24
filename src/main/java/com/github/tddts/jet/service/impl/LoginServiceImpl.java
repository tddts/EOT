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
import com.github.tddts.jet.consts.AuthorizationType;
import com.github.tddts.jet.consts.RestDataSource;
import com.github.tddts.jet.oauth.EmbeddedServer;
import com.github.tddts.jet.service.AuthService;
import com.github.tddts.jet.service.LoginService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class LoginServiceImpl implements LoginService {

  @Autowired
  private UserBean userBean;
  @Autowired
  private AuthService authService;
  @Autowired
  private EmbeddedServer server;


  @Override
  public void processLogin(Supplier<Optional<Pair<String, String>>> credentialsSupplier, Consumer<URI> loginUriConsumer) {
    AuthorizationType authType = userBean.getAuthorizationType();

    server.start();

    if (authType.isImplicit()) {
      loginUriConsumer.accept(authService.getLoginPageURI());
    }

    if (authType.isDev()) {
      Optional<Pair<String, String>> credentialsPair = credentialsSupplier.get();

      if (credentialsPair.isPresent()) {
        Pair<String, String> credentials = credentialsPair.get();
        userBean.setClientId(credentials.getKey());
        userBean.setSercretKey(credentials.getValue());

        loginUriConsumer.accept(authService.getLoginPageURI(userBean.getClientId()));
      }
    }
  }

  @Override
  public void processLoginTypeChange(AuthorizationType value) {
    userBean.setAuthorizationType(value);
  }

  @Override
  public void processDataSourceChange(RestDataSource restDataSource) {
    userBean.setRestDataSource(restDataSource);
  }
}
