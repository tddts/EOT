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

package com.github.tddts.evetrader.service;

import com.github.tddts.evetrader.model.app.OrderSearchRow;

/**
 * {@code UserInterfaceService} represents a service providing access to in-game user interface.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface UserInterfaceService {

  /**
   * Set route in in-game user interface from selling order location to buying order location using given row.
   *
   * @param searchRow order search result row
   */
  void setFullRoute(OrderSearchRow searchRow);

  /**
   * Set waypoint to buy order location in in-game user interface using given row.
   *
   * @param searchRow order search result row
   */
  void setBuyWaypoint(OrderSearchRow searchRow);

  /**
   * Set waypoint to sell order location in in-game user interface using given row.
   *
   * @param searchRow order search result row
   */
  void setSellWaypoint(OrderSearchRow searchRow);

  /**
   * Open market details for item from given row.
   *
   * @param searchRow order search result row
   */
  void openMarketDetails(OrderSearchRow searchRow);
}
