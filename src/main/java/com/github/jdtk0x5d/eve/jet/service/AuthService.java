package com.github.jdtk0x5d.eve.jet.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;

import java.net.URI;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface AuthService {

  String getLoginPage();

  URI getLoginPageURI();

  String getLoginPage(String clientId);

  URI getLoginPageURI(String clientId);

  void processAccessToken(String query);

  HttpStatus processAuthorizationCode(String query) throws RestClientException;

  void refreshAccessToken() throws RestClientException;

  HttpStatus processAuthorization(String query) throws RestClientException;
}
