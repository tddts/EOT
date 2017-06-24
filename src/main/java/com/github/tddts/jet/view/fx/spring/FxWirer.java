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

package com.github.tddts.jet.view.fx.spring;

import com.github.tddts.jet.exception.ApplicationException;
import com.github.tddts.jet.view.fx.view.View;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code FxWirer} provides functionality for wiring JavaFX objects to Spring context.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class FxWirer {

  private static final String CONTROLLER_SUFFIX = "Controller";

  @Autowired
  private AutowireCapableBeanFactory beanFactory;

  /**
   * View given view's controller to Spring context.
   *
   * @param view view
   */
  public void wire(View<?> view) {
    Object controller = view.getController();
    if (controller == null) return;
    wireController(controller);
    wireNestedControllers(controller);
  }

  /**
   * Wire object to Spring context using class simple name as a bean's name.
   *
   * @param object object
   */
  public void initBean(Object object) {
    beanFactory.autowireBean(object);
    beanFactory.initializeBean(object, object.getClass().getSimpleName());
  }

  private void wireNestedControllers(Object controller) {
    Class<?> type = controller.getClass();
    List<Object> controllers = new ArrayList<>();
    // Find injected controller fields
    try {
      for (Field field : type.getDeclaredFields()) {
        if (field.isAnnotationPresent(FXML.class) && field.getName().endsWith(CONTROLLER_SUFFIX)) {
          field.setAccessible(true);
          controllers.add(field.get(controller));
        }
      }
    } catch (IllegalAccessException e) {
      throw new ApplicationException(e);
    }
    // Wire nested controllers
    for (Object nestedController : controllers) {
      wireController(nestedController);
      wireNestedControllers(nestedController);
    }
  }

  private void wireController(Object controller) {
    initBean(controller);
  }
}
