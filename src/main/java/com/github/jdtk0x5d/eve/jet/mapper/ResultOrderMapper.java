/*
 * Copyright 2017 Tigran Dadaiants
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

package com.github.jdtk0x5d.eve.jet.mapper;

import com.github.jdtk0x5d.eve.jet.model.db.ResultOrder;
import io.ebean.SqlRow;
import org.springframework.stereotype.Component;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Component
public class ResultOrderMapper implements Mapper<ResultOrder> {

  @Override
  public ResultOrder convert(SqlRow sqlRow) {
    ResultOrder order = new ResultOrder();

    order.setTypeId(sqlRow.getInteger("type_id"));

    order.setSellOrderId(sqlRow.getInteger("sell_order_id"));
    order.setBuyOrderId(sqlRow.getInteger("buy_order_id"));

    order.setSellPrice(sqlRow.getDouble("sell_price"));
    order.setBuyPrice(sqlRow.getDouble("buy_price"));

    order.setSellQuantity(sqlRow.getLong("sell_quantity"));
    order.setBuyQuantity(sqlRow.getLong("buy_quantity"));
    order.setBuyMinQuantity(sqlRow.getLong("buy_min_quantity"));
    order.setTradeQuantity(sqlRow.getLong("trade_quantity"));

    order.setBuyLocation(sqlRow.getInteger("buy_location"));
    order.setSellLocation(sqlRow.getInteger("sell_location"));

    order.setItemCargoVolume(sqlRow.getDouble("item_cargo_volume"));
    order.setItemCargoFreeVolume(sqlRow.getDouble("item_cargo_free_volume"));

    order.setProfit(sqlRow.getDouble("profit"));
    return order;
  }
}
