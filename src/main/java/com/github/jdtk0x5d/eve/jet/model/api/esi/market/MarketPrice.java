package com.github.jdtk0x5d.eve.jet.model.api.esi.market;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class MarketPrice {

  private String average_price;
  private String adjusted_price;
  private String type_id;

  public String getAverage_price() {
    return average_price;
  }

  public void setAverage_price(String average_price) {
    this.average_price = average_price;
  }

  public String getAdjusted_price() {
    return adjusted_price;
  }

  public void setAdjusted_price(String adjusted_price) {
    this.adjusted_price = adjusted_price;
  }

  public String getType_id() {
    return type_id;
  }

  public void setType_id(String type_id) {
    this.type_id = type_id;
  }

  @Override
  public String toString() {
    return "MarketPrice{" + "average_price=[" + average_price + "], adjusted_price=[" + adjusted_price + "], type_id=[" + type_id + "]}";
  }
}
