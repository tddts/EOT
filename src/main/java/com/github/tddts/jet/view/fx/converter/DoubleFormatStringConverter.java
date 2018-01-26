package com.github.tddts.jet.view.fx.converter;

import javafx.util.converter.DoubleStringConverter;

import java.text.DecimalFormat;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DoubleFormatStringConverter extends NumericFormatStringConverter<Double> {

  public DoubleFormatStringConverter() {
    super(new DoubleStringConverter(), "#.####");
  }
}
