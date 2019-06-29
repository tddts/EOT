/*
 * Copyright 2018 Tigran Dadaiants
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

package com.github.tddts.evetrader.rest.client.esi.impl;

import com.github.tddts.evetrader.config.spring.annotations.RestClient;
import com.github.tddts.evetrader.config.spring.annotations.Retry;
import com.github.tddts.evetrader.consts.OrderType;
import com.github.tddts.evetrader.model.client.esi.market.MarketHistory;
import com.github.tddts.evetrader.model.client.esi.market.MarketOrder;
import com.github.tddts.evetrader.model.client.esi.market.MarketPrice;
import com.github.tddts.evetrader.rest.RestResponse;
import com.github.tddts.evetrader.rest.client.esi.MarketClient;
import com.github.tddts.evetrader.rest.provider.RestClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
@RestClient
public class MarketClientImpl implements MarketClient {

  @Value("${path.market.region.orders}")
  private String addressOrdersInRegion;

  @Value("${path.market.all.prices}")
  private String addressAllPrices;

  @Value("${path.market.item.history}")
  private String addressItemHistory;

  @Autowired
  private RestClientTemplate client;

  @Override
  public RestResponse<List<MarketOrder>> getOrders(OrderType orderType, long regionId, int page) {
    String url = client.apiUriBuilder(addressOrdersInRegion)
        .queryParam("page", page)
        .queryParam("order_type", orderType.getValue())
        .build().toString();

    return RestResponse.fromArrayResponse(client.restOperations().getForEntity(url, MarketOrder[].class, regionId));
  }

  @Override
  @Retry
  public RestResponse<List<MarketPrice>> getAllItemPrices() {
    return RestResponse.fromArrayResponse(client.restOperations().getForEntity(client.apiUrl(addressAllPrices), MarketPrice[].class));
  }

  @Override
  public RestResponse<List<MarketHistory>> getItemHistory(int type_id, int region_id) {
    String url = client.apiUriBuilder(addressItemHistory)
        .queryParam("type_id", type_id)
        .build().toString();

    return RestResponse.fromArrayResponse(client.restOperations().getForEntity(url, MarketHistory[].class, region_id));
  }

}
