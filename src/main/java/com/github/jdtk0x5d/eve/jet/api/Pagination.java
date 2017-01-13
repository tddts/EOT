package com.github.jdtk0x5d.eve.jet.api;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Pagination {

  public static <T> void perform(Function<Integer, Collection<T>> function, Consumer<Collection<T>> cons) {
    int page = 1;
    int rows = 0;
    do {
      Collection<T> supplied = function.apply(page);
      cons.accept(supplied);
      rows = supplied.size();
      page++;
    } while (rows > 0);
  }
}
