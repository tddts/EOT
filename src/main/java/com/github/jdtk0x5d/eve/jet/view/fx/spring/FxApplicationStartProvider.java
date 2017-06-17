package com.github.jdtk0x5d.eve.jet.view.fx.spring;

import com.github.jdtk0x5d.eve.jet.view.fx.tools.ApplicationStartProvider;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class FxApplicationStartProvider extends Application implements ApplicationStartProvider {

  private static FxViewProvider staticFxViewProvider;
  private static MessageSource staticMessageSource;

  @Autowired
  private FxViewProvider fxViewProvider;
  @Autowired
  private MessageSource messageSource;

  @Override
  public void startApplication() {
    staticFxViewProvider = fxViewProvider;
    staticMessageSource = messageSource;
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Thread.setDefaultUncaughtExceptionHandler(new FxUncaughtExceptionHandler(staticMessageSource));
    staticFxViewProvider.showView(primaryStage, event -> System.exit(0));
  }
}
