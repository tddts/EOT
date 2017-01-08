package com.github.jdtk0x5d.eve.jet.consts;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum DotlanRouteOption {

  FASTEST(""), HIGH_SEC("2"), LOW_SEC("3");

  private final String value;

  DotlanRouteOption(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
