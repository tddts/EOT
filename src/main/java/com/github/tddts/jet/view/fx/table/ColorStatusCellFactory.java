package com.github.tddts.jet.view.fx.table;

import com.github.tddts.jet.model.app.OrderSearchRow;
import com.github.tddts.tools.fx.table.cell.CustomizableCellFactory;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class ColorStatusCellFactory extends CustomizableCellFactory<OrderSearchRow, String> {

  public ColorStatusCellFactory(boolean buy) {
    addUpdater(new ColorStatusCellUpdater((row) -> getSecurity(row, buy)));
  }

  private float getSecurity(OrderSearchRow row, boolean buy) {
    if (row == null) return -1f;

    if (buy) {
      return row.getSearchResultData().getBuySecurity();
    }
    else {
      return row.getSearchResultData().getBuySecurity();
    }
  }

}
