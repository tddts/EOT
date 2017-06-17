package com.github.jdtk0x5d.eve.jet.view.fx.spring;

import com.github.jdtk0x5d.eve.jet.view.fx.exception.ApplicationExceptionHandler;
import com.github.jdtk0x5d.eve.jet.view.fx.tools.ApplicationStartProvider;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class FxApplicationStartProvider extends Application implements ApplicationStartProvider {

  private static FxViewProvider staticFxViewProvider;

  @Autowired
  private FxViewProvider fxViewProvider;

  @Override
  public void startApplication() {
    staticFxViewProvider = fxViewProvider;
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Thread.setDefaultUncaughtExceptionHandler(new ApplicationExceptionHandler());
    staticFxViewProvider.showView(primaryStage, event -> System.exit(0));
  }
}
