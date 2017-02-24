package com.github.jdtk0x5d.eve.jet.fx.tools.message;

import javafx.util.StringConverter;
import org.springframework.context.MessageSource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class MessageStringConverter<T extends MessageAware> extends StringConverter<T> {

  private final Map<T, String> values = new HashMap<>();

  public MessageStringConverter(MessageSource messageSource, T[] values) {
    for (T type : values) {
      this.values.put(type, messageSource.getMessage(type.getMessageKey(), new Object[0], Locale.getDefault()));
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
