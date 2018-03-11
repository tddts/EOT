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

package com.github.tddts.jet.model.db;


import io.ebean.annotation.Sql;

import javax.persistence.Entity;

/**
 * {@code ResultOrder} represents a single result of order search.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Entity
@Sql
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

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public Long getSellOrderId() {
    return sellOrderId;
  }

  public void setSellOrderId(Long sellOrderId) {
    this.sellOrderId = sellOrderId;
  }

  public Long getBuyOrderId() {
    return buyOrderId;
  }

  public void setBuyOrderId(Long buyOrderId) {
    this.buyOrderId = buyOrderId;
  }

  public Double getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(Double sellPrice) {
    this.sellPrice = sellPrice;
  }

  public Double getBuyPrice() {
    return buyPrice;
  }

  public void setBuyPrice(Double buyPrice) {
    this.buyPrice = buyPrice;
  }

  public Long getSellQuantity() {
    return sellQuantity;
  }

  public void setSellQuantity(Long sellQuantity) {
    this.sellQuantity = sellQuantity;
  }

  public Long getBuyQuantity() {
    return buyQuantity;
  }

  public void setBuyQuantity(Long buyQuantity) {
    this.buyQuantity = buyQuantity;
  }

  public Long getBuyMinQuantity() {
    return buyMinQuantity;
  }

  public void setBuyMinQuantity(Long buyMinQuantity) {
    this.buyMinQuantity = buyMinQuantity;
  }

  public Long getTradeQuantity() {
    return tradeQuantity;
  }

  public void setTradeQuantity(Long tradeQuantity) {
    this.tradeQuantity = tradeQuantity;
  }

  public Integer getBuyLocation() {
    return buyLocation;
  }

  public void setBuyLocation(Integer buyLocation) {
    this.buyLocation = buyLocation;
  }

  public Integer getSellLocation() {
    return sellLocation;
  }

  public void setSellLocation(Integer sellLocation) {
    this.sellLocation = sellLocation;
  }

  public Double getSellSecurity() {
    return sellSecurity;
  }

  public void setSellSecurity(Double sellSecurity) {
    this.sellSecurity = sellSecurity;
  }

  public Double getBuySecurity() {
    return buySecurity;
  }

  public void setBuySecurity(Double buySecurity) {
    this.buySecurity = buySecurity;
  }

  public Double getItemVolume() {
    return itemVolume;
  }

  public void setItemVolume(Double itemVolume) {
    this.itemVolume = itemVolume;
  }

  public Double getItemCargoVolume() {
    return itemCargoVolume;
  }

  public void setItemCargoVolume(Double itemCargoVolume) {
    this.itemCargoVolume = itemCargoVolume;
  }

  public Double getItemCargoFreeVolume() {
    return itemCargoFreeVolume;
  }

  public void setItemCargoFreeVolume(Double itemCargoFreeVolume) {
    this.itemCargoFreeVolume = itemCargoFreeVolume;
  }

  public Double getProfit() {
    return profit;
  }

  public void setProfit(Double profit) {
    this.profit = profit;
  }

  public Double getProfitPerUnit() {
    return profitPerUnit;
  }

  public void setProfitPerUnit(Double profitPerUnit) {
    this.profitPerUnit = profitPerUnit;
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

  @Override
  public String toString() {
    return "ResultOrder{" + "typeId=[" + typeId + "], sellOrderId=[" + sellOrderId + "], buyOrderId=[" + buyOrderId + "], sellPrice=[" + sellPrice + "], buyPrice=[" + buyPrice + "], sellQuantity=[" + sellQuantity + "], buyQuantity=[" + buyQuantity + "], buyMinQuantity=[" + buyMinQuantity + "], buyLocation=[" + buyLocation + "], sellLocation=[" + sellLocation + "], itemCargoVolume=[" + itemCargoVolume + "], itemCargoFreeVolume=[" + itemCargoFreeVolume + "], profit=[" + profit + "]}";
  }
}
