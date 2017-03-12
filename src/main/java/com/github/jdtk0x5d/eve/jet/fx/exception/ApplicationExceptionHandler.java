package com.github.jdtk0x5d.eve.jet.fx.exception;

import com.github.jdtk0x5d.eve.jet.fx.dialog.ExceptionDialog;
import javafx.application.Platform;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class ApplicationExceptionHandler implements Thread.UncaughtExceptionHandler {

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    Platform.runLater(() ->{
      ExceptionDialog dialog = new ExceptionDialog(e);
      dialog.showAndWait();
    });
  }
}
