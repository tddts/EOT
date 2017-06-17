package com.github.jdtk0x5d.eve.jet.view.fx.controls;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public abstract class NumericTextField<T extends Number> extends TextField {

  private StringConverter<T> converter;
  private T defaultValue;

  public NumericTextField(StringConverter<T> converter, T defaultValue) {
    this.converter = converter;
    this.defaultValue = defaultValue;
    setTextFormatter(new TextFormatter<>(converter));
  }

  public T getNumber() {
    String text = getText();
    if (text == null || text.isEmpty()) return defaultValue;
    return converter.fromString(text);
  }

}
