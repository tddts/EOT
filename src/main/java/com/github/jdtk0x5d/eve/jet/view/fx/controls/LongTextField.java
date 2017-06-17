package com.github.jdtk0x5d.eve.jet.view.fx.controls;

import javafx.util.converter.LongStringConverter;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class LongTextField extends NumericTextField<Long> {

  public LongTextField() {
    super(new LongStringConverter(), 0L);
  }
}
