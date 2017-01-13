package com.github.jdtk0x5d.eve.jet.api.esi.impl;

import com.github.jdtk0x5d.eve.jet.api.esi.MarketAPI;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.NullOnException;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Profiling;
import com.github.jdtk0x5d.eve.jet.consts.OrderType;
import com.github.jdtk0x5d.eve.jet.model.api.esi.market.MarketHistory;
import com.github.jdtk0x5d.eve.jet.model.api.esi.market.MarketPrice;
import com.github.jdtk0x5d.eve.jet.model.api.esi.market.MarketOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.apiUriBuilder;
import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.apiUrl;
import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.restOperations;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@NullOnException
public class MarketAPIImpl implements MarketAPI {

  @Value("${url.market.region.orders}")
  private String addressOrdersInRegion;

  @Value("${url.market.all.prices}")
  private String addressAllPrices;

  @Value("${url.market.item.history}")
  private String addressItemHistory;

  @Override
  @Profiling
  public List<MarketOrder> getOrders(OrderType orderType, long regionId, int page) {
    String url = apiUriBuilder(addressOrdersInRegion)
        .queryParam("page", page)
        .queryParam("order_type", orderType.getValue())
        .build().toString();

    MarketOrder[] marketOrders = restOperations().getForObject(url, MarketOrder[].class, regionId);
    return Arrays.asList(marketOrders);
  }

  @Override
  public List<MarketPrice> getAllItemPrices() {
    String url = apiUrl(addressAllPrices);
    MarketPrice[] marketPrices = restOperations().getForObject(url, MarketPrice[].class);
    return Arrays.asList(marketPrices);
  }

  @Override
  public List<MarketHistory> getItemHistory(int type_id, int region_id) {
    String url = apiUriBuilder(addressItemHistory)
        .queryParam("type_id", type_id)
        .build().toString();

    MarketHistory[] marketHistory = restOperations().getForObject(url, MarketHistory[].class, region_id);
    return Arrays.asList(marketHistory);
  }

}
