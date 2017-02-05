package com.github.jdtk0x5d.eve.jet.api;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class PaginationBuilder<E, T extends Collection<E>> {

  private Function<Integer, RestResponse<T>> loadFunction;
  private Consumer<T> loadingResultConsumer;
  private Consumer<Pagination> errorHandler;

  private int firstPage = 1;
  private int lastPage = -1;

  public PaginationBuilder() {
  }

  public PaginationBuilder<E, T> loadPage(Function<Integer, RestResponse<T>> loadFunction) {
    this.loadFunction = loadFunction;
    return this;
  }

  public PaginationBuilder<E, T> processPage(Consumer<T> loadingResultConsumer) {
    this.loadingResultConsumer = loadingResultConsumer;
    return this;
  }

  public PaginationBuilder<E, T> onError(Consumer<Pagination> errorHandler) {
    this.errorHandler = errorHandler;
    return this;
  }

  void startWith(int page) {
    firstPage = page < 1 ? 1 : page;
  }

  void finishOn(int page) {
    lastPage = page;
  }

  public Pagination build() {
    if (loadFunction == null || loadingResultConsumer == null || errorHandler == null) {
      throw new IllegalArgumentException("Pagination could not be initialized! Some of required fields are NULL!.");
    }
    return new PaginationImpl<>(loadFunction, loadingResultConsumer, errorHandler, firstPage, lastPage);
  }
}
