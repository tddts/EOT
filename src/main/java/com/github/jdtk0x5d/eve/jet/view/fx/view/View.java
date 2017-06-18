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

package com.github.jdtk0x5d.eve.jet.view.fx.view;

import com.github.jdtk0x5d.eve.jet.view.fx.exception.ViewException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * {@code View} represents JavaFX view.
 * Automatically loads view from {@code FXML} file when created,
 *
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

  private URL getFileURL() {
    return Thread.currentThread().getContextClassLoader().getResource(fileName);
  }

  /**
   * Get name of FXML file used for this view.
   *
   * @return .fxml file name.
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * Get {@link FXMLLoader} used to load this view.
   *
   * @return FXML loader
   */
  public FXMLLoader getLoader() {
    return loader;
  }

  /**
   * Get this view's controller object.
   *
   * @return controller
   */
  public T getController() {
    return controller;
  }

  /**
   * Get this view's root node.
   *
   * @return root node
   */
  public Parent getRoot() {
    return root;
  }

}
