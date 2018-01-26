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

package com.github.tddts.jet.oauth.impl;

import com.github.tddts.jet.config.spring.annotations.LoadContent;
import com.github.tddts.jet.config.spring.annotations.Message;
import com.github.tddts.jet.oauth.EmbeddedServer;
import com.github.tddts.jet.oauth.server.DefaultAuthHandler;
import com.github.tddts.jet.service.AuthService;
import com.github.tddts.tools.web.oauth.handler.AuthHandler;
import com.github.tddts.tools.web.oauth.handler.impl.CallbackRequestHandler;
import com.github.tddts.tools.web.oauth.handler.impl.SimpleAuthHandlerCallback;
import com.github.tddts.tools.web.oauth.server.TemporaryHttpServer;
import com.github.tddts.tools.web.oauth.server.TemporaryServer;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class EmbeddedServerImpl implements EmbeddedServer {

  private final Logger logger = LogManager.getLogger(EmbeddedServerImpl.class);

  @Value("${server.path}")
  private String serverPath;

  @Value("${server.port}")
  private int port;

  @Value("${server.timeout}")
  private long timeout;

  @LoadContent("/web/login.html")
  private String implicitSuccessResponse;

  @Message("login.success")
  private String successMessage;

  @Autowired
  private AuthService authService;

  private TemporaryServer temporaryServer;

  @PostConstruct
  public void init() {
    AuthHandler authHandler = new DefaultAuthHandler(authService, implicitSuccessResponse, successMessage);
    CallbackRequestHandler requestHandler = new CallbackRequestHandler(authHandler, new SimpleAuthHandlerCallback());

    HttpServer httpServer = ServerBootstrap.bootstrap()
        .setListenerPort(port)
        .registerHandler(serverPath, requestHandler)
        .create();

    temporaryServer = new TemporaryHttpServer(httpServer);
  }


  @Override
  public void start() {
    temporaryServer.start();
  }

  @Override
  public void stop() {
    temporaryServer.stop();
  }

}
