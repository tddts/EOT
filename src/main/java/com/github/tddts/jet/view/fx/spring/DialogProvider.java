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

import com.github.tddts.jet.config.spring.beans.ResourceBundleProvider;
import com.github.tddts.jet.util.Util;
import com.github.tddts.jet.view.fx.annotations.FxDialog;
import com.github.tddts.jet.view.fx.annotations.FxDialogInit;
import com.github.tddts.jet.view.fx.exception.DialogException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@code DialogProvider} creates {@link Dialog} objects by processing classes with special annotations.
 * {@code DialogProvider} is capable of injecting dialog with FXML nodes similar to JavaFX controllers,
 * and also capable processing Dialog as a Spring bean (including injection of dependencies).
 * <p>
 * To create a Dialog via {@code DialogProvider} you should mark corresponding Dialog implementation with
 * {@link FxDialog} annotation and describe path to FXML file with dialog content.
 * FXMl file should have {@code fx:controller} property set to dialog class.
 * <p>
 * To initialize dialog crate a method with required parameters and mark by {@link FxDialogInit} annotation.
 * This initialization method will be invoked every time this dialog is called.
 * <p>
 * Such dialog would also support {@link PostConstruct} annotation as a Spring-processed bean.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DialogProvider {

  private static final Object[] EMPTY_ARGS = new Object[]{};

  private Map<Class<?>, Dialog<?>> dialogCache = new HashMap<>();
  private Map<Class<?>, List<Method>> dialogInitCache = new HashMap<>();

  @Autowired
  private FxBeanWirer fxBeanWirer;
  @Autowired
  private ResourceBundleProvider resourceBundleProvider;

  /**
   * Create dialog of given type.
   *
   * @param type dialog class
   * @param <T>  dialog generic type
   * @return dialog of given type.
   */
  @SuppressWarnings("unchecked")
  public <T extends Dialog<?>> T getDialog(Class<T> type) {
    return getDialog(type, EMPTY_ARGS);
  }

  /**
   * Create dialog of given type using given arguments.
   *
   * @param type dialog class
   * @param <T>  dialog generic type
   * @param args dialog arguments
   * @return dialog of given type.
   */
  @SuppressWarnings("unchecked")
  public <T extends Dialog<?>> T getDialog(Class<T> type, Object... args) {
    Dialog<?> dialog = dialogCache.get(type);
    dialog = dialog == null ? createDialog(type) : dialog;
    processInitMethods(type, dialog, args);
    return (T) dialog;
  }

  private <T extends Dialog<?>> T createDialog(Class<T> type) {

    if (!type.isAnnotationPresent(FxDialog.class)) {
      throw new DialogException("Dialog class should have a @FxDialog annotation!");
    }

    FxDialog dialogAnnotation = type.getDeclaredAnnotation(FxDialog.class);
    FXMLLoader loader = loadDialogView(dialogAnnotation);
    T dialog = loader.getController();
    setDialogContent(dialog, loader.getRoot(), dialogAnnotation);
    fxBeanWirer.initBean(dialog);
    dialogCache.put(type, dialog);
    return dialog;

  }

  private void processInitMethods(Class<?> type, Dialog<?> dialog, Object[] args) {

    List<Method> initMethods = dialogInitCache.get(type);

    // Add dialog init methods to cache
    if (initMethods == null) {
      initMethods = new ArrayList<>();
      for (Method method : type.getDeclaredMethods()) {
        if (method.isAnnotationPresent(FxDialogInit.class)) initMethods.add(method);
      }
      dialogInitCache.put(type, initMethods);
    }

    // Invoke init methods
    try {
      for (Method method : initMethods) {
        method.setAccessible(true);
        if (method.getParameterCount() == 0) {
          method.invoke(dialog, EMPTY_ARGS);
        }
        else {
          method.invoke(dialog, args);
        }
      }

    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new DialogException("Could not initialize dialog!", e);
    }
  }

  private void setDialogContent(Dialog<?> dialog, Node root, FxDialog dialogAnnotation) {
    boolean expandable = dialogAnnotation.expandable();
    if (expandable) {
      dialog.getDialogPane().setExpandableContent(root);
    }
    else {
      dialog.getDialogPane().setContent(root);
    }
  }

  private FXMLLoader loadDialogView(FxDialog dialogAnnotation) {

    String filePath = dialogAnnotation.value();

    if (filePath.isEmpty()) {
      throw new DialogException("@FxDialog should contain path to FXML file!");
    }

    FXMLLoader loader = new FXMLLoader(Util.getClasspathResourceURL(filePath), resourceBundleProvider.getResourceBundle());

    try {
      loader.load();
    } catch (IOException e) {
      throw new DialogException(e.getMessage(), e);
    }

    return loader;
  }
}
