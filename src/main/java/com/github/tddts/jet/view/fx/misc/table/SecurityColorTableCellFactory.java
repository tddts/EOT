package com.github.tddts.jet.view.fx.misc.table;

import com.github.tddts.jet.model.app.OrderSearchRow;
import com.github.tddts.tools.fx.cell.factory.CustomizableTableCellFactory;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class SecurityColorTableCellFactory extends CustomizableTableCellFactory<OrderSearchRow, String> {

  public SecurityColorTableCellFactory(boolean buy) {
    addUpdater(new SecurityColorTableCellUpdater((row) -> getSecurity(row, buy)));
  }

  private double getSecurity(OrderSearchRow row, boolean buy) {
    if (row == null) return -1f;

    if (buy) {
      return row.getSearchResultData().getBuySecurity();
    }
    else {
      return row.getSearchResultData().getSellSecurity();
    }
  }

}
