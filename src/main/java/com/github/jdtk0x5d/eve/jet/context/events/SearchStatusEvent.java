package com.github.jdtk0x5d.eve.jet.context.events;

import com.github.jdtk0x5d.eve.jet.fx.tools.message.MessageAware;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum SearchStatusEvent implements MessageAware {

  CLEARING_CACHE("enum.search.status.clearing.cache"),
  LOADING_PRICES("enum.search.status.loading.prices"),
  LOADING_ORDERS("enum.search.status.loading.orders"),
  FILTERING_ORDERS("enum.search.status.filtering.orders"),
  SEARCHING_FOR_PROFIT("enum.search.status.searching.for.profit"),
  SEARCHING_FOR_ROUTES("enum.search.status.searching.for.routes"),
  NO_ORDERS_FOUND("enum.search.status.no.orders.found"),
  FINISHED("enum.search.status.finished");

  private final String messageKey;

  SearchStatusEvent(String messageKey) {
    this.messageKey = messageKey;
  }

  @Override
  public String getMessageKey() {
    return messageKey;
  }

  public boolean isFinished() {
    return this == FINISHED || this == NO_ORDERS_FOUND;
  }
}
