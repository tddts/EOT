package com.github.jdtk0x5d.eve.jet.model.api.esi.universe;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DogmaEffect {

  private String effect_id;
  private String is_default;


  public DogmaEffect() {
  }

  public DogmaEffect(String effect_id, String is_default) {
    this.effect_id = effect_id;
    this.is_default = is_default;
  }

  public String getEffect_id() {
    return effect_id;
  }

  public void setEffect_id(String effect_id) {
    this.effect_id = effect_id;
  }

  public String getIs_default() {
    return is_default;
  }

  public void setIs_default(String is_default) {
    this.is_default = is_default;
  }

  @Override
  public String toString() {
    return "DogmaEffect{" + "effect_id=[" + effect_id + "], is_default=[" + is_default + "]}";
  }
}
