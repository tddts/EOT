package com.github.jdtk0x5d.eve.jet.rest.api.esi.impl;

import com.github.jdtk0x5d.eve.jet.api.RestResponse;
import com.github.jdtk0x5d.eve.jet.config.spring.annotations.RestApi;
import com.github.jdtk0x5d.eve.jet.consts.OrderType;
import com.github.jdtk0x5d.eve.jet.model.api.esi.market.MarketHistory;
import com.github.jdtk0x5d.eve.jet.model.api.esi.market.MarketOrder;
import com.github.jdtk0x5d.eve.jet.model.api.esi.market.MarketPrice;
import com.github.jdtk0x5d.eve.jet.rest.api.esi.MarketAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.github.jdtk0x5d.eve.jet.util.RequestUtil.*;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestApi
public class MarketAPIImpl implements MarketAPI {

  @Value("${url.market.region.orders}")
  private String addressOrdersInRegion;

  @Value("${url.market.all.prices}")
  private String addressAllPrices;

  @Value("${url.market.item.history}")
  private String addressItemHistory;

  @Override
  public RestResponse<List<MarketOrder>> getOrders(OrderType orderType, long regionId, int page) {
    String url = apiUriBuilder(addressOrdersInRegion)
        .queryParam("page", page)
        .queryParam("order_type", orderType.getValue())
        .build().toString();

    return RestResponse.fromArrayResponse(restOperations().getForEntity(url, MarketOrder[].class, regionId));
  }

  @Override
  public RestResponse<List<MarketPrice>> getAllItemPrices() {
    return RestResponse.fromArrayResponse(restOperations().getForEntity(apiUrl(addressAllPrices), MarketPrice[].class));
  }

  @Override
  public RestResponse<List<MarketHistory>> getItemHistory(int type_id, int region_id) {
    String url = apiUriBuilder(addressItemHistory)
        .queryParam("type_id", type_id)
        .build().toString();

    return RestResponse.fromArrayResponse(restOperations().getForEntity(url, MarketHistory[].class, region_id));
  }

}
