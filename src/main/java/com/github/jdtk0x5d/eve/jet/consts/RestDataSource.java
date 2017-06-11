package com.github.jdtk0x5d.eve.jet.consts;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum RestDataSource {

  TRANQULITY("tranquility", "Tranquility"),

  SINGULARITY("singularity", "Singularity"),

  NONE("", "None");

  private final String value;
  private final String textValue;

  RestDataSource(String value, String textValue) {
    this.value = value;
    this.textValue = textValue;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return textValue;
  }
}
