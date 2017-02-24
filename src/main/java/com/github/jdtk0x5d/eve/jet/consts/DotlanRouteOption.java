package com.github.jdtk0x5d.eve.jet.consts;

import com.github.jdtk0x5d.eve.jet.fx.tools.message.MessageAware;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public enum DotlanRouteOption implements MessageAware {

  FASTEST("enum.dotlan.route.option.fastest", "", -1),
  HIGH_SEC("enum.dotlan.route.option.high.sec", "2", 0.5),
  LOW_SEC("enum.dotlan.route.option.low.sec", "3", 0);

  private final String messageKey;
  private final String value;
  private final double security;

  DotlanRouteOption(String messageKey, String value, double security) {
    this.messageKey = messageKey;
    this.value = value;
    this.security = security;
  }

  public String getValue() {
    return value;
  }

  public double getSecurity() {
    return security;
  }

  @Override
  public String getMessageKey() {
    return messageKey;
  }
}
