/*
 * Copyright 2017 Tigran Dadaiants
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.jdtk0x5d.eve.jet.view.fx.tools.message;

import com.github.jdtk0x5d.eve.jet.view.fx.tools.message.provider.MessageHelper;
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
