package com.github.jdtk0x5d.eve.jet.dao;

import com.github.jdtk0x5d.eve.jet.model.db.CachedOrder;
import com.github.jdtk0x5d.eve.jet.model.db.ResultOrder;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface OrderDao extends GenericDao<CachedOrder> {

  int removeSoonExpiredOrders(int time);

  int removeDuplicateOrders();

  int removeLargeItemOrders(double volume);

  int removeTooExpensiveOrders(long funds);

  List<ResultOrder> findProfitableOrders(double security, double cargoVolume, double taxRate);
}
