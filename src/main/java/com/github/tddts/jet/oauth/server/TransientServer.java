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

package com.github.tddts.jet.oauth.server;

import com.github.tddts.jet.exception.ServerCreationException;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * {@code TransientServer} represents a server that can be started for a short period of time just to receive
 * OAUTH 2.0 requests.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class TransientServer {

  private final Logger logger = LogManager.getLogger(TransientServer.class);

  private HttpServer server;
  private int stopDelay;
  private long timeout;
  private boolean started;

  private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
  private Future<?> serverTimeoutTaskFuture;


  public TransientServer(String resourceLocation, int port, long timeout, HttpHandler httpHandler) {
    this.timeout = timeout;
    try {

      server = HttpServer.create(new InetSocketAddress(port), 0);
      server.createContext(resourceLocation, httpHandler);
      server.setExecutor(Executors.newSingleThreadExecutor());

    } catch (IOException e) {
      throw new ServerCreationException(e);
    }
  }

  public void start() {
    if (started) {
      serverTimeoutTaskFuture.cancel(false);
      scheduleServerTimeoutStop();
      logger.debug("Server timeout refreshed.");
    }
    else {
      server.start();
      scheduleServerTimeoutStop();
      started = true;
      logger.debug("Server started.");
    }
  }

  public void stop() {
    server.stop(stopDelay);
    started = false;
    logger.debug("Server stopped.");
  }

  public void setStopDelay(int stopDelay) {
    this.stopDelay = stopDelay;
  }

  private void scheduleServerTimeoutStop() {
    serverTimeoutTaskFuture = executor.schedule(this::stopByTimeout, timeout, TimeUnit.SECONDS);
  }

  private void stopByTimeout() {
    logger.debug("Timeout expired.");
    stop();
  }
}
