package com.github.jdtk0x5d.eve.jet.rest.provider;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface RestClientProvider {

  RestOperations restOperations();

  String apiUrl(String resourceAddress);

  UriComponentsBuilder apiUriBuilder(String resourceAddress);

  UriComponentsBuilder authUriBuilder(String resourceAddress);

  HttpEntity<?> jsonEntity(Object body);

  HttpEntity<?> authorizedEntity();

  HttpEntity<?> authorizedEntity(Object body);
}
