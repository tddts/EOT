package com.github.jdtk0x5d.eve.jet.service;

import com.github.jdtk0x5d.eve.jet.model.app.OrderSearchRow;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface UserInterfaceService {

  void setFullRoute(OrderSearchRow searchRow);

  void setBuyWaypoint(OrderSearchRow searchRow);

  void setSellWaypoint(OrderSearchRow searchRow);

  void openMarketDetails(OrderSearchRow searchRow);
}
