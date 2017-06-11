package com.github.jdtk0x5d.eve.jet.consts;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum AuthorizationType {

  IMPLICIT,
  DEV;

  public boolean isImplicit() {
    return this == IMPLICIT;
  }

  public boolean isDev() {
    return this == DEV;
  }
}
