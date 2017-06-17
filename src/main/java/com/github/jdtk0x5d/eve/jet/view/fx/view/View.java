package com.github.jdtk0x5d.eve.jet.view.fx.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class View<T> {

  private final String fileName;

  private final FXMLLoader loader;
  private final T controller;
  private final Parent root;

  public View(String fileName) {
    this(fileName, null);
  }

  public View(String fileName, ResourceBundle bundle) {
    this.fileName = fileName;

    if (bundle == null) {
      loader = new FXMLLoader(getFileURL());
    }
    else {
      loader = new FXMLLoader(getFileURL(), bundle);
    }

    try {
      loader.load();
    } catch (IOException e) {
      throw new ViewException(e);
    }

    root = loader.getRoot();
    controller = loader.getController();
  }

  public String getFileName() {
    return fileName;
  }

  private URL getFileURL() {
    return Thread.currentThread().getContextClassLoader().getResource(fileName);
  }

  public FXMLLoader getLoader() {
    return loader;
  }

  public T getController() {
    return controller;
  }

  public Parent getRoot() {
    return root;
  }

}
