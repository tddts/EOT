package com.github.jdtk0x5d.eve.jet.model.api.esi.universe;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class UniverseType {

  private String icon_id;
  private String type_name;
  private String group_id;
  private String type_description;
  private String category_id;

  public String getIcon_id() {
    return icon_id;
  }

  public void setIcon_id(String icon_id) {
    this.icon_id = icon_id;
  }

  public String getType_name() {
    return type_name;
  }

  public void setType_name(String type_name) {
    this.type_name = type_name;
  }

  public String getGroup_id() {
    return group_id;
  }

  public void setGroup_id(String group_id) {
    this.group_id = group_id;
  }

  public String getType_description() {
    return type_description;
  }

  public void setType_description(String type_description) {
    this.type_description = type_description;
  }

  public String getCategory_id() {
    return category_id;
  }

  public void setCategory_id(String category_id) {
    this.category_id = category_id;
  }

  @Override
  public String toString() {
    return "UniverseType{" + "icon_id=[" + icon_id + "], type_name=[" + type_name + "], group_id=["
        + group_id + "], type_description=[" + type_description + "], category_id=[" + category_id + "]}";
  }
}
