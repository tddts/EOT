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

  private Long sellQuantity;
  private Long buyQuantity;
  private Long buyMinQuantity;
  private Long tradeQuantity;

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

    sellQuantity = sqlRow.getLong("sell_quantity");
    buyQuantity = sqlRow.getLong("buy_quantity");
    buyMinQuantity = sqlRow.getLong("buy_min_quantity");
    tradeQuantity = sqlRow.getLong("trade_quantity");

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
    return "OrderSearchResult{" + "typeId=[" + typeId + "], sellOrderId=[" + sellOrderId + "], buyOrderId=[" + buyOrderId + "], sellPrice=[" + sellPrice + "], buyPrice=[" + buyPrice + "], sellQuantity=[" + sellQuantity + "], buyQuantity=[" + buyQuantity + "], buyMinQuantity=[" + buyMinQuantity + "], buyLocation=[" + buyLocation + "], sellLocation=[" + sellLocation + "], itemCargoVolume=[" + itemCargoVolume + "], itemCargoFreeVolume=[" + itemCargoFreeVolume + "], profit=[" + profit + "]}";
  }
}
