package com.github.tddts.jet;

import java.text.DecimalFormat;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class AppTest {

  public static void main(String[] args) {
    DecimalFormat decimalFormat = new DecimalFormat("#.#");
    double value = 102222220.333d;
    System.out.println(decimalFormat.format(value));
  }
}
