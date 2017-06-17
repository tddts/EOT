package com.github.jdtk0x5d.eve.jet.view.fx.spring;

import com.github.jdtk0x5d.eve.jet.exception.ApplicationException;
import com.github.jdtk0x5d.eve.jet.view.fx.view.View;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class FxViewWirer {

  private static final String CONTROLLER_SUFFIX = "Controller";

  @Autowired
  private AutowireCapableBeanFactory beanFactory;

  public void wire(View<?> view) {
    Object controller = view.getController();
    if (controller == null) return;
    wireController(controller);
    wireNestedControllers(controller);
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
    initBean(controller, controller.getClass().getSimpleName());
  }

  private void initBean(Object object, String name) {
    beanFactory.autowireBean(object);
    beanFactory.initializeBean(object, name);
  }
}
