package com.github.jdtk0x5d.eve.jet.model.app;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class OrderSearchRow {

  private StringProperty item;
  private IntegerProperty quantity;
  private StringProperty sellingLocation;
  private StringProperty buyingLocation;
  private IntegerProperty jumps;
  private DoubleProperty profit;
  private DoubleProperty perJumpProfit;

  public String getItem() {
    return item.get();
  }

  public void setItem(String item) {
    this.item.set(item);
  }

  public StringProperty itemProperty() {
    return item;
  }

  public int getQuantity() {
    return quantity.get();
  }

  public void setQuantity(int quantity) {
    this.quantity.set(quantity);
  }

  public IntegerProperty quantityProperty() {
    return quantity;
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
}
