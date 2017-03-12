package com.github.jdtk0x5d.eve.jet;

import com.github.jdtk0x5d.eve.jet.fx.exception.ApplicationExceptionHandler;
import com.github.jdtk0x5d.eve.jet.fx.view.View;
import com.github.jdtk0x5d.eve.jet.fx.view.ViewUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

  public static void main(String[] args) throws Exception {
    launch(args);
  }


  @Override
  public void start(Stage stage) throws Exception {
    Thread.setDefaultUncaughtExceptionHandler(new ApplicationExceptionHandler());
    stage.setTitle("JET v1.0");
    stage.setOnCloseRequest(t -> {
      Platform.exit();
      System.exit(0);
    });

    View view = new View("fxml/main.fxml");
    ViewUtil.wire(view);
    Scene scene = new Scene(view.getRoot(), 1024, 600);
    stage.setScene(scene);
    stage.show();
  }
}
