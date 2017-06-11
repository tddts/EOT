package com.github.jdtk0x5d.eve.jet.model.client.esi.universe;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class UniverseName {

  private String category;
  private String name;
  private int id;

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "UniverseName{" + "category=[" + category + "], name=[" + name + "], id=[" + id + "]}";
  }
}
