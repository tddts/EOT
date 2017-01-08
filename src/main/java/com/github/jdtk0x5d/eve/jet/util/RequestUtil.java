package com.github.jdtk0x5d.eve.jet.util;

import com.github.jdtk0x5d.eve.jet.context.Context;
import com.github.jdtk0x5d.eve.jet.consts.RestDataSource;
import org.springframework.http.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class RequestUtil {

  private static final RestOperations REST_OPERATIONS;

  static {
    REST_OPERATIONS = Context.getBean(RestOperations.class);
  }

  public static RestOperations restOperations() {
    return REST_OPERATIONS;
  }

  public static String apiUrl(String resourceAddress) {
    UriComponentsBuilder builder = apiUriBuilder(resourceAddress);
    return builder.build().toString();
  }

  public static UriComponentsBuilder apiUriBuilder(String resourceAddress) {
    String URL = Context.getPropertyHolder().getProperty("url.swagger") + resourceAddress;
    return setDefaultParameters(UriComponentsBuilder.fromHttpUrl(URL));
  }

  public static HttpEntity<?> jsonEntity(Object body){
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<>(body, headers);
  }

  public static HttpEntity<?> authorizedEntity() {
    return authorizedEntity(null);
  }

  public static HttpEntity<?> authorizedEntity(Object body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", Context.getUserBean().getAccessToken().getFullValue());
    return new HttpEntity<>(body, headers);
  }

  private static UriComponentsBuilder setDefaultParameters(UriComponentsBuilder builder) {
    RestDataSource restDataSource = Context.getUserBean().getRestDataSource();
    if (!RestDataSource.NONE.equals(restDataSource)) {
      builder.queryParam("datasource", restDataSource.getValue());
    }
    return builder;
  }

}
