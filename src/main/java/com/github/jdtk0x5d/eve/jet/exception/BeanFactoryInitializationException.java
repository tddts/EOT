package com.github.jdtk0x5d.eve.jet.exception;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class BeanFactoryInitializationException extends ApplicationException{

  public BeanFactoryInitializationException() {
  }

  public BeanFactoryInitializationException(String s) {
    super(s);
  }

  public BeanFactoryInitializationException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public BeanFactoryInitializationException(Throwable throwable) {
    super(throwable);
  }

  public BeanFactoryInitializationException(String s, Throwable throwable, boolean b, boolean b1) {
    super(s, throwable, b, b1);
  }
}
