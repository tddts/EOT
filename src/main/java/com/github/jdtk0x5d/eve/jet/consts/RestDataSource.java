package com.github.jdtk0x5d.eve.jet.consts;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum RestDataSource {

  TRANQULITY("tranquility"),

  SINGULARITY("singularity");

  private final String value;

  RestDataSource(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
