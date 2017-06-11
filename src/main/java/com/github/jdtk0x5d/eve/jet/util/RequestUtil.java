package com.github.jdtk0x5d.eve.jet.util;

import com.github.jdtk0x5d.eve.jet.consts.RestDataSource;
import com.github.jdtk0x5d.eve.jet.context.Context;
import com.github.jdtk0x5d.eve.jet.model.client.esi.sso.AccessToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    String URL = Context.getAppProperties().getProperty("path.swagger") + resourceAddress;
    return setDefaultParameters(UriComponentsBuilder.fromHttpUrl(URL));
  }


  public static UriComponentsBuilder authUriBuilder(String resourceAddress) {
    return UriComponentsBuilder.fromHttpUrl(Context.getAppProperties().getProperty("path.auth=") + resourceAddress);
  }

  public static HttpEntity<?> jsonEntity(Object body) {
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
    AccessToken token = Context.getUserBean().getAccessToken();
    String authValue = token == null ? "" : token.getFullValue();
    headers.set("Authorization", authValue);
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
