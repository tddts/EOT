package com.github.jdtk0x5d.eve.jet.oauth.impl;

import com.github.jdtk0x5d.eve.jet.config.spring.beans.UserBean;
import com.github.jdtk0x5d.eve.jet.context.events.AuthorizationEvent;
import com.github.jdtk0x5d.eve.jet.service.AuthService;
import com.github.jdtk0x5d.eve.jet.oauth.SessionDaemon;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@Scope("singleton")
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

  private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

  @Subscribe
  private void processAuthorizationEvent(AuthorizationEvent event) {
    if (event.isAuthorized() || event.isRefreshed()) {
      if (userBean.isTokenRefreshable()) {
        scheduleRefresh();
      } else {
        scheduleExpiration();
      }
    }
  }

  private void scheduleExpiration() {
    logger.debug("Scheduling authorization expiration task.");
    executor.schedule(this::fireExpirationEvent, getRefreshDelay(expireRate), TimeUnit.SECONDS);
  }

  private void scheduleRefresh() {
    logger.debug("Scheduling token refresh task.");
    executor.schedule(this::refreshToken, getRefreshDelay(refreshRate), TimeUnit.SECONDS);
  }

  private void fireExpirationEvent() {
    eventBus.post(AuthorizationEvent.EXPIRED);
  }

  private void refreshToken() {
    logger.debug("Refreshing token...");
    authService.refreshAccessToken(userBean.getAccessToken().getRefresh_token(), userBean.getClientId(), userBean.getSercretKey());
  }

  private long getRefreshDelay(double rate) {
    return (long) (userBean.getAccessToken().getExpires_in() * rate);
  }
}
