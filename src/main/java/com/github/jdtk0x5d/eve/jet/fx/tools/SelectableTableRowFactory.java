package com.github.jdtk0x5d.eve.jet.fx.tools;

import javafx.collections.ObservableList;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class SelectableTableRowFactory<T> implements Callback<TableView<T>, TableRow<T>> {

  private int index = 0;

  @Override
  public TableRow<T> call(TableView<T> param) {

    TableRow<T> row = new TableRow<>();
    if(index % 5 == 0){
      row.setStyle("-fx-background-color:red");
    }
    index++;
    return row;
  }
}
