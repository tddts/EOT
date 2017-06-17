package com.github.jdtk0x5d.eve.jet.view.fx.controls;

import javafx.util.converter.FloatStringConverter;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class FloatTextField extends NumericTextField<Float> {

  public FloatTextField() {
    super(new FloatStringConverter(), 0f);
  }
}
