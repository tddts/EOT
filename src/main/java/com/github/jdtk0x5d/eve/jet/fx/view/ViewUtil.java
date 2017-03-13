package com.github.jdtk0x5d.eve.jet.fx.view;

import com.github.jdtk0x5d.eve.jet.exception.ApplicationException;
import com.github.jdtk0x5d.eve.jet.exception.BrowserOpeningException;
import com.github.jdtk0x5d.eve.jet.util.SpringUtil;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class ViewUtil {

  public static final Background BACKGROUND_RED = new Background(new BackgroundFill(Color.web("#ed4949"), CornerRadii.EMPTY, Insets.EMPTY));
  public static final Background BACKGROUND_GREEN = new Background(new BackgroundFill(Color.web("#d4fcd9"), CornerRadii.EMPTY, Insets.EMPTY));


  public static void wire(View<?> view) {
    Object controller = view.getController();
    if (controller == null) return;
    wireController(controller);
    wireNestedControllers(controller);
  }

  private static void wireNestedControllers(Object controller) {
    Class<?> type = controller.getClass();
    List<Object> controllers = new ArrayList<>();
    // Find injected controller fields
    try {
      for (Field field : type.getDeclaredFields()) {
        if (field.isAnnotationPresent(FXML.class) && field.getName().endsWith("Controller")) {
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

  private static void wireController(Object controller) {
    SpringUtil.initBean(controller, controller.getClass().getSimpleName());
  }

  public static Stage getStage(Node node) {
    return (Stage) node.getScene().getWindow();
  }

  public static void openWebpage(URI uri) throws BrowserOpeningException {
    Thread thread = new Thread(() -> openPage(uri));
    thread.start();
  }

  private static void openPage(URI uri) {
    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
      try {
        desktop.browse(uri);
      } catch (Exception e) {
        throw new BrowserOpeningException(e);
      }
    }
  }

  public static View<?> loadView(String fileName) {
    View view = new View(fileName);
    wire(view);
    return view;
  }

  public static ModalView<?> loadModalView(String fileName, Node node) {
    return loadModalView(fileName, getStage(node));
  }

  public static ModalView<?> loadModalView(String fileName, Stage stage) {
    ModalView view = new ModalView(fileName, stage);
    wire(view);
    return view;
  }

}
