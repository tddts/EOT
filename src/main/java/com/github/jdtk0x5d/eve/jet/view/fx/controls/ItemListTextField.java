package com.github.jdtk0x5d.eve.jet.view.fx.controls;

import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class ItemListTextField extends TextField {

  public ItemListTextField() {
    setEditable(false);
  }

  public void addItems(String... items) {
    StringBuilder itemsBuilder = new StringBuilder(getText());
    for (String item : items) {
      if (item == null) continue;
      if (!itemsBuilder.toString().contains(item)) {
        if (itemsBuilder.length() > 0) itemsBuilder.append(",");
        itemsBuilder.append(item);
      }
    }
    setText(itemsBuilder.toString());
  }

  public void addItem(String item) {
    if (item == null) return;

    String items = getText();
    if (!items.contains(item)) {
      if (!items.isEmpty()) items = items + ",";
      items = items + item;
    }
    setText(items);
  }

  public List<String> getItems() {
    return getText().isEmpty() ? Collections.emptyList() : Arrays.asList(getText().split(","));
  }
}
