package com.github.tddts.jet.view.fx.converter;

import javafx.util.converter.FloatStringConverter;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class FloatFormatStringConverter extends NumericFormatStringConverter<Float> {

  public FloatFormatStringConverter() {
    super(new FloatStringConverter(), "#.##");
  }
}
