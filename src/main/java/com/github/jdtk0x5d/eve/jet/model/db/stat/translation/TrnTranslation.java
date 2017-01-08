package com.github.jdtk0x5d.eve.jet.model.db.stat.translation;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "trnTranslations" database table.
 */
@Entity
@Table(name = "\"trnTranslations\"")
@NamedQuery(name = "TrnTranslation.findAll", query = "SELECT t FROM TrnTranslation t")
public class TrnTranslation implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"keyID\"")
  private Integer keyID;

  @Column(name = "\"languageID\"")
  private String languageID;

  @Column(name = "\"tcID\"")
  private Integer tcID;

  @Column(name = "\"text\"")
  private String text;

  public TrnTranslation() {
  }

  public Integer getKeyID() {
    return this.keyID;
  }

  public void setKeyID(Integer keyID) {
    this.keyID = keyID;
  }

  public String getLanguageID() {
    return this.languageID;
  }

  public void setLanguageID(String languageID) {
    this.languageID = languageID;
  }

  public Integer getTcID() {
    return this.tcID;
  }

  public void setTcID(Integer tcID) {
    this.tcID = tcID;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return "TrnTranslation{" + "keyID=[" + keyID + "], languageID=[" + languageID + "], tcID=[" + tcID + "], text=[" + text + "]}";
  }
}