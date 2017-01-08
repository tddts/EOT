package com.github.jdtk0x5d.eve.jet.config.spring.beans;

import com.github.jdtk0x5d.eve.jet.consts.AuthorizationType;
import com.github.jdtk0x5d.eve.jet.consts.RestDataSource;
import com.github.jdtk0x5d.eve.jet.model.api.esi.sso.AccessToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@Scope("singleton")
public class UserBean {

  private final Logger logger = LogManager.getLogger(UserBean.class);

  private long character_id;
  private String clientId;
  private String sercretKey;
  private AccessToken accessToken;
  private AuthorizationType authorizationType = AuthorizationType.IMPLICIT;
  private RestDataSource restDataSource = RestDataSource.TRANQULITY;

  public UserBean() {
  }

  public boolean isTokenRefreshable(){
    return accessToken.getRefresh_token() != null;
  }

  public long getCharacter_id() {
    return character_id;
  }

  public void setCharacter_id(long character_id) {
    this.character_id = character_id;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getSercretKey() {
    return sercretKey;
  }

  public void setSercretKey(String sercretKey) {
    this.sercretKey = sercretKey;
  }

  public AccessToken getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(AccessToken accessToken) {
    logger.debug("New access token: " + accessToken);
    this.accessToken = accessToken;
  }

  public AuthorizationType getAuthorizationType() {
    return authorizationType;
  }

  public void setAuthorizationType(AuthorizationType authorizationType) {
    this.authorizationType = authorizationType;
  }

  public Logger getLogger() {
    return logger;
  }

  public RestDataSource getRestDataSource() {
    return restDataSource;
  }

  public void setRestDataSource(RestDataSource restDataSource) {
    this.restDataSource = restDataSource;
  }
}
