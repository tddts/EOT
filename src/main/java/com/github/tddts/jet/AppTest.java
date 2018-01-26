package com.github.tddts.jet;

import java.text.DecimalFormat;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class AppTest {

  public static void main(String[] args) {
    DecimalFormat decimalFormat = new DecimalFormat("#%");
    float value = 0.1f;
    System.out.println(decimalFormat.format(value));
    System.out.println(value);
  }
}
