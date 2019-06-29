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

package com.github.tddts.evetrader.oauth.server;

import com.github.tddts.evetrader.service.AuthService;
import com.github.tddts.tools.web.oauth.handler.AuthHandler;
import com.github.tddts.tools.web.oauth.handler.AuthHandlerCallback;
import org.apache.http.NameValuePair;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DefaultAuthHandler implements AuthHandler {

  private AuthService authService;
  private String responseText;
  private String successMessage;

  public DefaultAuthHandler(AuthService authService, String responseText, String successMessage) {
    this.authService = authService;
    this.responseText = responseText;
    this.successMessage = successMessage;
  }

  @Override
  public void process(AuthHandlerCallback callback, List<NameValuePair> params) {
    if (params.isEmpty()) {
      callback.returnMessage(responseText);
    }
    else {
      HttpStatus status = authService.processAuthorization(params);
      String message = status.is2xxSuccessful() ? successMessage : status.getReasonPhrase();
      callback.returnMessage(status.value(), message);
    }
  }
}
