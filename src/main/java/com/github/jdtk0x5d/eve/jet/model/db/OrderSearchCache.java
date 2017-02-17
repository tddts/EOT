package com.github.jdtk0x5d.eve.jet.model.db;

import com.github.jdtk0x5d.eve.jet.model.api.esi.market.MarketOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Entity
public class OrderSearchCache {

  @Id
  @Column
  private Long orderId;

  @Column
  private Boolean buyOrder;
  @Column
  private Integer duration;
  @Column
  private Double price;
  @Column
  private String range;

  @Column
  private LocalDateTime issued;

  @Column
  private Integer typeID;
  @Column
  private Long locationID;

  @Column
  private Long minVolume;
  @Column
  private Long volumeRemain;
  @Column
  private Long volumeTotal;

  public OrderSearchCache() {
  }

  public OrderSearchCache(MarketOrder order) {
    this.buyOrder = order.getIs_buy_order();
    this.duration = order.getDuration();
    this.price = order.getPrice();
    this.range = order.getRange();
    this.issued = order.getIssued();
    this.orderId = order.getOrder_id();
    this.typeID = order.getType_id();
    this.locationID = order.getLocation_id();
    this.minVolume = order.getMin_volume();
    this.volumeRemain = order.getVolume_remain();
    this.volumeTotal = order.getVolume_total();
  }

  public Boolean getBuyOrder() {
    return buyOrder;
  }

  public void setBuyOrder(Boolean buyOrder) {
    this.buyOrder = buyOrder;
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getRange() {
    return range;
  }

  public void setRange(String range) {
    this.range = range;
  }

  public LocalDateTime getIssued() {
    return issued;
  }

  public void setIssued(LocalDateTime issued) {
    this.issued = issued;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Integer getTypeID() {
    return typeID;
  }

  public void setTypeID(Integer typeID) {
    this.typeID = typeID;
  }

  public Long getLocationID() {
    return locationID;
  }

  public void setLocationID(Long locationID) {
    this.locationID = locationID;
  }

  public Long getMinVolume() {
    return minVolume;
  }

  public void setMinVolume(Long minVolume) {
    this.minVolume = minVolume;
  }

  public Long getVolumeRemain() {
    return volumeRemain;
  }

  public void setVolumeRemain(Long volumeRemain) {
    this.volumeRemain = volumeRemain;
  }

  public Long getVolumeTotal() {
    return volumeTotal;
  }

  public void setVolumeTotal(Long volumeTotal) {
    this.volumeTotal = volumeTotal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    OrderSearchCache that = (OrderSearchCache) o;

    return orderId.equals(that.orderId);
  }

  @Override
  public int hashCode() {
    return orderId.hashCode();
  }
}
