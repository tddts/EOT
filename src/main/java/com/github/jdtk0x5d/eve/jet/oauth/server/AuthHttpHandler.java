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

package com.github.jdtk0x5d.eve.jet.oauth.server;

import com.github.jdtk0x5d.eve.jet.service.AuthService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * {@code AuthHttpHandler} is a {@link HttpHandler} that processes redirected OAUTH 2,0 request.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class AuthHttpHandler implements HttpHandler {

  private static final Logger logger = LogManager.getLogger(AuthHttpHandler.class);

  private AuthService authService;
  private String responseText;
  private String successMessage;

  public AuthHttpHandler(AuthService authService, String responseText, String successMessage) {
    this.authService = authService;
    this.responseText = responseText;
    this.successMessage = successMessage;
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {

    String response = "";
    int status = HttpStatus.OK.value();

    try {

      URI requestURI = exchange.getRequestURI();

      logger.debug("Request URI:[" + requestURI + "]");
      logger.debug("Query: " + requestURI.getQuery());

      if (exchange.getRequestMethod().equals(RequestMethod.GET.name())) {

        response = successMessage;

        if (requestURI.getQuery() == null) {
          logger.debug("Query is null.");
          response = responseText;
        }
        else {
          logger.debug("Query is not null.");
          HttpStatus httpStatus = authService.processAuthorization(requestURI.getQuery());
          response = httpStatus.is2xxSuccessful() ? response : httpStatus.getReasonPhrase();
          status = httpStatus.value();
        }
      }

    } catch (Throwable e) {

      status = HttpStatus.INTERNAL_SERVER_ERROR.value();

    } finally {

      exchange.sendResponseHeaders(status, 0);

      logger.debug("Response: \n" + response + "");

      try (OutputStream os = exchange.getResponseBody()) {
        os.write(response.getBytes());
        os.flush();
      }

      logger.debug("Response stream closed.");
    }
  }
}
