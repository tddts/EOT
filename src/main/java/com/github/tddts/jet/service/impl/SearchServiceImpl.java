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
import com.github.tddts.jet.exception.SearchRunningException;
import com.github.tddts.jet.model.app.SearchParams;
import com.github.tddts.jet.service.SearchService;
import com.github.tddts.jet.service.SearchOperations;
import com.github.tddts.tools.core.task.TaskChain;
import com.github.tddts.tools.core.task.impl.ReusableTaskChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class SearchServiceImpl implements SearchService {

  private final ExecutorService executorService = Executors.newSingleThreadExecutor();

  @Autowired
  private SearchOperations searchOperations;
  private TaskChain<?> searchChain;

  @PostConstruct
  private void init(){
    searchChain = new ReusableTaskChain<>()
            .perform(searchOperations::loadPrices)
            .perform(searchOperations::loadOrders)
            .onStop(searchOperations::stopLoadingOrders)
            .perform(searchOperations::filterLoaded)
            .supply(searchOperations::findProfitableOrders)
            .process(searchOperations::filterResults)
            .process(searchOperations::extractTypeNames)
            .process(searchOperations::searchForRoutes)
            .consume(searchOperations::consumeResult)
            .finallyAction(searchOperations::cleanUp);
  }

  @Override
  @Profiling
  public void searchForOrders(SearchParams searchParams) throws SearchRunningException {
    checkSearchRunning();
    launchSearch(searchParams);
  }

  private void checkSearchRunning() {
    if (searchChain.isRunning())
      throw new SearchRunningException("Search is in progress!");
  }

  private void launchSearch(SearchParams searchParams){
    searchOperations.setSearchParams(searchParams);
    executorService.execute(searchChain::execute);
  }

  @Override
  public void stopSearch() {
    searchChain.stop();
  }

  @PreDestroy
  private void onDestroy() {
    executorService.shutdown();
  }


}
