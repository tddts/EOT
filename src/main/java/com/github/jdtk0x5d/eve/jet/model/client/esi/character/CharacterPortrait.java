package com.github.jdtk0x5d.eve.jet.model.client.esi.character;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class CharacterPortrait {

  private String px256x256;
  private String px64x64;
  private String px512x512;
  private String px128x128;

  public String getPx256x256() {
    return px256x256;
  }

  public void setPx256x256(String px256x256) {
    this.px256x256 = px256x256;
  }

  public String getPx64x64() {
    return px64x64;
  }

  public void setPx64x64(String px64x64) {
    this.px64x64 = px64x64;
  }

  public String getPx512x512() {
    return px512x512;
  }

  public void setPx512x512(String px512x512) {
    this.px512x512 = px512x512;
  }

  public String getPx128x128() {
    return px128x128;
  }

  public void setPx128x128(String px128x128) {
    this.px128x128 = px128x128;
  }

  @Override
  public String toString() {
    return "CharacterPortrait{" + "px256x256=[" + px256x256 + "], px64x64=[" + px64x64 + "], px512x512=[" + px512x512 + "], px128x128=[" + px128x128 + "]}";
  }
}
