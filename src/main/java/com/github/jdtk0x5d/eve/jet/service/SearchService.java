package com.github.jdtk0x5d.eve.jet.service;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Profiling;
import com.github.jdtk0x5d.eve.jet.model.api.esi.market.MarketOrder;
import com.github.jdtk0x5d.eve.jet.model.app.OrderSearchRow;
import com.github.jdtk0x5d.eve.jet.model.db.OrderSearchCache;

import java.util.Collection;
import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface SearchService {

  List<OrderSearchRow> searchForOrders(double isk, double volume, Collection<String> regions);
}
