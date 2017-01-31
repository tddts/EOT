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

  public Pagination(Function<Integer, RestResponse<T>> loadFunction, Consumer<Collection<E>> loadingResultConsumer, Function<Pagination<E, T>, PaginationErrorHandlerProvider> errorHandler) {
    this.loadFunction = loadFunction;
    this.loadingResultConsumer = loadingResultConsumer;
    this.errorHandler = errorHandler;
  }

  // Builder methods
  //--------------------------------------------------------------------------------------------------------------------

  /**
   * Set {@link Function} that loads page as a collection of objects.
   *
   * @param loadFunction page loading function.
   * @return current Pagination object.
   */
  public Pagination<E, T> loadPage(Function<Integer, RestResponse<T>> loadFunction) {
    this.loadFunction = loadFunction;
    return this;
  }

  /**
   * Set {@link Consumer} that processes loaded page.
   *
   * @param loadingResultConsumer page data consumer.
   * @return current Pagination object.
   */
  public Pagination<E, T> processPage(Consumer<Collection<E>> loadingResultConsumer) {
    this.loadingResultConsumer = loadingResultConsumer;
    return this;
  }

  /**
   * Set {@link Function} that hadles pagination errors.
   * Actual error handling logic is restricted so use
   * {@link #onErrorSkipPage} ,
   * {@link #onErrorStop()} and
   * {@link #onErrorRetry(int, long, boolean)}
   * to receive available handler implementations.
   *
   * @param errorHandler error handling function.
   * @return current Pagination object.
   */
  public Pagination<E, T> onError(Function<Pagination<E, T>, PaginationErrorHandlerProvider> errorHandler) {
    this.errorHandler = errorHandler;
    return this;
  }

  // Perform method
  //--------------------------------------------------------------------------------------------------------------------

  /**
   * Perform pagination using given parameters.
   *
   * @throws IllegalArgumentException if Pagination object is not initialized.
   */
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
      }
    } while (rows > 0);
  }

  private void nextPage(Collection<E> supplied){
    rows = supplied.size();
    page++;
    retryCount = 0;
  }

  private void checkInitialization() {
    if (loadFunction == null || loadingResultConsumer == null || errorHandler == null) {
      throw new IllegalArgumentException("Pagination is not initialized! One of required fields is NULL.");
    }
  }

  // Error handlers
  //--------------------------------------------------------------------------------------------------------------------

  /**
   * Skip page on error.
   *
   * @return {@link PaginationErrorHandlerProvider} that provides error handler implementation which skips page on error.
   */
  public PaginationErrorHandlerProvider onErrorSkipPage() {
    return () -> this::skipPage;
  }

  /**
   * Stop pagination process on error.
   *
   * @return {@link PaginationErrorHandlerProvider} that provides error handler implementation which stops pagination on error.
   */
  public PaginationErrorHandlerProvider onErrorStop() {
    return () -> this::stop;
  }

  /**
   * Retry page loading on error.
   * Current {@link Thread} sleeps for given timeout before each loading attempt.
   *
   * @param maxTries maximum number of attempts.
   * @param timeout timeout before new attempt.
   * @param skip if <b>true</b> - skip page when retry fails, stop pagination otherwise.
   * @return {@link PaginationErrorHandlerProvider} that provides error handler implementation which repeats page loading on error.
   */
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

  /**
   *  Only need not to increment page counter to retry.
   */
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

  // Inner classes
  //--------------------------------------------------------------------------------------------------------------------

  public interface PaginationErrorHandlerProvider {

    PaginationErrorHandler getHandler();
  }

  private interface PaginationErrorHandler {

    void handle();
  }

}
