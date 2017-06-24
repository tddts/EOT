/*
 * Copyright 2017 Tigran Dadaiants
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tddts.jet.tools.pagination;

import com.github.tddts.jet.rest.RestResponse;
import com.github.tddts.jet.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * {@code PaginationImpl} is a {@link Pagination} implementation performing pagination over REST resource.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class PaginationImpl<E, T extends Collection<E>> implements Pagination, PaginationErrorHandler {

  private static final Logger logger = LogManager.getLogger(PaginationImpl.class);


  private final Function<Integer, RestResponse<T>> loadFunction;
  private final Consumer<T> loadingResultConsumer;
  private final Predicate<Pagination> paginationCondition;
  private final Consumer<PaginationErrorHandler> errorConsumer;

  private final int retryNumber;
  private final long retryTimeout;
  private final boolean skipPageOnRetry;

  private volatile int page;
  private volatile int rows;
  private volatile int retryCount;

  PaginationImpl(Function<Integer, RestResponse<T>> loadFunction,
                 Consumer<T> loadingResultConsumer,
                 Consumer<PaginationErrorHandler> errorConsumer,
                 int firstPage, int lastPage, int retryNumber, long retryTimeout, boolean skipPageOnRetry) {
    // Functions
    this.loadFunction = loadFunction;
    this.loadingResultConsumer = loadingResultConsumer;
    this.errorConsumer = errorConsumer;
    // Retry params
    this.retryNumber = retryNumber;
    this.retryTimeout = retryTimeout;
    this.skipPageOnRetry = skipPageOnRetry;
    // First and last page
    page = firstPage;
    if (lastPage <= 0) {
      paginationCondition = pagination -> rows > 0 || (rows == 0 && page == firstPage);
    }
    else {
      int lastPageToLoad = lastPage < firstPage ? firstPage : lastPage;
      paginationCondition = pagination -> rows > 0 || (rows == 0 && page == firstPage) || page <= lastPageToLoad;
    }
  }

  @Override
  public void perform() {
    do {
      RestResponse<T> response = load();
      if (response.hasError()) {
        logger.warn("Error occurred: " + response.getStatusMessage());
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

  private synchronized RestResponse<T> load() {
    return loadFunction.apply(page);
  }

  private void nextPage(T supplied) {
    rows = supplied.size();
    page++;
    retryCount = 0;
  }

  @Override
  public synchronized void stop() {
    logger.warn("Stopping pagination");
    rows = 0;
    page = 0;
  }

  @Override
  public void skipPage() {
    logger.warn("Skipping page [" + page + "]");
    page++;
  }

  @Override
  public void retryPage() {
    logger.warn("Retrying page [" + page + "]");
    if (retryCount < retryNumber) {
      // Retry after timeout
      Util.sleepForTimeout(retryTimeout);
      retryCount++;
    }
    else {
      // Set retry count to zero
      retryCount = 0;
      // Skip or stop
      if (skipPageOnRetry) {
        skipPage();
      }
      else {
        stop();
      }
    }
  }


}
