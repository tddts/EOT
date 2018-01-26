package com.github.tddts.jet.view.fx.converter;

import javafx.util.converter.IntegerStringConverter;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class IntegerFormatStringConverter extends NumericFormatStringConverter<Integer>{

  public IntegerFormatStringConverter() {
    super(new IntegerStringConverter(), "#");
  }
}
