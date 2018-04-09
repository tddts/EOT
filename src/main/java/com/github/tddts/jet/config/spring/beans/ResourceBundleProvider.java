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

package com.github.tddts.jet.config.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * {@code ResourceBundleProvider} provides {@link MessageSource} as a {@link ResourceBundle}.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class ResourceBundleProvider {

  @Autowired
  private MessageSource messageSource;

  private ResourceBundle resourceBundle;

  @PostConstruct
  public void init() {
    resourceBundle = new MessageSourceResourceBundle(messageSource, Locale.getDefault());
  }

  public ResourceBundle getResourceBundle() {
    return resourceBundle;
  }
}
