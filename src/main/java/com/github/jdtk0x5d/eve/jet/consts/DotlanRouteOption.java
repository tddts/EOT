package com.github.jdtk0x5d.eve.jet.consts;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum DotlanRouteOption {

  FASTEST("", -1),
  HIGH_SEC("2", 0.5),
  LOW_SEC("3", 0);

  private final String value;
  private final double security;

  DotlanRouteOption(String value, double security) {
    this.value = value;
    this.security = security;
  }

  public String getValue() {
    return value;
  }

  public double getSecurity() {
    return security;
  }
}
