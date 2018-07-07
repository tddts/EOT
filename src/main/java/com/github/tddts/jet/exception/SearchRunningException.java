package com.github.tddts.jet.exception;

public class SearchRunningException extends ApplicationException {

  public SearchRunningException() {
  }

  public SearchRunningException(String s) {
    super(s);
  }

  public SearchRunningException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public SearchRunningException(Throwable throwable) {
    super(throwable);
  }

  public SearchRunningException(String s, Throwable throwable, boolean b, boolean b1) {
    super(s, throwable, b, b1);
  }
}
