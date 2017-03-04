package com.github.jdtk0x5d.eve.jet.fx.controls;

import javafx.util.converter.IntegerStringConverter;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class IntegerTextField extends NumericTextField<Integer> {

  public IntegerTextField() {
    super(new IntegerStringConverter(), 0);
  }
}
