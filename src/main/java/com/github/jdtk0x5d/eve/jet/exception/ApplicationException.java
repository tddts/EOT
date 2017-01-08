package com.github.jdtk0x5d.eve.jet.exception;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class ApplicationException extends RuntimeException {

  public ApplicationException() {
  }

  public ApplicationException(String s) {
    super(s);
  }

  public ApplicationException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public ApplicationException(Throwable throwable) {
    super(throwable);
  }

  public ApplicationException(String s, Throwable throwable, boolean b, boolean b1) {
    super(s, throwable, b, b1);
  }
}
