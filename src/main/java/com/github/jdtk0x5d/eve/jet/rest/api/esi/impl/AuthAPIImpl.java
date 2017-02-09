package com.github.jdtk0x5d.eve.jet.rest.api.esi.impl;

import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.RestApi;
import com.github.jdtk0x5d.eve.jet.config.spring.beans.UserBean;
import com.github.jdtk0x5d.eve.jet.model.api.esi.sso.AccessToken;
import com.github.jdtk0x5d.eve.jet.rest.api.esi.AuthAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Base64;

import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.authUriBuilder;
import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.restOperations;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestApi
public class AuthAPIImpl implements AuthAPI {

  @Value("${url.auth.authorize}")
  private String addressAuthorize;

  @Value("${url.auth.token}")
  private String addressToken;

  @Autowired
  private UserBean userBean;

  @Override
  public RestResponse<AccessToken> getToken(String authCode) {
    URI uri = authUriBuilder(addressAuthorize)
        .queryParam("grant_type", "authorization_code")
        .queryParam("code", authCode)
        .build().toUri();

    return getToken(uri);
  }

  @Override
  public RestResponse<AccessToken> refreshToken() {
    URI uri = authUriBuilder(addressToken)
        .queryParam("grant_type", "refresh_token")
        .queryParam("refresh_token", userBean.getAccessToken().getRefresh_token())
        .build().toUri();

    return getToken(uri);
  }

  private RestResponse<AccessToken> getToken(URI uri) {
    return new RestResponse<>(restOperations().exchange(uri, HttpMethod.POST, basicAuthEntity(), AccessToken.class));
  }

  private HttpEntity<?> basicAuthEntity() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    String authHeader = userBean.getClientId() + ":" + userBean.getSercretKey();
    String base64EncodedHeader = new String(Base64.getEncoder().encode(authHeader.getBytes()), Charset.forName("UTF-8"));
    headers.set("Authorization", "Basic " + base64EncodedHeader);
    return new HttpEntity<>(null, headers);
  }
}
