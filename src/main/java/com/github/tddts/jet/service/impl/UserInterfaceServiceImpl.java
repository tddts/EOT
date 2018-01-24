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

package com.github.tddts.jet.service.impl;

import com.github.tddts.jet.model.app.OrderSearchRow;
import com.github.tddts.jet.rest.client.esi.UserInterfaceClient;
import com.github.tddts.jet.service.UserInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class UserInterfaceServiceImpl implements UserInterfaceService {

  @Autowired
  private UserInterfaceClient userInterfaceClient;

  @Override
  public void setFullRoute(OrderSearchRow searchRow) {
    List<Integer> waypoints = searchRow.getOrderRoute().getWaypoints();
    if (!waypoints.isEmpty()) {
      //Clear old waypoints
      userInterfaceClient.setWaypoint(waypoints.get(0), true, true);
      // Sett all waypoints except for first one
      for (int i = 1; i < waypoints.size(); i++) {
        userInterfaceClient.setWaypoint(waypoints.get(i), false, false);
      }
    }
  }

  @Override
  public void setBuyWaypoint(OrderSearchRow searchRow) {
    List<Integer> waypoints = searchRow.getOrderRoute().getWaypoints();
    if (!waypoints.isEmpty()) {
      userInterfaceClient.setWaypoint(waypoints.get(waypoints.size() - 1), false, false);
    }
  }

  @Override
  public void setSellWaypoint(OrderSearchRow searchRow) {
    List<Integer> waypoints = searchRow.getOrderRoute().getWaypoints();
    if (!waypoints.isEmpty()) {
      userInterfaceClient.setWaypoint(waypoints.get(0), false, false);
    }
  }

  @Override
  public void openMarketDetails(OrderSearchRow searchRow) {
    userInterfaceClient.openMarketDetails(searchRow.getSearchResultData().getTypeId());
  }
}
