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

package com.github.jdtk0x5d.eve.jet.tools.pagination;

import com.github.jdtk0x5d.eve.jet.rest.RestResponse;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * {@code PaginationBuilder} represents a builder for {@link Pagination} object performing REST resource pagination.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class PaginationBuilder<E, T extends Collection<E>> {

  private Function<Integer, RestResponse<T>> loadFunction;
  private Consumer<T> loadingResultConsumer;
  private Consumer<PaginationErrorHandler> errorConsumer;

  private int firstPage = 1;
  private int lastPage = -1;

  private int retryNumber = 3;
  private long retryTimeout = 100;
  private boolean skipPageOnRetry = true;

  public PaginationBuilder() {
  }

  /**
   * Set function that will be loading data for given page.
   *
   * @param loadFunction page loading function
   * @return current builder instance
   */
  public PaginationBuilder<E, T> loadPage(Function<Integer, RestResponse<T>> loadFunction) {
    this.loadFunction = loadFunction;
    return this;
  }

  /**
   * Set consumer processing loaded page data.
   *
   * @param loadingResultConsumer page data consumer
   * @return current builder instance
   */
  public PaginationBuilder<E, T> processPage(Consumer<T> loadingResultConsumer) {
    this.loadingResultConsumer = loadingResultConsumer;
    return this;
  }

  /**
   * Set error handler.
   *
   * @param errorConsumer consumer error handler.
   * @return current builder instance
   */
  public PaginationBuilder<E, T> onError(Consumer<PaginationErrorHandler> errorConsumer) {
    this.errorConsumer = errorConsumer;
    return this;
  }

  /**
   * Set page for pagination to start with.
   *
   * @param page page number.
   * @return current builder instance
   */
  public PaginationBuilder<E, T> startWith(int page) {
    firstPage = page < 1 ? 1 : page;
    return this;
  }

  /**
   * Set page for pagination to finish on.
   *
   * @param page page number
   * @return current builder instance
   */
  public PaginationBuilder<E, T> finishOn(int page) {
    lastPage = page;
    return this;
  }

  /**
   * Set maximum number of retries for each page in case of error.
   *
   * @param retryNumber number of retries
   * @return current builder instance
   */
  public PaginationBuilder<E, T> retryNumber(int retryNumber) {
    this.retryNumber = retryNumber;
    return this;
  }

  /**
   * Set timeout before each retry.
   *
   * @param retryTimeout timeout
   * @return current builder instance
   */
  public PaginationBuilder<E, T> retryTimeout(long retryTimeout) {
    this.retryTimeout = retryTimeout;
    return this;
  }

  /**
   * Skip page if retry was unsuccessful or stop overall pagination process.
   * Set <b>false</b> to stop pagination.
   * Default value is <b>true</b>.
   *
   * @param skipPageOnRetry skip flag
   * @return current builder instance
   */
  public PaginationBuilder<E, T> skiPageOnRetry(boolean skipPageOnRetry) {
    this.skipPageOnRetry = skipPageOnRetry;
    return this;
  }

  /**
   * Build pagination object using given parameters.
   *
   * @return pagination object.
   */
  public Pagination build() {
    if (loadFunction == null || loadingResultConsumer == null || errorConsumer == null) {
      throw new IllegalStateException("Pagination can not be initialized! Some of required fields are NULL!");
    }
    return new PaginationImpl<>(loadFunction, loadingResultConsumer, errorConsumer,
        firstPage, lastPage,
        retryNumber, retryTimeout, skipPageOnRetry);
  }
}
