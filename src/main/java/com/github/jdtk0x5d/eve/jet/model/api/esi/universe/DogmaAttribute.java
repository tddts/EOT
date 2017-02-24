package com.github.jdtk0x5d.eve.jet.model.api.esi.universe;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DogmaAttribute {

  private String value;
  private String attribute_id;

  public DogmaAttribute() {
  }

  public DogmaAttribute(String value, String attribute_id) {
    this.value = value;
    this.attribute_id = attribute_id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getAttribute_id() {
    return attribute_id;
  }

  public void setAttribute_id(String attribute_id) {
    this.attribute_id = attribute_id;
  }

  @Override
  public String toString() {
    return "DogmaAttribute{" + "value=[" + value + "], attribute_id=[" + attribute_id + "]}";
  }
}
