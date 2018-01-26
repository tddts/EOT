package com.github.tddts.jet.model.client.esi.sso;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class CharacterInfo {

  private String ExpiresOn;
  private long CharacterID;
  private String CharacterOwnerHash;
  private String Scopes;
  private String TokenType;
  private String CharacterName;

  public CharacterInfo() {
  }

  public String getExpiresOn() {
    return ExpiresOn;
  }

  public void setExpiresOn(String expiresOn) {
    this.ExpiresOn = expiresOn;
  }

  public long getCharacterID() {
    return CharacterID;
  }

  public void setCharacterID(long characterID) {
    this.CharacterID = characterID;
  }

  public String getCharacterOwnerHash() {
    return CharacterOwnerHash;
  }

  public void setCharacterOwnerHash(String characterOwnerHash) {
    this.CharacterOwnerHash = characterOwnerHash;
  }

  public String getScopes() {
    return Scopes;
  }

  public void setScopes(String scopes) {
    this.Scopes = scopes;
  }

  public String getTokenType() {
    return TokenType;
  }

  public void setTokenType(String tokenType) {
    this.TokenType = tokenType;
  }

  public String getCharacterName() {
    return CharacterName;
  }

  public void setCharacterName(String characterName) {
    this.CharacterName = characterName;
  }
}
