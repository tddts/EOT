package com.github.jdtk0x5d.eve.jet.model.api.dotlan;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DotlanJump {

  private Integer from;
  private Integer to;

  public DotlanJump(Integer from, Integer to) {
    this.from = from;
    this.to = to;
  }

  public Integer getFrom() {
    return from;
  }

  public void setFrom(Integer from) {
    this.from = from;
  }

  public Integer getTo() {
    return to;
  }

  public void setTo(Integer to) {
    this.to = to;
  }

  @Override
  public String toString() {
    return "DotlanJump{" + "from=[" + from + "], to=[" + to + "]}";
  }
}
