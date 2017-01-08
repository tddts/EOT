package com.github.jdtk0x5d.eve.jet.consts;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum OrderType {

  ALL("all"), BUY("buy"), SELL("sell");

  private final String value;

  OrderType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
