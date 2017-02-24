package com.github.jdtk0x5d.eve.jet.rest;

import com.github.jdtk0x5d.eve.jet.exception.RestResponseException;
import com.github.jdtk0x5d.eve.jet.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class RestResponse<T> {

  private T object;
  private HttpStatus status;
  private long retryTimeout = 100;

  public RestResponse(T object, HttpStatus httpStatus) {
    this.object = object;
    this.status = httpStatus;
  }

  public RestResponse(ResponseEntity<T> responseEntity) {
    object = responseEntity.getBody();
    status = responseEntity.getStatusCode();
  }

  public RestResponse(HttpStatusCodeException statusCodeException) {
    status = statusCodeException.getStatusCode();
  }

  public static <T> RestResponse<List<T>> fromArrayResponse(ResponseEntity<T[]> entity) {
    return new RestResponse<>(Arrays.asList(entity.getBody()), entity.getStatusCode());
  }

  public T getObject() {
    return object;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getStatusMessage() {
    return status.value() + " " + status.getReasonPhrase();
  }

  public long getRetryTimeout() {
    return retryTimeout;
  }

  public void setRetryTimeout(long retryTimeout) {
    this.retryTimeout = retryTimeout;
  }

  public boolean hasObject() {
    return object != null;
  }

  public boolean hasError() {
    return status.is5xxServerError() || status.is4xxClientError();
  }

  public boolean isSuccessful() {
    return status.is2xxSuccessful();
  }

  public RestResponse<T> checkObject() throws RestResponseException {
    if (!isSuccessful() || !hasObject()) throwError();
    return this;
  }

  public RestResponse<T> checkObject(Supplier<RestResponse<T>> retrySupplier) throws RestResponseException {
    if (!hasObject()) {
      retry(retrySupplier);
    }
    return checkObject();
  }

  public void process(Consumer<T> consumer) {
    if (isSuccessful()) {
      consumer.accept(object);
    }
    else {
      throwError();
    }
  }

  public void process(Consumer<T> consumer, Supplier<RestResponse<T>> retrySupplier) {
    if (isSuccessful()) {
      consumer.accept(object);
    }
    else {
      retry(retrySupplier);
      process(consumer);
    }
  }

  private void retry(Supplier<RestResponse<T>> retrySupplier) {
    Util.sleepForTimeout(retryTimeout);
    RestResponse<T> retryResponse = retrySupplier.get();
    object = retryResponse.object;
    status = retryResponse.status;
  }

  private void throwError() {
    throw new RestResponseException(status);
  }

}
