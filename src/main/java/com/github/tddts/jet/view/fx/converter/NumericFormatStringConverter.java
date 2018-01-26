package com.github.tddts.jet.view.fx.converter;

import javafx.util.StringConverter;

import java.text.DecimalFormat;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public abstract class NumericFormatStringConverter<T extends Number> extends StringConverter<T>{

  private StringConverter<T> stringConverter;
  private DecimalFormat format;

  public NumericFormatStringConverter(StringConverter<T> stringConverter, String pattern) {
    this.stringConverter = stringConverter;
    this.format = new DecimalFormat(pattern);
  }

  @Override
  public String toString(T object) {
    // If the specified value is null, return a zero-length String
    if (object == null) {
      return "";
    }

    return format.format(object);
  }

  @Override
  public T fromString(String string) {
    return stringConverter.fromString(string);
  }

  public void setPattern(String pattern){
    format.applyPattern(pattern);
  }
}
