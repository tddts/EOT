package com.github.jdtk0x5d.eve.jet.model.db;

import com.github.jdtk0x5d.eve.jet.model.client.esi.market.MarketPrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Entity
public class CachedMarketPrice {

  @Column
  private double averagePrice;
  @Column
  private double adjustedPrice;
  @Id
  @Column
  private double typeId;

  public CachedMarketPrice() {
  }

  public CachedMarketPrice(MarketPrice marketPrice) {
    averagePrice = marketPrice.getAverage_price();
    averagePrice = marketPrice.getAdjusted_price();
    typeId = marketPrice.getType_id();
  }

  public CachedMarketPrice(double averagePrice, double adjustedPrice, double typeId) {
    this.averagePrice = averagePrice;
    this.adjustedPrice = adjustedPrice;
    this.typeId = typeId;
  }

  public double getAveragePrice() {
    return averagePrice;
  }

  public void setAveragePrice(double averagePrice) {
    this.averagePrice = averagePrice;
  }

  public double getAdjustedPrice() {
    return adjustedPrice;
  }

  public void setAdjustedPrice(double adjustedPrice) {
    this.adjustedPrice = adjustedPrice;
  }

  public double getTypeId() {
    return typeId;
  }

  public void setTypeId(double typeId) {
    this.typeId = typeId;
  }
}
