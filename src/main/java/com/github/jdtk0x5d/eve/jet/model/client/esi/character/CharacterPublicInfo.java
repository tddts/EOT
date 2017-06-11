package com.github.jdtk0x5d.eve.jet.model.client.esi.character;

import java.time.LocalDateTime;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class CharacterPublicInfo {

  private LocalDateTime birthday;
  private String description;
  private String name;
  private String gender;
  private long corporation_id;
  private int ancestry_id;
  private int bloodline_id;
  private int race_id;


  public LocalDateTime getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDateTime birthday) {
    this.birthday = birthday;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public long getCorporation_id() {
    return corporation_id;
  }

  public void setCorporation_id(long corporation_id) {
    this.corporation_id = corporation_id;
  }

  public int getAncestry_id() {
    return ancestry_id;
  }

  public void setAncestry_id(int ancestry_id) {
    this.ancestry_id = ancestry_id;
  }

  public int getBloodline_id() {
    return bloodline_id;
  }

  public void setBloodline_id(int bloodline_id) {
    this.bloodline_id = bloodline_id;
  }

  public int getRace_id() {
    return race_id;
  }

  public void setRace_id(int race_id) {
    this.race_id = race_id;
  }

  @Override
  public String toString() {
    return "CharacterPublicInfo{" + "birthday=[" + birthday + "], description=[" + description
        + "], name=[" + name + "], gender=[" + gender + "], corporation_id=[" + corporation_id
        + "], ancestry_id=[" + ancestry_id + "], bloodline_id=[" + bloodline_id + "], race_id=[" + race_id + "]}";
  }
}
