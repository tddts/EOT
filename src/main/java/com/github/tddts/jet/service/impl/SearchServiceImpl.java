/*
 * Copyright 2018 Tigran Dadaiants
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

package com.github.tddts.jet.service.impl;

import com.github.tddts.jet.config.spring.annotations.Profiling;
import com.github.tddts.jet.model.app.SearchParams;
import com.github.tddts.jet.service.SearchService;
import com.github.tddts.jet.service.SearchOperations;
import com.github.tddts.tools.core.task.TaskChain;
import com.github.tddts.tools.core.task.impl.ReusableTaskChain;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class SearchServiceImpl implements SearchService {

  private final AtomicLong idCounter = new AtomicLong(Long.MIN_VALUE);
  private final Map<Long, TaskChain<?>> taskChainMap = new ConcurrentHashMap<>();

  private final ExecutorService executorService = Executors.newCachedThreadPool();

  @Override
  @Profiling
  public long searchForOrders(SearchParams searchParams) {
    TaskChain<?> operationsChain = createTaskChain(getOperations(), searchParams);
    executorService.execute(operationsChain::execute);
    return saveAndGetChainId(operationsChain);
  }

  private long saveAndGetChainId(TaskChain<?> operationsChain) {
    Long chainId = idCounter.incrementAndGet();
    taskChainMap.put(chainId, operationsChain);
    return chainId;
  }

  private TaskChain<?> createTaskChain(SearchOperations operations, SearchParams params) {
    operations.setSearchParams(params);
    return new ReusableTaskChain<>()
        .perform(operations::loadPrices)
        .perform(operations::loadOrders)
        .onStop(operations::stopLoadingOrders)
        .perform(operations::filterLoaded)
        .supply(operations::findProfitableOrders)
        .process(operations::filterResults)
        .process(operations::extractTypeNames)
        .process(operations::searchForRoutes)
        .consume(operations::consumeResult)
        .finallyAction(operations::cleanUp);
  }

  @Override
  public void stopSearch(long chainId) {
    TaskChain<?> taskChain = taskChainMap.get(chainId);
    if (taskChain != null) {
      taskChain.stop();
      taskChainMap.remove(chainId);
    }
  }

  @PreDestroy
  private void onDestroy() {
    executorService.shutdown();
  }

  @Lookup
  public SearchOperations getOperations() {
    return null;
  }


}
