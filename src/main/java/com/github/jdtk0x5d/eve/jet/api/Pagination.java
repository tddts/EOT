package com.github.jdtk0x5d.eve.jet.api;

import com.github.jdtk0x5d.eve.jet.exception.ApplicationException;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class Pagination<E, T extends Collection<E>> {

  public static final Predicate<Pagination> ALL_PAGES = pagination -> pagination.rows > 0;

  private int page = 1;
  private int rows = 0;
  private int retryCount = 0;

  private Pagination() {
  }

  public static <E, T extends Collection<E>> void perform(Function<Integer, RestResponse<T>> function, Consumer<Collection<E>> cons, Consumer<Pagination<E, T>> errorHandler) {
    Pagination<E, T> pagination = new Pagination<>();
    pagination.performPagination(function, cons, errorHandler);
  }

  private void performPagination(Function<Integer, RestResponse<T>> function, Consumer<Collection<E>> collectionConsumer, Consumer<Pagination<E, T>> errorConsumer) {
    do {
      RestResponse<T> response = function.apply(page);
      if (response.hasError()) {
        errorConsumer.accept(this);
      } else {
        Collection<E> supplied = response.getObject();
        collectionConsumer.accept(supplied);
        nextPage(supplied);
      }
    } while (rows > 0);
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
