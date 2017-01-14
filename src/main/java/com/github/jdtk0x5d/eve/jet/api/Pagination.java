package com.github.jdtk0x5d.eve.jet.api;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Pagination {

  public static final Predicate<Pagination> ALL_PAGES = pagination -> pagination.rows > 0;

  private int page = 1;
  private int rows = 0;

  public static <T> void perform(Function<Integer, Collection<T>> function, Consumer<Collection<T>> cons, Predicate<Pagination> predicate) {
    Pagination pagination = new Pagination();
    pagination.performPagination(function, cons, predicate);
  }

  private <T> void performPagination(Function<Integer, Collection<T>> function, Consumer<Collection<T>> cons, Predicate<Pagination> predicate) {
    do {
      Collection<T> supplied = function.apply(page);
      cons.accept(supplied);
      rows = supplied.size();
      page++;
    } while (predicate.test(this));
  }

  public int getPage() {
    return page;
  }

  public int getRows() {
    return rows;
  }
}
