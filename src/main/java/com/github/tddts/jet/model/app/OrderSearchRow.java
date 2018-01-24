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

package com.github.tddts.jet.model.app;

import com.github.tddts.jet.model.client.dotlan.DotlanRoute;
import com.github.tddts.jet.model.db.ResultOrder;
import javafx.beans.property.*;
import javafx.util.converter.NumberStringConverter;

/**
 * {@code OrderSearchRow} represents a row in a table with order search results.
 *
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class OrderSearchRow {

  private StringProperty item = new SimpleStringProperty();
  private LongProperty quantity = new SimpleLongProperty();

  private DoubleProperty volume = new SimpleDoubleProperty();
  private DoubleProperty volumeRemain = new SimpleDoubleProperty();

  private StringProperty sellingLocation = new SimpleStringProperty();
  private StringProperty buyingLocation = new SimpleStringProperty();

  private IntegerProperty jumps = new SimpleIntegerProperty();

  private DoubleProperty sellPrice = new SimpleDoubleProperty();
  private DoubleProperty buyPrice = new SimpleDoubleProperty();

  private DoubleProperty profit = new SimpleDoubleProperty();
  private DoubleProperty perJumpProfit = new SimpleDoubleProperty();

  private StringProperty profitText = new SimpleStringProperty();
  private StringProperty perJumpProfitText = new SimpleStringProperty();

  private OrderRoute orderRoute;
  private ResultOrder searchResultData;

  public OrderSearchRow() {
    NumberStringConverter numberStringConverter = new NumberStringConverter("###.##");
    profitText.bindBidirectional(profit, numberStringConverter);
    perJumpProfitText.bindBidirectional(perJumpProfit, numberStringConverter);
  }

  public OrderSearchRow(String typeName, String sellSystem, String buySystem, ResultOrder searchResult, OrderRoute orderRoute) {
    this();
    setItem(typeName);
    setSellingLocation(sellSystem);
    setBuyingLocation(buySystem);

    setQuantity(searchResult.getTradeQuantity());
    setVolume(searchResult.getItemCargoVolume());
    setVolumeRemain(searchResult.getItemCargoFreeVolume());
    setJumps(orderRoute.getJumpsCount());
    setSellPrice(searchResult.getSellPrice());
    setBuyPrice(searchResult.getBuyPrice());
    setProfit(searchResult.getProfit());

    int jumpsCount = orderRoute.getJumpsCount();
    int jumps = jumpsCount > 0 ? jumpsCount : 1;
    setPerJumpProfit(searchResult.getProfit() / jumps);

    this.orderRoute = orderRoute;
    this.searchResultData = searchResult;
  }

  public String getItem() {
    return item.get();
  }

  public void setItem(String item) {
    this.item.set(item);
  }

  public StringProperty itemProperty() {
    return item;
  }

  public long getQuantity() {
    return quantity.get();
  }

  public void setQuantity(long quantity) {
    this.quantity.set(quantity);
  }

  public LongProperty quantityProperty() {
    return quantity;
  }

  public double getVolume() {
    return volume.get();
  }

  public void setVolume(double volume) {
    this.volume.set(volume);
  }

  public DoubleProperty volumeProperty() {
    return volume;
  }

  public double getVolumeRemain() {
    return volumeRemain.get();
  }

  public void setVolumeRemain(double volumeRemain) {
    this.volumeRemain.set(volumeRemain);
  }

  public DoubleProperty volumeRemainProperty() {
    return volumeRemain;
  }

  public String getSellingLocation() {
    return sellingLocation.get();
  }

  public void setSellingLocation(String sellingLocation) {
    this.sellingLocation.set(sellingLocation);
  }

  public StringProperty sellingLocationProperty() {
    return sellingLocation;
  }

  public String getBuyingLocation() {
    return buyingLocation.get();
  }

  public void setBuyingLocation(String buyingLocation) {
    this.buyingLocation.set(buyingLocation);
  }

  public StringProperty buyingLocationProperty() {
    return buyingLocation;
  }

  public int getJumps() {
    return jumps.get();
  }

  public void setJumps(int jumps) {
    this.jumps.set(jumps);
  }

  public IntegerProperty jumpsProperty() {
    return jumps;
  }

  public double getSellPrice() {
    return sellPrice.get();
  }

  public void setSellPrice(double sellPrice) {
    this.sellPrice.set(sellPrice);
  }

  public DoubleProperty sellPriceProperty() {
    return sellPrice;
  }

  public double getBuyPrice() {
    return buyPrice.get();
  }

  public void setBuyPrice(double buyPrice) {
    this.buyPrice.set(buyPrice);
  }

  public DoubleProperty buyPriceProperty() {
    return buyPrice;
  }

  public double getProfit() {
    return profit.get();
  }

  public void setProfit(double profit) {
    this.profit.set(profit);
  }

  public DoubleProperty profitProperty() {
    return profit;
  }

  public double getPerJumpProfit() {
    return perJumpProfit.get();
  }

  public void setPerJumpProfit(double perJumpProfit) {
    this.perJumpProfit.set(perJumpProfit);
  }

  public DoubleProperty perJumpProfitProperty() {
    return perJumpProfit;
  }


  public String getProfitText() {
    return profitText.get();
  }

  public void setProfitText(String profitText) {
    this.profitText.set(profitText);
  }

  public StringProperty profitTextProperty() {
    return profitText;
  }

  public String getPerJumpProfitText() {
    return perJumpProfitText.get();
  }

  public void setPerJumpProfitText(String perJumpProfitText) {
    this.perJumpProfitText.set(perJumpProfitText);
  }

  public StringProperty perJumpProfitTextProperty() {
    return perJumpProfitText;
  }

  public OrderRoute getOrderRoute() {
    return orderRoute;
  }

  public void setOrderRoute(OrderRoute orderRoute) {
    this.orderRoute = orderRoute;
  }

  public ResultOrder getSearchResultData() {
    return searchResultData;
  }

  public void setSearchResultData(ResultOrder searchResultData) {
    this.searchResultData = searchResultData;
  }
}
