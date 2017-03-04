package com.github.jdtk0x5d.eve.jet.service;

import com.github.jdtk0x5d.eve.jet.consts.DotlanRouteOption;
import com.github.jdtk0x5d.eve.jet.model.app.OrderSearchRow;

import java.util.Collection;
import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface SearchService {

  List<OrderSearchRow> searchForOrders(DotlanRouteOption routeOption, long isk, double volume, double taxRate, Collection<String> regions);

  void cleanUp();
}
