package com.github.jdtk0x5d.eve.jet.fx.tools.message.provider;

import javafx.util.StringConverter;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface MessageHelper {

  String getMessage(Enum object);

  <T extends Enum<T>> StringConverter<T> getConverter(T[] values);
}
