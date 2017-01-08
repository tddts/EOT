package com.github.jdtk0x5d.eve.jet.model.api.esi.market;

import java.time.LocalDateTime;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class MarketHistory {

  private LocalDateTime date;
  private long volume;
  private int order_count;
  private double average;
  private double lowest;
  private double highest;

  public double getHighest ()
  {
    return highest;
  }

  public void setHighest (double highest)
  {
    this.highest = highest;
  }

  public long getVolume ()
  {
    return volume;
  }

  public void setVolume (long volume)
  {
    this.volume = volume;
  }

  public LocalDateTime getDate ()
  {
    return date;
  }

  public void setDate (LocalDateTime date)
  {
    this.date = date;
  }

  public double getLowest ()
  {
    return lowest;
  }

  public void setLowest (double lowest)
  {
    this.lowest = lowest;
  }

  public int getOrder_count ()
  {
    return order_count;
  }

  public void setOrder_count (int order_count)
  {
    this.order_count = order_count;
  }

  public double getAverage ()
  {
    return average;
  }

  public void setAverage (double average)
  {
    this.average = average;
  }

  @Override
  public String toString() {
    return "MarketHistory{" + "date=[" + date + "], volume=[" + volume + "], order_count=[" + order_count + "], average=[" + average + "], lowest=[" + lowest + "], highest=[" + highest + "]}";
  }
}
