package com.github.jdtk0x5d.eve.jet.model.db;


import io.ebean.SqlRow;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class OrderSearchResult {

  private Integer typeId;

  private Integer sellOrderId;
  private Integer buyOrderId;

  private Double sellPrice;
  private Double buyPrice;

  private Long sellVolume;
  private Long buyVolume;
  private Long buyMinVolume;
  private Long tradeVolume;

  private Integer buyLocation;
  private Integer sellLocation;

  private Double itemCargoVolume;
  private Double itemCargoFreeVolume;

  private Double profit;

  public OrderSearchResult() {
  }

  public OrderSearchResult(SqlRow sqlRow) {
    typeId = sqlRow.getInteger("type_id");

    sellOrderId = sqlRow.getInteger("sell_order_id");
    buyOrderId = sqlRow.getInteger("buy_order_id");

    sellPrice = sqlRow.getDouble("sell_price");
    buyPrice = sqlRow.getDouble("buy_price");

    sellVolume = sqlRow.getLong("sell_volume");
    buyVolume = sqlRow.getLong("buy_volume");
    buyMinVolume = sqlRow.getLong("buy_min_volume");
    tradeVolume = sqlRow.getLong("trade_volume");

    buyLocation = sqlRow.getInteger("buy_location");
    sellLocation = sqlRow.getInteger("sell_location");

    itemCargoVolume = sqlRow.getDouble("item_cargo_volume");
    itemCargoFreeVolume = sqlRow.getDouble("item_cargo_free_volume");

    profit = sqlRow.getDouble("profit");
  }

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public Integer getSellOrderId() {
    return sellOrderId;
  }

  public void setSellOrderId(Integer sellOrderId) {
    this.sellOrderId = sellOrderId;
  }

  public Integer getBuyOrderId() {
    return buyOrderId;
  }

  public void setBuyOrderId(Integer buyOrderId) {
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

  public Long getBuyMinVolume() {
    return buyMinVolume;
  }

  public void setBuyMinVolume(Long buyMinVolume) {
    this.buyMinVolume = buyMinVolume;
  }

  public Long getTradeVolume() {
    return tradeVolume;
  }

  public void setTradeVolume(Long tradeVolume) {
    this.tradeVolume = tradeVolume;
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

  @Override
  public String toString() {
    return "OrderSearchResult{" + "typeId=[" + typeId + "], sellOrderId=[" + sellOrderId + "], buyOrderId=[" + buyOrderId + "], sellPrice=[" + sellPrice + "], buyPrice=[" + buyPrice + "], sellVolume=[" + sellVolume + "], buyVolume=[" + buyVolume + "], buyMinVolume=[" + buyMinVolume + "], buyLocation=[" + buyLocation + "], sellLocation=[" + sellLocation + "], itemCargoVolume=[" + itemCargoVolume + "], itemCargoFreeVolume=[" + itemCargoFreeVolume + "], profit=[" + profit + "]}";
  }
}
