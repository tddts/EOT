package com.github.jdtk0x5d.eve.jet.exception;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class FileLoadingException extends ApplicationException {

  public FileLoadingException() {
  }

  public FileLoadingException(String s) {
    super(s);
  }

  public FileLoadingException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public FileLoadingException(Throwable throwable) {
    super(throwable);
  }

  public FileLoadingException(String s, Throwable throwable, boolean b, boolean b1) {
    super(s, throwable, b, b1);
  }
}
