package com.github.jdtk0x5d.eve.jet.context.events;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum AuthorizationEvent {

  AUTHORIZED, REFRESHED, EXPIRED;

  public boolean isAuthorized() {
    return this == AUTHORIZED;
  }

  public boolean isExpired() {
    return this == REFRESHED;
  }

  public boolean isRefreshed() {
    return this == EXPIRED;
  }
}
