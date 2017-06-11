package com.github.jdtk0x5d.eve.jet.fx.tools.message;

import com.github.jdtk0x5d.eve.jet.fx.tools.message.provider.MessageHelper;
import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class EnumMessageStringConverter<T extends Enum<T>> extends StringConverter<T> {

  private final Map<T, String> values = new HashMap<>();

  public EnumMessageStringConverter(MessageHelper messageHelper, T[] values) {
    for (T type : values) {
      this.values.put(type, messageHelper.getMessage(type));
    }
  }

  @Override
  public String toString(T object) {
    return values.get(object);
  }

  @Override
  public T fromString(String string) {
    for (T object : values.keySet()) {
      if (values.get(object).equals(string)) {
        return object;
      }
    }
    return null;
  }
}
