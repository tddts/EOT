package com.github.jdtk0x5d.eve.jet.context.events;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum SearchStatusEvent {

  CLEARING_CACHE,
  LOADING_PRICES,
  LOADING_ORDERS,
  FILTERING_ORDERS,
  SEARCHING_FOR_PROFIT,
  SEARCHING_FOR_ROUTES,
  NO_ORDERS_FOUND,
  FINISHED;

  public boolean isFinished() {
    return this == FINISHED || this == NO_ORDERS_FOUND;
  }
}
