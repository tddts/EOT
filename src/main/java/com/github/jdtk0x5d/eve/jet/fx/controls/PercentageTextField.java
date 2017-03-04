package com.github.jdtk0x5d.eve.jet.fx.controls;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class PercentageTextField extends IntegerTextField {

  public double getFraction(){
    return getNumber().doubleValue() / 100;
  }
}
