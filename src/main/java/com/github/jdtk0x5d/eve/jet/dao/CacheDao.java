package com.github.jdtk0x5d.eve.jet.dao;

import com.github.jdtk0x5d.eve.jet.model.db.OrderSearchResult;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface CacheDao extends GenericDao {

  int removeSoonExpiredOrders(int time);

  int removeDuplicateOrders();

  int removeLargeItemOrders(double volume);

  int removeTooExpensiveOrders(long funds);

  List<OrderSearchResult> findProfitableOrders(double security, double cargoVolume, double taxRate);

  String findStationSystemName(long station);

}
