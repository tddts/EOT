package com.github.jdtk0x5d.eve.jet.util;

import javafx.scene.control.TextField;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class FxUtil {

  public static double getDouble(TextField textField){
    String text = textField.getText();
    return NumberUtils.toDouble(text);
  }

  public static long getLong(TextField textField){
    String text = textField.getText();
    return NumberUtils.toLong(text);
  }

  public static int getInt(TextField textField){
    String text = textField.getText();
    return NumberUtils.toInt(text);
  }

  public static double getPercent(TextField textField){
    return getDouble(textField) / 100;
  }

}
