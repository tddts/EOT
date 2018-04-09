/*
 * Copyright 2018 Tigran Dadaiants
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

package com.github.tddts.jet.view.fx.tools.message;

import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link StringConverter} implementation that provides mssages for Enum objects.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class EnumMessageStringConverter<T extends Enum> extends StringConverter<T> {

  private final Map<T, String> values = new HashMap<>();

  public EnumMessageStringConverter(MessageProvider messageProvider, T[] values) {
    for (T type : values) {
      this.values.put(type, messageProvider.getMessage(type));
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
