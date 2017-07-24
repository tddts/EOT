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

import com.github.tddts.jet.exception.ApplicationException;
import com.github.tddts.tools.core.pagination.Pagination;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class PaginationExecutor {

  private static final int MAX_POOL_SIZE = 7;

  private final Set<Pagination> paginationSet = new HashSet<>();

  public void add(Pagination pagination) {
    paginationSet.add(pagination);
  }

  public void execute() {

    if (paginationSet.isEmpty()) return;

    ExecutorService executorService = Executors.newFixedThreadPool(getPoolSize());

    try {
      executorService.invokeAll(createCallableList());
    } catch (InterruptedException e) {
      throw new ApplicationException(e);
    } finally {
      paginationSet.clear();
      executorService.shutdown();
    }
  }

  private Collection<Callable<Pagination>> createCallableList() {
    return paginationSet.stream().map(this::createCallable).collect(Collectors.toList());
  }

  private Callable<Pagination> createCallable(Pagination pagination) {
    return () -> {
      pagination.perform();
      return pagination;
    };
  }

  private int getPoolSize() {
    int size = paginationSet.size();
    return size > MAX_POOL_SIZE ? MAX_POOL_SIZE : size;
  }

  public void stop() {
    paginationSet.forEach(Pagination::stop);
  }
}
