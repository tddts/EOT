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

package com.github.tddts.jet.model.db;

import com.github.tddts.jet.model.client.esi.market.MarketOrder;
import io.ebean.annotation.Index;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * {@code CachedOrder} represents a market order cached in database.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Table
@Entity
public class CachedOrder {

  @Id
  @Column(unique = true)
  private Long orderId;

  @Index
  @Column
  private Long locationID;

  @Index
  @Column
  private Integer typeID;

  @Column
  private Boolean buyOrder;
  @Column
  private Integer duration;
  @Index
  @Column
  private Double price;
  @Column
  private String range;

  @Column
  private LocalDateTime issued;

  @Column
  private Long minVolume;
  @Column
  private Long volumeRemain;
  @Column
  private Long volumeTotal;

  public CachedOrder() {
  }

  public CachedOrder(MarketOrder order) {
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

    CachedOrder that = (CachedOrder) o;

    return orderId.equals(that.orderId);
  }

  @Override
  public int hashCode() {
    return orderId.hashCode();
  }
}
