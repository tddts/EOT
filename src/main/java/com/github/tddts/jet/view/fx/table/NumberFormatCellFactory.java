package com.github.tddts.jet.view.fx.table;

import com.github.tddts.jet.model.app.OrderSearchRow;
import com.github.tddts.tools.fx.table.cell.CustomizableCellFactory;
import com.github.tddts.tools.fx.table.cell.NumberFormatCellUpdater;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class NumberFormatCellFactory extends CustomizableCellFactory<OrderSearchRow, Double> {

  public NumberFormatCellFactory(String pattern) {
    super(new NumberFormatCellUpdater<>(pattern));
  }

}
