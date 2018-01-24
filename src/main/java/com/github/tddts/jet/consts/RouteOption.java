package com.github.tddts.jet.consts;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum RouteOption {

  SHORTEST(-1, "shortest"),

  SECURE(0.5, "secure"),

  INSECURE(-1, "insecure");

  private double security;
  private String value;

  RouteOption(double security, String value) {
    this.security = security;
    this.value = value;
  }

  public double getSecurity() {
    return security;
  }

  public String getValue() {
    return value;
  }
}
