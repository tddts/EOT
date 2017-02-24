package com.github.jdtk0x5d.eve.jet.model.app;

import com.github.jdtk0x5d.eve.jet.model.api.dotlan.DotlanRoute;
import com.github.jdtk0x5d.eve.jet.model.db.OrderSearchResult;
import javafx.beans.property.*;
import javafx.util.converter.NumberStringConverter;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class OrderSearchRow {

  private StringProperty item = new SimpleStringProperty();
  private LongProperty quantity = new SimpleLongProperty();

  private StringProperty sellingLocation = new SimpleStringProperty();
  private StringProperty buyingLocation = new SimpleStringProperty();

  private IntegerProperty jumps = new SimpleIntegerProperty();

  private DoubleProperty sellPrice = new SimpleDoubleProperty();
  private DoubleProperty buyPrice = new SimpleDoubleProperty();

  private DoubleProperty profit = new SimpleDoubleProperty();
  private DoubleProperty perJumpProfit = new SimpleDoubleProperty();

  private StringProperty profitText = new SimpleStringProperty();
  private StringProperty perJumpProfitText = new SimpleStringProperty();

  private DotlanRoute dotlanRoute;
  private OrderSearchResult searchResultData;

  public OrderSearchRow() {
    NumberStringConverter numberStringConverter = new NumberStringConverter("###.##");
    profitText.bindBidirectional(profit, numberStringConverter);
    perJumpProfitText.bindBidirectional(perJumpProfit, numberStringConverter);
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

  public DoubleProperty sellPriceProperty() {
    return sellPrice;
  }

  public void setSellPrice(double sellPrice) {
    this.sellPrice.set(sellPrice);
  }

  public double getBuyPrice() {
    return buyPrice.get();
  }

  public DoubleProperty buyPriceProperty() {
    return buyPrice;
  }

  public void setBuyPrice(double buyPrice) {
    this.buyPrice.set(buyPrice);
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

  public DotlanRoute getDotlanRoute() {
    return dotlanRoute;
  }

  public void setDotlanRoute(DotlanRoute dotlanRoute) {
    this.dotlanRoute = dotlanRoute;
  }

  public OrderSearchResult getSearchResultData() {
    return searchResultData;
  }

  public void setSearchResultData(OrderSearchResult searchResultData) {
    this.searchResultData = searchResultData;
  }
}
