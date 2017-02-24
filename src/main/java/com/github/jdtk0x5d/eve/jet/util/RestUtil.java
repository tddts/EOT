package com.github.jdtk0x5d.eve.jet.util;

import com.github.jdtk0x5d.eve.jet.rest.RestResponse;

import java.util.function.Supplier;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class RestUtil {

  public static <T> RestResponse<T> safeRequest(Supplier<RestResponse<T>> responseSupplier){
    return responseSupplier.get().checkObject(responseSupplier);
  }
}
