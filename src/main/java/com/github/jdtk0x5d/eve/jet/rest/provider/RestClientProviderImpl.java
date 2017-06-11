package com.github.jdtk0x5d.eve.jet.rest.provider;

import com.github.jdtk0x5d.eve.jet.config.spring.beans.UserBean;
import com.github.jdtk0x5d.eve.jet.consts.RestDataSource;
import com.github.jdtk0x5d.eve.jet.context.Context;
import com.github.jdtk0x5d.eve.jet.model.client.esi.sso.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class RestClientProviderImpl implements RestClientProvider {

  private static final String EMPTY_TOKEN = "";

  @Value("${url.root}")
  private String rootUrl;

  @Value("${url.auth}")
  private String authUrl;

  @Value("${header.authorization}")
  private String authHeader;

  @Value("${query.param.datasource}")
  private String dataSourceQueryParam;

  @Autowired
  private RestOperations restOperations;
  @Autowired
  private UserBean userBean;

  @Override
  public RestOperations restOperations() {
    return restOperations;
  }

  @Override
  public String apiUrl(String resourceAddress) {
    UriComponentsBuilder builder = apiUriBuilder(resourceAddress);
    return builder.build().toString();
  }

  @Override
  public UriComponentsBuilder apiUriBuilder(String resourceAddress) {
    return setDefaultParameters(UriComponentsBuilder.fromHttpUrl(rootUrl + resourceAddress));
  }


  @Override
  public UriComponentsBuilder authUriBuilder(String resourceAddress) {
    return UriComponentsBuilder.fromHttpUrl(authUrl + resourceAddress);
  }

  @Override
  public HttpEntity<?> jsonEntity(Object body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<>(body, headers);
  }

  @Override
  public HttpEntity<?> authorizedEntity() {
    return authorizedEntity(null);
  }

  @Override
  public HttpEntity<?> authorizedEntity(Object body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    AccessToken token = userBean.getAccessToken();
    String authValue = token == null ? EMPTY_TOKEN : token.getFullValue();
    headers.set(authHeader, authValue);
    return new HttpEntity<>(body, headers);
  }

  private UriComponentsBuilder setDefaultParameters(UriComponentsBuilder builder) {
    RestDataSource restDataSource = userBean.getRestDataSource();
    if (restDataSource != null) builder.queryParam(dataSourceQueryParam, restDataSource.getValue());
    return builder;
  }
}
