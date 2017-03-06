package com.github.jdtk0x5d.eve.jet.model.app;

import com.github.jdtk0x5d.eve.jet.consts.DotlanRouteOption;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class SearchParams {

  private long isk;
  private double cargo;
  private double tax;

  private DotlanRouteOption routeOption = DotlanRouteOption.FASTEST;
  private List<String> regions = Collections.emptyList();
  private Consumer<List<OrderSearchRow>> resultConsumer = (res) -> {};

  public long getIsk() {
    return isk;
  }

  public SearchParams setIsk(long isk) {
    this.isk = isk;
    return this;
  }

  public double getCargo() {
    return cargo;
  }

  public SearchParams setCargo(double cargo) {
    this.cargo = cargo;
    return this;
  }

  public double getTax() {
    return tax;
  }

  public SearchParams setTax(double tax) {
    this.tax = tax;
    return this;
  }

  public DotlanRouteOption getRouteOption() {
    return routeOption;
  }

  public SearchParams setRouteOption(DotlanRouteOption routeOption) {
    if (routeOption != null) this.routeOption = routeOption;
    return this;
  }

  public List<String> getRegions() {
    return regions;
  }

  public SearchParams setRegions(List<String> regions) {
    if (regions != null) this.regions = regions;
    return this;
  }

  public Consumer<List<OrderSearchRow>> getResultConsumer() {
    return resultConsumer;
  }

  public SearchParams setResultConsumer(Consumer<List<OrderSearchRow>> resultConsumer) {
    if (resultConsumer != null) this.resultConsumer = resultConsumer;
    return this;
  }

}