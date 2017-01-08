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

  public StringProperty itemProperty() {
    return item;
  }

  public void setItem(String item) {
    this.item.set(item);
  }

  public int getQuantity() {
    return quantity.get();
  }

  public IntegerProperty quantityProperty() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity.set(quantity);
  }

  public String getSellingLocation() {
    return sellingLocation.get();
  }

  public StringProperty sellingLocationProperty() {
    return sellingLocation;
  }

  public void setSellingLocation(String sellingLocation) {
    this.sellingLocation.set(sellingLocation);
  }

  public String getBuyingLocation() {
    return buyingLocation.get();
  }

  public StringProperty buyingLocationProperty() {
    return buyingLocation;
  }

  public void setBuyingLocation(String buyingLocation) {
    this.buyingLocation.set(buyingLocation);
  }

  public int getJumps() {
    return jumps.get();
  }

  public IntegerProperty jumpsProperty() {
    return jumps;
  }

  public void setJumps(int jumps) {
    this.jumps.set(jumps);
  }

  public double getProfit() {
    return profit.get();
  }

  public DoubleProperty profitProperty() {
    return profit;
  }

  public void setProfit(double profit) {
    this.profit.set(profit);
  }

  public double getPerJumpProfit() {
    return perJumpProfit.get();
  }

  public DoubleProperty perJumpProfitProperty() {
    return perJumpProfit;
  }

  public void setPerJumpProfit(double perJumpProfit) {
    this.perJumpProfit.set(perJumpProfit);
  }
}
