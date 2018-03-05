package com.github.tddts.jet.consts;

import com.github.tddts.jet.util.Util;

/**
 * {@code SecurityLevel} represents a solar system security level with corresponding HTML color code.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum SecurityLevel {

  LEVEL_00(0.0f, "#F00000"),
  LEVEL_01(0.1f, "#D73000"),
  LEVEL_02(0.2f, "#F04800"),
  LEVEL_03(0.3f, "#F06000"),
  LEVEL_04(0.4f, "#D77700"),
  LEVEL_05(0.5f, "#EFEF00"),
  LEVEL_06(0.6f, "#8FEF2F"),
  LEVEL_07(0.7f, "#00F000"),
  LEVEL_08(0.8f, "#00EF47"),
  LEVEL_09(0.9f, "#48F0C0"),
  LEVEL_10(1.0f, "#2FEFEF"),;


  private final float value; // Numeric value.
  private final String color; // HTML color code.

  SecurityLevel(float value, String color) {
    this.value = value;
    this.color = color;
  }

  /**
   * Get numeric value of security level.
   *
   * @return security level numeric value;
   */
  public float getValue() {
    return value;
  }

  /**
   * Get security level HTML color code.
   *
   * @return HTML color code of a security level.
   */
  public String getColor() {
    return color;
  }

  /**
   * Get security level object for given numeric value.
   *
   * @param value security level numeric value.
   * @return security level for given numeric value or <b>null</b>.
   */
  public static SecurityLevel fromValue(float value) {
    value = (float) Util.round(value, 1);
    for (SecurityLevel level : values()) {
      if (Float.compare(value, level.value) == 0) return level;
    }
    return null;
  }

}
