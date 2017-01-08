package com.github.jdtk0x5d.eve.jet.service.impl;

import com.github.jdtk0x5d.eve.jet.config.spring.beans.UserBean;
import com.github.jdtk0x5d.eve.jet.context.events.AuthorizationEvent;
import com.github.jdtk0x5d.eve.jet.model.api.esi.sso.AccessToken;
import com.github.jdtk0x5d.eve.jet.oauth.QueryParser;
import com.github.jdtk0x5d.eve.jet.service.AuthService;
import com.github.jdtk0x5d.eve.jet.util.RequestUtil;
import com.google.common.eventbus.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Service
public class AuthServiceImpl implements AuthService {

  private final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

  @Value("${login.url.authorize}")
  private String loginAuthURL;

  @Value("${login.url.token}")
  private String loginTokenURL;

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


  @Override
  public String getLoginPage(String clientId) {
    return getLoginPageURI(clientId).toString();
  }

  @Override
  public URI getLoginPageURI(String clientId) {
    return getLoginPageURI(clientId, "code");
  }

  @Override
  public String getLoginPage() {
    return getLoginPageURI().toString();
  }

  @Override
  public URI getLoginPageURI() {
    return getLoginPageURI(defaultClientId, "token");
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
  public void processAuthorizationCode(String query, String clientId, String secretKey) {
    URI uri = UriComponentsBuilder.fromHttpUrl(loginTokenURL)
        .queryParam("grant_type", "authorization_code")
        .queryParam("code", queryParser.parseAuthCode(query))
        .build().toUri();
    // Get first token
    AccessToken token = RequestUtil.restOperations()
        .exchange(uri, HttpMethod.POST, new HttpEntity<>(null, getBasicAuthHeader(clientId, secretKey)), AccessToken.class).getBody();
    // Save access token
    userBean.setAccessToken(token);
    // Fire authorization event
    eventBus.post(AuthorizationEvent.AUTHORIZED);

  }

  @Override
  public void refreshAccessToken(String refreshToken, String clientId, String secretKey) {
    URI uri = UriComponentsBuilder.fromHttpUrl(loginTokenURL)
        .queryParam("grant_type", "refresh_token")
        .queryParam("refresh_token", refreshToken)
        .build().toUri();
    // Get new token
    AccessToken token = RequestUtil.restOperations()
        .exchange(uri, HttpMethod.POST, new HttpEntity<>(null, getBasicAuthHeader(clientId, secretKey)), AccessToken.class).getBody();
    // Save access token
    userBean.setAccessToken(token);
    // Fire authorization event
    eventBus.post(AuthorizationEvent.REFRESHED);
  }

  @Override
  public void processAuthorization(String query) {
    if (query.startsWith("code")) {
      processAuthorizationCode(query, userBean.getClientId(), userBean.getSercretKey());
    } else {
      processAccessToken(query);
    }
  }

  private HttpHeaders getBasicAuthHeader(String clientId, String secretKey) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    String authHeader = clientId + ":" + secretKey;
    String base64EncodedHeader = new String(Base64.getEncoder().encode(authHeader.getBytes()), Charset.forName("UTF-8"));
    headers.set("Authorization", "Basic " + base64EncodedHeader);
    return headers;
  }
}
