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

package com.github.tddts.evetrader.oauth.impl;

import com.github.tddts.evetrader.config.spring.beans.UserBean;
import com.github.tddts.evetrader.context.events.AuthorizationEvent;
import com.github.tddts.evetrader.oauth.SessionDaemon;
import com.github.tddts.evetrader.service.AuthService;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class SessionDaemonImpl implements SessionDaemon {

  private final Logger logger = LogManager.getLogger(SessionDaemonImpl.class);

  @Value("${session.daemon.refresh.rate}")
  private double refreshRate;

  @Value("${session.daemon.expire.rate}")
  private double expireRate;

  @Autowired
  private UserBean userBean;

  @Autowired
  private AuthService authService;

  @Autowired
  private EventBus eventBus;

  private ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

  private Future<?> expirationFuture;
  private Future<?> refreshFuture;

  @Subscribe
  private void processAuthorizationEvent(AuthorizationEvent event) {
    if (event.isAuthorized() || event.isRefreshed()) {
      if (userBean.isTokenRefreshable()) scheduleRefresh();
      scheduleExpiration();
    }
  }

  private void scheduleExpiration() {
    logger.debug("Scheduling authorization expiration task.");
    checkCancel(expirationFuture);
    expirationFuture = executor.schedule(this::fireExpirationEvent, getRefreshDelay(expireRate), TimeUnit.SECONDS);
  }

  private void scheduleRefresh() {
    logger.debug("Scheduling token refresh task.");
    checkCancel(refreshFuture);
    refreshFuture = executor.schedule(this::refreshToken, getRefreshDelay(refreshRate), TimeUnit.SECONDS);
  }

  private void fireExpirationEvent() {
    eventBus.post(AuthorizationEvent.EXPIRED);
  }

  private void refreshToken() {
    logger.debug("Refreshing token...");
    authService.refreshAccessToken();
  }

  private long getRefreshDelay(double rate) {
    return (long) (userBean.getAccessToken().getExpires_in() * rate);
  }

  private void checkCancel(Future<?> future) {
    if (future != null && !future.isDone()) {
      future.cancel(true);
    }
  }
}
