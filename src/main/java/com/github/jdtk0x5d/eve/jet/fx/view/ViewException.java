package com.github.jdtk0x5d.eve.jet.fx.view;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class ViewException extends RuntimeException {

  public ViewException() {
  }

  public ViewException(String s) {
    super(s);
  }

  public ViewException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public ViewException(Throwable throwable) {
    super(throwable);
  }

  public ViewException(String s, Throwable throwable, boolean b, boolean b1) {
    super(s, throwable, b, b1);
  }
}
