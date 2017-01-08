package com.github.jdtk0x5d.eve.jet.model.db.stat.translation;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "trnTranslationColumns" database table.
 */
@Entity
@Table(name = "\"trnTranslationColumns\"")
@NamedQuery(name = "TrnTranslationColumn.findAll", query = "SELECT t FROM TrnTranslationColumn t")
public class TrnTranslationColumn implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"columnName\"")
  private String columnName;

  @Column(name = "\"masterID\"")
  private String masterID;

  @Column(name = "\"tableName\"")
  private String tableName;

  @Column(name = "\"tcGroupID\"")
  private Integer tcGroupID;

  @Column(name = "\"tcID\"")
  private Integer tcID;

  public TrnTranslationColumn() {
  }

  public String getColumnName() {
    return this.columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getMasterID() {
    return this.masterID;
  }

  public void setMasterID(String masterID) {
    this.masterID = masterID;
  }

  public String getTableName() {
    return this.tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public Integer getTcGroupID() {
    return this.tcGroupID;
  }

  public void setTcGroupID(Integer tcGroupID) {
    this.tcGroupID = tcGroupID;
  }

  public Integer getTcID() {
    return this.tcID;
  }

  public void setTcID(Integer tcID) {
    this.tcID = tcID;
  }

  @Override
  public String toString() {
    return "TrnTranslationColumn{" + "columnName=[" + columnName + "], masterID=[" + masterID + "], tableName=[" + tableName + "], tcGroupID=[" + tcGroupID + "], tcID=[" + tcID + "]}";
  }
}