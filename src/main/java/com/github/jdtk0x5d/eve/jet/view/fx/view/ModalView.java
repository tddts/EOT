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