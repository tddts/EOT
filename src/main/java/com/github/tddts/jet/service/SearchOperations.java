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

package com.github.tddts.jet.service;

import com.github.tddts.jet.model.app.OrderSearchRow;
import com.github.tddts.jet.model.app.SearchParams;
import com.github.tddts.jet.model.db.ResultOrder;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface SearchOperations {

  void setSearchParams(SearchParams params);

  void loadPrices();

  void loadOrders();

  void stopLoadingOrders();

  void filterLoaded();

  void consumeResult(List<OrderSearchRow> result);

  void cleanUp();

  List<ResultOrder> findProfitableOrders();

  List<ResultOrder> filterResults(List<ResultOrder> searchResults);

  Pair<List<ResultOrder>, Map<Integer, String>> extractTypeNames(List<ResultOrder> searchResults);

  List<OrderSearchRow> searchForRoutes(Pair<List<ResultOrder>, Map<Integer, String>> searchPair);
}
