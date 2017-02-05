package com.github.jdtk0x5d.eve.jet.api;

import com.github.jdtk0x5d.eve.jet.exception.ApplicationException;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class PaginationImpl<E, T extends Collection<E>> implements Pagination {

  private final Function<Integer, RestResponse<T>> loadFunction;
  private final Consumer<T> loadingResultConsumer;
  private final Consumer<Pagination> errorHandler;
  private final Predicate<Pagination> paginationCondition;

  private int page;
  private int rows;
  private int retryCount;

  PaginationImpl(Function<Integer, RestResponse<T>> loadFunction,
                 Consumer<T> loadingResultConsumer,
                 Consumer<Pagination> errorHandler,
                 int firstPage, int lastPage) {

    this.loadFunction = loadFunction;
    this.loadingResultConsumer = loadingResultConsumer;
    this.errorHandler = errorHandler;

    page = firstPage;

    if (lastPage <= 0) {
      paginationCondition = pagination -> rows > 0;
    }
    else {
      int lastLoadedPage = lastPage < firstPage ? firstPage : lastPage;
      paginationCondition = pagination -> rows > 0 || page <= lastLoadedPage;
    }
  }

  @Override
  public void perform() {
    do {
      RestResponse<T> response = loadFunction.apply(page);
      if (response.hasError()) {
        errorHandler.accept(this);
      }
      else {
        T supplied = response.getObject();
        loadingResultConsumer.accept(supplied);
        nextPage(supplied);
      }
    } while (paginationCondition.test(this));
  }

  private void nextPage(T supplied) {
    rows = supplied.size();
    page++;
    retryCount = 0;
  }

  @Override
  public void stop() {
    rows = 0;
    page = 0;
  }

  @Override
  public void skipPage() {
    page++;
  }

  @Override
  public void retry(int maxTries, long timeout, boolean skip) {
    try {
      if (retryCount < maxTries) {
        // Retry after timeout
        Thread.sleep(timeout);
        retryCount++;
      }
      else {
        // Set retry count to zero
        retryCount = 0;
        // Skip or stop
        if (skip) {
          skipPage();
        }
        else {
          stop();
        }
      }
    } catch (InterruptedException e) {
      throw new ApplicationException(e);
    }
  }
}
