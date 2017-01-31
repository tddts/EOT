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
  private Function<Pagination<E, T>, PaginationErrorHandlerProvider> errorHandler;

  private int page = 1;
  private int rows = 0;
  private int retryCount = 0;

  public Pagination() {
  }

  public Pagination<E, T> loadPage(Function<Integer, RestResponse<T>> loadFunction) {
    this.loadFunction = loadFunction;
    return this;
  }

  public Pagination<E, T> processPage(Consumer<Collection<E>> loadingResultConsumer) {
    this.loadingResultConsumer = loadingResultConsumer;
    return this;
  }

  public Pagination<E, T> onError(Function<Pagination<E, T>, PaginationErrorHandlerProvider> errorHandler) {
    this.errorHandler = errorHandler;
    return this;
  }

  public void perform() throws IllegalArgumentException {
    checkInitialization();

    do {
      RestResponse<T> response = loadFunction.apply(page);
      if (response.hasError()) {
        errorHandler.apply(this).getHandler().handle();
      }
      else {
        Collection<E> supplied = response.getObject();
        loadingResultConsumer.accept(supplied);
        nextPage(supplied);
      }
    } while (rows > 0);
  }

  private void checkInitialization() {
    if (loadFunction == null || loadingResultConsumer == null || errorHandler == null) {
      throw new IllegalArgumentException("Pagination is not initialized!");
    }
  }

  private void nextPage(Collection<E> supplied) {
    rows = supplied.size();
    page++;
  }

  public PaginationErrorHandlerProvider onErrorSkipPage() {
    return () -> this::skipPage;
  }

  public PaginationErrorHandlerProvider onErrorStop() {
    return () -> this::stop;
  }

  public PaginationErrorHandlerProvider onErrorRetry(int maxTries, long timeout, boolean skip) {
    return () -> () -> retry(maxTries, timeout, skip);
  }

  private void skipPage() {
    page++;
  }

  private void stop() {
    rows = 0;
    page = 0;
  }

  private void retry(int maxTries, long timeout, boolean skip) {
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

  public int getPage() {
    return page;
  }

  public int getRows() {
    return rows;
  }

  public interface PaginationErrorHandlerProvider {

    PaginationErrorHandler getHandler();
  }

  private interface PaginationErrorHandler {

    void handle();
  }


}
