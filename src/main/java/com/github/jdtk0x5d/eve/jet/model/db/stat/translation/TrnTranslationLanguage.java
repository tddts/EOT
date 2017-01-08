package com.github.jdtk0x5d.eve.jet.model.db.stat.translation;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "trnTranslationLanguages" database table.
 */
@Entity
@Table(name = "\"trnTranslationLanguages\"")
@NamedQuery(name = "TrnTranslationLanguage.findAll", query = "SELECT t FROM TrnTranslationLanguage t")
public class TrnTranslationLanguage implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "\"languageID\"")
  private String languageID;

  @Column(name = "\"languageName\"")
  private String languageName;

  @Column(name = "\"numericLanguageID\"")
  private Integer numericLanguageID;

  public TrnTranslationLanguage() {
  }

  public String getLanguageID() {
    return this.languageID;
  }

  public void setLanguageID(String languageID) {
    this.languageID = languageID;
  }

  public String getLanguageName() {
    return this.languageName;
  }

  public void setLanguageName(String languageName) {
    this.languageName = languageName;
  }

  public Integer getNumericLanguageID() {
    return this.numericLanguageID;
  }

  public void setNumericLanguageID(Integer numericLanguageID) {
    this.numericLanguageID = numericLanguageID;
  }

  @Override
  public String toString() {
    return "TrnTranslationLanguage{" + "languageID=[" + languageID + "], languageName=[" + languageName + "], numericLanguageID=[" + numericLanguageID + "]}";
  }
}