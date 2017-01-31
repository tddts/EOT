package com.github.jdtk0x5d.eve.jet.service.impl;

import com.github.jdtk0x5d.eve.jet.api.RestResponse;
import com.github.jdtk0x5d.eve.jet.config.spring.beans.UserBean;
import com.github.jdtk0x5d.eve.jet.context.events.AuthorizationEvent;
import com.github.jdtk0x5d.eve.jet.model.api.esi.sso.AccessToken;
import com.github.jdtk0x5d.eve.jet.oauth.QueryParser;
import com.github.jdtk0x5d.eve.jet.rest.api.esi.AuthAPI;
import com.github.jdtk0x5d.eve.jet.service.AuthService;
import com.google.common.eventbus.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Service
public class AuthServiceImpl implements AuthService {

  private static final String CODE = "code";
  private static final String TOKEN = "token";

  private final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

  @Value("${login.url.authorize}")
  private String loginAuthURL;

  @Value("${login.redirect.uri}")
  private String redirectURI;

  @Value("${login.realm}")
  private String realm;

  @Value("${login.client.id}")
  private String defaultClientId;

  @Value("${login.scopes}")
  private String scopes;

  @Value("${login.state}")
  private String state;

  @Autowired
  private QueryParser queryParser;

  @Autowired
  private UserBean userBean;

  @Autowired
  private EventBus eventBus;

  @Autowired
  private AuthAPI authAPI;


  @Override
  public String getLoginPage(String clientId) {
    return getLoginPageURI(clientId).toString();
  }

  @Override
  public URI getLoginPageURI(String clientId) {
    return getLoginPageURI(clientId, CODE);
  }

  @Override
  public String getLoginPage() {
    return getLoginPageURI().toString();
  }

  @Override
  public URI getLoginPageURI() {
    return getLoginPageURI(defaultClientId, TOKEN);
  }

  private URI getLoginPageURI(String clientId, String responseType) {
    return UriComponentsBuilder.fromHttpUrl(loginAuthURL)
        .queryParam("response_type", responseType)
        .queryParam("redirect_uri", redirectURI)
        .queryParam("realm", realm)
        .queryParam("client_id", clientId)
        .queryParam("scope", scopes)
        .queryParam("state", state)
        .build().toUri();
  }

  @Override
  public void processAccessToken(String query) {
    AccessToken token = queryParser.parseAccessToken(query);
    userBean.setAccessToken(token);
    eventBus.post(AuthorizationEvent.AUTHORIZED);
  }

  @Override
  public HttpStatus processAuthorizationCode(String query) {
    RestResponse<AccessToken> response = authAPI.getToken(queryParser.parseAuthCode(query));

    if (response.isSuccessful()) {
      userBean.setAccessToken(response.getObject());
      eventBus.post(AuthorizationEvent.AUTHORIZED);
    }

    return response.getStatus();
  }

  @Override
  public void refreshAccessToken() {
    RestResponse<AccessToken> response = authAPI.refreshToken();

    if (response.isSuccessful()) {
      userBean.setAccessToken(response.getObject());
      eventBus.post(AuthorizationEvent.REFRESHED);
    }
  }


  @Override
  public HttpStatus processAuthorization(String query) {
    if (query.startsWith(CODE)) {
      return processAuthorizationCode(query);
    }
    else {
      processAccessToken(query);
      return HttpStatus.OK;
    }
  }

}
