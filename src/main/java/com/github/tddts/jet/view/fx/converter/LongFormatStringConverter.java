package com.github.tddts.jet.view.fx.converter;

import javafx.util.converter.LongStringConverter;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class LongFormatStringConverter extends NumericFormatStringConverter<Long> {

  public LongFormatStringConverter() {
    super(new LongStringConverter(), "#");
  }
}
