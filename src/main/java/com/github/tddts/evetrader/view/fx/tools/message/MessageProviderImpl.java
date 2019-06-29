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

package com.github.tddts.evetrader.view.fx.tools.message;

import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class MessageProviderImpl implements MessageProvider {

  private static final Object[] NO_ARGS = new Object[]{};

  @Autowired
  private MessageSource messageSource;

  @Override
  public String getMessage(String key) {
    return messageSource.getMessage(key, NO_ARGS, Locale.getDefault());
  }

  @Override
  public String getMessage(Enum object) {
    return messageSource.getMessage(
        object.getClass().getName() + "." + object.name(),
        NO_ARGS,
        Locale.getDefault());
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends Enum> StringConverter<T> getConverter(T[] values) {
    return new EnumMessageStringConverter(this, values);
  }
}
