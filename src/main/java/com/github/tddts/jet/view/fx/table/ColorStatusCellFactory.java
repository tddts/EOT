package com.github.tddts.jet.view.fx.table;

import com.github.tddts.jet.model.app.OrderSearchRow;
import com.github.tddts.tools.fx.table.cell.CustomizableCellFactory;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class ColorStatusCellFactory extends CustomizableCellFactory<OrderSearchRow, String> {

  public ColorStatusCellFactory(boolean buy) {
    addUpdater(new ColorStatusCellUpdater(buy ? this::getBuySecurity : this::getSellSecurity));

  }

  private float getSellSecurity(OrderSearchRow row) {
    return row == null ? -1f : row.getSearchResultData().getSellSecurity();
  }

  private float getBuySecurity(OrderSearchRow row) {
    return row == null ? -1f : row.getSearchResultData().getBuySecurity();
  }
}
