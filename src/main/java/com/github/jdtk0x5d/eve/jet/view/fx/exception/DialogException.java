package com.github.jdtk0x5d.eve.jet.view.fx.exception;

import org.jetbrains.annotations.NonNls;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DialogException extends RuntimeException {

  public DialogException() {
  }

  public DialogException(@NonNls String message) {
    super(message);
  }

  public DialogException(String message, Throwable cause) {
    super(message, cause);
  }

  public DialogException(Throwable cause) {
    super(cause);
  }

  public DialogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
