package com.github.jdtk0x5d.eve.jet.view.fx.controls;

import javafx.util.converter.DoubleStringConverter;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DoubleTextField extends NumericTextField<Double> {

  public DoubleTextField() {
    super(new DoubleStringConverter(), 0d);
  }
}
