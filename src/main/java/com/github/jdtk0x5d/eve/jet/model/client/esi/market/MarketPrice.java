package com.github.jdtk0x5d.eve.jet.model.client.esi.market;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class MarketPrice {

  private double average_price;
  private double adjusted_price;
  private double type_id;

  public double getAverage_price() {
    return average_price;
  }

  public void setAverage_price(double average_price) {
    this.average_price = average_price;
  }

  public double getAdjusted_price() {
    return adjusted_price;
  }

  public void setAdjusted_price(double adjusted_price) {
    this.adjusted_price = adjusted_price;
  }

  public double getType_id() {
    return type_id;
  }

  public void setType_id(double type_id) {
    this.type_id = type_id;
  }

  @Override
  public String toString() {
    return "MarketPrice{" + "average_price=[" + average_price + "], adjusted_price=[" + adjusted_price + "], type_id=[" + type_id + "]}";
  }
}
