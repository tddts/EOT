package com.github.jdtk0x5d.eve.jet.model.client.esi.character;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class CharacterName {

  private long character_id;
  private String character_name;

  public long getCharacter_id() {
    return character_id;
  }

  public void setCharacter_id(long character_id) {
    this.character_id = character_id;
  }

  public String getCharacter_name() {
    return character_name;
  }

  public void setCharacter_name(String character_name) {
    this.character_name = character_name;
  }

  @Override
  public String toString() {
    return "CharacterName{" + "character_id=[" + character_id + "], character_name=[" + character_name + "]}";
  }
}
