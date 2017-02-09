package com.github.jdtk0x5d.eve.jet.tools.pagination;

import com.github.jdtk0x5d.eve.jet.rest.RestResponse;
import com.github.jdtk0x5d.eve.jet.exception.ApplicationException;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class PaginationImpl<E, T extends Collection<E>> implements Pagination, PaginationErrorHandler {

  private final Function<Integer, RestResponse<T>> loadFunction;
  private final Consumer<T> loadingResultConsumer;
  private final Predicate<Pagination> paginationCondition;
  private final Consumer<PaginationErrorHandler> errorConsumer;

  private int page;
  private int rows;
  private int retryCount;

  PaginationImpl(Function<Integer, RestResponse<T>> loadFunction,
                 Consumer<T> loadingResultConsumer,
                 Consumer<PaginationErrorHandler> errorConsumer,
                 int firstPage, int lastPage) {
    // Functions
    this.loadFunction = loadFunction;
    this.loadingResultConsumer = loadingResultConsumer;
    this.errorConsumer = errorConsumer;
    // First and last page
    page = firstPage;
    if (lastPage <= 0) {
      paginationCondition = pagination -> rows > 0;
    }
    else {
      int lastPageToLoad = lastPage < firstPage ? firstPage : lastPage;
      paginationCondition = pagination -> rows > 0 || page <= lastPageToLoad;
    }
  }

  @Override
  public void perform() {
    do {
      RestResponse<T> response = loadFunction.apply(page);
      if (response.hasError()) {
        errorConsumer.accept(this);
      }
      else {
        T supplied = response.getObject();
        loadingResultConsumer.accept(supplied);
        nextPage(supplied);
      }
    }
    while (paginationCondition.test(this));
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
  public void retryPage(int maxTries, long timeout, boolean skip) {
    if (retryCount < maxTries) {
      // Retry after timeout
      sleepForTimeout(timeout);
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
  }

  private void sleepForTimeout(long timeout) {
    try {
      Thread.sleep(timeout);
    }
    catch (InterruptedException e) {
      throw new ApplicationException(e);
    }
  }
}
