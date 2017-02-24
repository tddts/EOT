package com.github.jdtk0x5d.eve.jet.consts;

import com.github.jdtk0x5d.eve.jet.fx.tools.message.MessageAware;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum AuthorizationType implements MessageAware{

  IMPLICIT("enum.auth.type.implicit"),
  DEV("enum.auth.type.dev");

  private final String key;

  AuthorizationType(String key) {
    this.key = key;
  }

  public String getMessageKey() {
    return key;
  }

  public boolean isImplicit() {
    return this == IMPLICIT;
  }

  public boolean isDev() {
    return this == DEV;
  }
}
