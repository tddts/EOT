package com.github.jdtk0x5d.eve.jet.fx.tools.message.provider;

import com.github.jdtk0x5d.eve.jet.fx.tools.message.EnumMessageStringConverter;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class MessageHelperImpl implements MessageHelper {

  private static final Object[] NO_ARGS = new Object[]{};

  @Autowired
  private MessageSource messageSource;

  @Override
  public String getMessage(Enum object) {
    return messageSource.getMessage(
        object.getClass().getName() + "." + object.name(),
        NO_ARGS,
        Locale.getDefault());
  }

  @Override
  public <T extends Enum<T>> StringConverter<T> getConverter(T[] values) {
    return new EnumMessageStringConverter(this,values);
  }
}
