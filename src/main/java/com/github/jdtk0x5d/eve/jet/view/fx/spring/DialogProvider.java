package com.github.jdtk0x5d.eve.jet.view.fx.spring;

import com.github.jdtk0x5d.eve.jet.config.spring.beans.ResourceBundleContainer;
import com.github.jdtk0x5d.eve.jet.view.fx.config.annotations.FXDialog;
import com.github.jdtk0x5d.eve.jet.view.fx.config.annotations.Init;
import com.github.jdtk0x5d.eve.jet.view.fx.exception.DialogException;
import com.github.jdtk0x5d.eve.jet.view.fx.view.View;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DialogProvider {

  private static final String FXML_FIELD_LOOKUP_PREFIX = "#";

  private Map<Class<?>, Dialog<?>> dialogCache = new HashMap<>();

  @Autowired
  private FxWirer fxWirer;
  @Autowired
  private ResourceBundleContainer resourceBundleContainer;

  @SuppressWarnings("unchecked")
  public <T extends Dialog<?>> T getDialog(Class<T> type) {
    // Try to use cached dialog
    Dialog<?> cachedDialog = dialogCache.get(type);
    // Create new dialog
    return cachedDialog == null ? createDialog(type) : (T) cachedDialog;
  }

  @SuppressWarnings("unchecked")
  private <T extends Dialog<?>> T createDialog(Class<T> type) {
    Constructor<?>[] constructors = type.getConstructors();
    if (constructors.length == 0) {
      throw new DialogException("Dialog should have a public constructor!");
    }
    if (constructors.length > 1) {
      throw new DialogException("Dialog should have only one constructor!");
    }
    if (constructors[0].getParameterCount() > 0) {
      throw new DialogException("Dialog constructor should have no parameters!");
    }
    try {

      T dialog = (T) constructors[0].newInstance();
      wireDialog(type, dialog);
      dialogCache.put(type, dialog);
      return dialog;

    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new DialogException("Could not create dialog!", e);
    }
  }

  private void wireDialog(Class<?> type, Dialog<?> dialog) throws IllegalAccessException, InvocationTargetException {
    wireFXML(type, dialog);
    fxWirer.initBean(dialog);
    processInitMethod(type, dialog);
  }

  private void processInitMethod(Class<?> type, Dialog<?> dialog) throws InvocationTargetException, IllegalAccessException {
    for (Method method : type.getDeclaredMethods()) {
      if (method.isAnnotationPresent(Init.class)) {
        method.setAccessible(true);
        method.invoke(dialog);
      }
    }
  }

  private void wireFXML(Class<?> type, Dialog<?> dialog) throws IllegalAccessException {
    View<?> view = loadDialogView(type);
    Node root = view.getRoot();
    dialog.getDialogPane().setContent(root);

    for (Field field : type.getDeclaredFields()) {
      if (field.isAnnotationPresent(FXML.class)) {
        field.setAccessible(true);
        field.set(dialog, root.lookup(FXML_FIELD_LOOKUP_PREFIX + field.getName()));
      }
    }
  }


  private View<?> loadDialogView(Class<?> type) {
    if (!type.isAnnotationPresent(FXDialog.class)) {
      throw new DialogException("Dialog class should have a @FXDialog annotation!");
    }

    FXDialog dialogAnnotation = type.getDeclaredAnnotation(FXDialog.class);
    String filePath = dialogAnnotation.value();

    if (filePath.isEmpty()) {
      throw new DialogException("@FXDialog should contain path to FXML file!");
    }

    return new View<>(filePath, resourceBundleContainer.getResourceBundle());
  }
}
