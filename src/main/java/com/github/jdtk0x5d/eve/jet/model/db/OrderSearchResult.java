package com.github.jdtk0x5d.eve.jet.model.db;


import io.ebean.SqlRow;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class OrderSearchResult {

  private Long orderId;
  private Long locationId;
  private Double sellPrice;
  private Double buyPrice;
  private Long sellVolume;
  private Long buyVolume;
  private Long volume;
  private Double profit;

  public OrderSearchResult() {
  }

  public OrderSearchResult(SqlRow sqlRow) {
    orderId = sqlRow.getLong("order_id");
    locationId = sqlRow.getLong("location_id");
    sellPrice = sqlRow.getDouble("sell_price");
    buyPrice = sqlRow.getDouble("buy_price");
    sellVolume = sqlRow.getLong("sell_volume");
    buyVolume = sqlRow.getLong("buy_volume");
    volume = sqlRow.getLong("volume");
    profit = sqlRow.getDouble("profit");
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getLocationId() {
    return locationId;
  }

  public void setLocationId(Long locationId) {
    this.locationId = locationId;
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

  public Long getSellVolume() {
    return sellVolume;
  }

  public void setSellVolume(Long sellVolume) {
    this.sellVolume = sellVolume;
  }

  public Long getBuyVolume() {
    return buyVolume;
  }

  public void setBuyVolume(Long buyVolume) {
    this.buyVolume = buyVolume;
  }

  public Long getVolume() {
    return volume;
  }

  public void setVolume(Long volume) {
    this.volume = volume;
  }

  public Double getProfit() {
    return profit;
  }

  public void setProfit(Double profit) {
    this.profit = profit;
  }

  @Override
  public String toString() {
    return "OrderSearchResult{" + "orderId=[" + orderId + "], locationId=[" + locationId + "], sellPrice=["
        + sellPrice + "], buyPrice=[" + buyPrice + "], sellVolume=[" + sellVolume + "], buyVolume=["
        + buyVolume + "], volume=[" + volume + "], profit=[" + profit + "]}";
  }
}
