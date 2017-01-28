package com.github.jdtk0x5d.eve.jet.api;

import com.github.jdtk0x5d.eve.jet.exception.ApplicationException;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Pagination<E, T extends Collection<E>> {

  private Function<Integer, RestResponse<T>> loadFunction;
  private Consumer<Collection<E>> loadingResultConsumer;
  private Consumer<Pagination<E, T>> errorHandler;

  private int page = 1;
  private int rows = 0;
  private int retryCount = 0;

  public Pagination() {
  }

  public Pagination(Function<Integer, RestResponse<T>> loadFunction, Consumer<Collection<E>> loadingResultConsumer, Consumer<Pagination<E, T>> errorHandler) {
    this.loadFunction = loadFunction;
    this.loadingResultConsumer = loadingResultConsumer;
    this.errorHandler = errorHandler;
  }

  public Pagination<E, T> loadPage(Function<Integer, RestResponse<T>> loadFunction) {
    this.loadFunction = loadFunction;
    return this;
  }

  public Pagination<E, T> processPage(Consumer<Collection<E>> loadingResultConsumer) {
    this.loadingResultConsumer = loadingResultConsumer;
    return this;
  }

  public Pagination<E, T> onError(Consumer<Pagination<E, T>> errorHandler) {
    this.errorHandler = errorHandler;
    return this;
  }

  public void perform() throws IllegalArgumentException{
    checkNulls();

    do {
      RestResponse<T> response = loadFunction.apply(page);
      if (response.hasError()) {
        errorHandler.accept(this);
      } else {
        Collection<E> supplied = response.getObject();
        loadingResultConsumer.accept(supplied);
        nextPage(supplied);
      }
    } while (rows > 0);
  }

  private void checkNulls() {
    if (loadFunction == null || loadingResultConsumer == null || errorHandler == null) {
      throw new IllegalArgumentException("Pagination is not initialized!");
    }
  }

  private void nextPage(Collection<E> supplied) {
    rows = supplied.size();
    page++;
  }

  public void skipPage() {
    page++;
  }

  public void stop() {
    rows = 0;
    page = 0;
  }

  public void retry(int maxTries, long timeout, boolean skip) {
    try {
      if (retryCount < maxTries) {
        // Retry after timeout
        Thread.sleep(timeout);
        retryCount++;
      } else {
        // Set retry count to zero
        retryCount = 0;
        // Skip or stop
        if (skip) {
          skipPage();
        } else {
          stop();
        }
      }
    } catch (InterruptedException e) {
      throw new ApplicationException(e);
    }
  }

  public int getPage() {
    return page;
  }

  public int getRows() {
    return rows;
  }
}
