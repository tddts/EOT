package com.github.jdtk0x5d.eve.jet.service.impl;

import com.github.jdtk0x5d.eve.jet.model.app.OrderSearchRow;
import com.github.jdtk0x5d.eve.jet.rest.client.esi.UserInterfaceClient;
import com.github.jdtk0x5d.eve.jet.service.UserInterfaceService;
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
    List<Integer> waypoints = searchRow.getDotlanRoute().getWaypoints();
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
    List<Integer> waypoints = searchRow.getDotlanRoute().getWaypoints();
    if (!waypoints.isEmpty()) {
      userInterfaceClient.setWaypoint(waypoints.get(waypoints.size() - 1), false, false);
    }
  }

  @Override
  public void setSellWaypoint(OrderSearchRow searchRow) {
    List<Integer> waypoints = searchRow.getDotlanRoute().getWaypoints();
    if (!waypoints.isEmpty()) {
      userInterfaceClient.setWaypoint(waypoints.get(0), false, false);
    }
  }

  @Override
  public void openMarketDetails(OrderSearchRow searchRow) {
    userInterfaceClient.openMarketDetails(searchRow.getSearchResultData().getTypeId());
  }
}
