package com.github.jdtk0x5d.eve.jet.model.api.esi.input;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class IdArray {

  private long[] ids;

  public IdArray(long[] ids) {
    this.ids = ids;
  }

  public long[] getIds() {
    return ids;
  }

  public void setIds(long[] ids) {
    this.ids = ids;
  }
}
