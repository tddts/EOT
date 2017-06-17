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

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class ModalView<T> extends View<T> {

  private final Stage stage;
  private final Scene scene;

  public ModalView(String fileName, ResourceBundle bundle, Stage parent) {
    super(fileName, bundle);
    stage = createPopup(parent);
    scene = new Scene(getRoot());
    stage.setScene(scene);
  }

  public ModalView(String fileName, Stage parent) {
    super(fileName);
    stage = createPopup(parent);
    scene = new Scene(getRoot());
    stage.setScene(scene);
  }

  private static Stage createPopup(Stage primaryStage) {
    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(primaryStage);
    return dialog;
  }

  public Stage getStage() {
    return stage;
  }

  public Scene getScene() {
    return scene;
  }

  public void show() {
    stage.show();
  }

  public void showAndWait() {
    stage.showAndWait();
  }

}