package com.github.tddts.jet.view.fx.converter;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class PercentageFormatStringConverter extends FloatFormatStringConverter {

  public PercentageFormatStringConverter() {
    setPattern("#%");
  }

  @Override
  public Float fromString(String value) {
    // If the specified value is null or zero-length, return null
    if (value == null) {
      return null;
    }

    value = value.trim();

    if (value.length() < 1) {
      return null;
    }

    return Float.valueOf(value) / 100;
  }
}
