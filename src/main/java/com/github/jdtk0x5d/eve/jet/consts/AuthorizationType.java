package com.github.jdtk0x5d.eve.jet.consts;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum AuthorizationType {

  IMPLICIT("auth.type.implicit"),
  DEV("auth.type.dev");

  private final String key;

  AuthorizationType(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public boolean isImplicit() {
    return this == IMPLICIT;
  }

  public boolean isDev() {
    return this == DEV;
  }
}
