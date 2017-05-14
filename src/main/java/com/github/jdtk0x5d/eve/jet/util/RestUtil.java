package com.github.jdtk0x5d.eve.jet.util;

import com.github.jdtk0x5d.eve.jet.exception.RestResponseException;
import com.github.jdtk0x5d.eve.jet.rest.RestResponse;

import java.util.function.Supplier;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class RestUtil {

  private static final int DEFAULT_RETRY_COUNT = 3;
  private static final long DEFAULT_RETRY_TIMEOUT = 100;

  public static <T> RestResponse<T> requestWithRetry(Supplier<RestResponse<T>> responseSupplier) throws RestResponseException {
    return requestWithRetry(DEFAULT_RETRY_COUNT, DEFAULT_RETRY_TIMEOUT, responseSupplier);
  }

  public static <T> RestResponse<T> requestWithRetry(int retryCount, long retryTimeout, Supplier<RestResponse<T>> responseSupplier) throws RestResponseException {
    int retries = 0;
    RestResponse<T> restResponse = responseSupplier.get();

    if(restResponse.isSuccessful()) return restResponse;

    if (restResponse.hasError()) {

      while (restResponse.hasError() && retries < retryCount) {
        Util.sleepForTimeout(retryTimeout);
        restResponse = responseSupplier.get();
        retries++;

        if(restResponse.isSuccessful()) return restResponse;
      }
    }
    throw new RestResponseException(restResponse.getStatus());
  }

}
