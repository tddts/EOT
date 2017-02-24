package com.github.jdtk0x5d.eve.jet.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class RestResponseException extends ApplicationException {

  public RestResponseException(HttpStatus httpStatus) {
    super("REST HTTP Error " + httpStatus.value() + ": " + httpStatus.getReasonPhrase());
  }
}
