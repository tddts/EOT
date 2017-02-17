package com.github.jdtk0x5d.eve.jet.model.db;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Embeddable
public class RouteCachePK {

  @Column
  private Long startPointId;
  @Column
  private Long endPointId;

  public RouteCachePK(Long startPointId, Long endPointId) {
    this.startPointId = startPointId;
    this.endPointId = endPointId;
  }

  public Long getStartPointId() {
    return startPointId;
  }

  public void setStartPointId(Long startPointId) {
    this.startPointId = startPointId;
  }

  public Long getEndPointId() {
    return endPointId;
  }

  public void setEndPointId(Long endPointId) {
    this.endPointId = endPointId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RouteCachePK that = (RouteCachePK) o;

    return (startPointId != null ? startPointId.equals(that.startPointId) : that.startPointId == null)
        && (endPointId != null ? endPointId.equals(that.endPointId) : that.endPointId == null);
  }

  @Override
  public int hashCode() {
    int result = startPointId != null ? startPointId.hashCode() : 0;
    result = 31 * result + (endPointId != null ? endPointId.hashCode() : 0);
    return result;
  }
}
