package com.github.tddts.jet.view.fx.misc.table;

import com.github.tddts.jet.model.app.OrderSearchRow;
import com.github.tddts.tools.fx.cell.factory.CustomizableTableCellFactory;
import com.github.tddts.tools.fx.cell.updater.NumberFormatTableCellUpdater;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class NumberFormatTableCellFactory extends CustomizableTableCellFactory<OrderSearchRow, Double> {

  public NumberFormatTableCellFactory(String pattern) {
    super(new NumberFormatTableCellUpdater<>(pattern));
  }

}
