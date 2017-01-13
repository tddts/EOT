package com.github.jdtk0x5d.eve.jet.rest.api.esi;

import com.github.jdtk0x5d.eve.jet.consts.OrderType;
import com.github.jdtk0x5d.eve.jet.model.api.esi.market.MarketHistory;
import com.github.jdtk0x5d.eve.jet.model.api.esi.market.MarketPrice;
import com.github.jdtk0x5d.eve.jet.model.api.esi.market.MarketOrder;
import org.springframework.web.client.RestClientException;

import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public interface MarketAPI {

  List<MarketOrder> getOrders(OrderType orderType, long regionId, int page) throws RestClientException;

  List<MarketPrice> getAllItemPrices() throws RestClientException;

  List<MarketHistory> getItemHistory(int type_id, int region_id) throws RestClientException;
}
