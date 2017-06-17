package com.github.jdtk0x5d.eve.jet.view.fx.spring;

import com.github.jdtk0x5d.eve.jet.view.fx.dialog.ExceptionDialog;
import javafx.application.Platform;
import org.springframework.context.MessageSource;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class FxUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

  private MessageSource messageSource;

  public FxUncaughtExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    Platform.runLater(() ->{
      ExceptionDialog dialog = new ExceptionDialog(e, messageSource);
      dialog.showAndWait();
    });
  }
}
