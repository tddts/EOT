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

package com.github.tddts.evetrader.model.db;


import io.ebean.annotation.Sql;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * {@code ResultOrder} represents a single result of order search.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Entity
@Sql
@Getter
@Setter
@ToString
public class ResultOrder {

  private Integer typeId;

  private Long sellOrderId;
  private Long buyOrderId;

  private Double sellPrice;
  private Double buyPrice;

  private Long sellQuantity;
  private Long buyQuantity;
  private Long buyMinQuantity;
  private Long tradeQuantity;

  private Integer buyLocation;
  private Integer sellLocation;

  private Double sellSecurity;
  private Double buySecurity;

  private Double itemVolume;
  private Double itemCargoVolume;
  private Double itemCargoFreeVolume;

  private Double profit;
  private Double profitPerUnit;

  public ResultOrder() {
  }

  public Double getProfitPerJump(int jumps) {
    if (jumps < 1) jumps = 1;
    return profit / jumps;
  }

  public boolean merge(ResultOrder order) {
    if (this.isSimilar(order) && this.itemCargoFreeVolume >= order.itemCargoFreeVolume) {
      this.itemCargoVolume += order.itemCargoVolume;
      this.itemCargoFreeVolume -= order.itemCargoFreeVolume;
      this.profit += order.profit;
      return true;
    }

    return false;
  }

  public boolean isSimilar(ResultOrder order) {
    if (this == order) return true;

    if (!typeId.equals(order.typeId)) return false;
    if (!sellPrice.equals(order.sellPrice)) return false;
    if (!buyPrice.equals(order.buyPrice)) return false;
    if (!buyLocation.equals(order.buyLocation)) return false;
    return sellLocation.equals(order.sellLocation);
  }
}
