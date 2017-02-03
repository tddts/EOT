package com.github.jdtk0x5d.eve.jet.config.ebean;

import com.avaje.ebeaninternal.server.type.ScalarTypeBaseVarchar;
import com.github.jdtk0x5d.eve.jet.model.api.dotlan.DotlanRoute;
import com.google.gson.Gson;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class ScalarTypeDotlanRoute extends ScalarTypeBaseVarchar<DotlanRoute> {

  private Gson gson = new Gson();

  public ScalarTypeDotlanRoute() {
    super(DotlanRoute.class);
  }

  @Override
  public String formatValue(DotlanRoute value) {
    return gson.toJson(value);
  }

  @Override
  public DotlanRoute parse(String value) {
    return gson.fromJson(value, DotlanRoute.class);
  }

  @Override
  public DotlanRoute convertFromDbString(String dbValue) {
    return gson.fromJson(dbValue, DotlanRoute.class);
  }

  @Override
  public String convertToDbString(DotlanRoute beanValue) {
    return gson.toJson(beanValue);
  }
}
