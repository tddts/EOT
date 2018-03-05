package com.github.tddts.jet.view.fx.table;

import com.github.tddts.jet.consts.SecurityLevel;
import com.github.tddts.jet.model.app.OrderSearchRow;
import com.github.tddts.tools.core.function.ToFloatFunction;
import com.github.tddts.tools.fx.table.cell.CellUpdater;
import javafx.scene.control.TableCell;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class ColorStatusCellUpdater implements CellUpdater<OrderSearchRow, String> {

  private final ToFloatFunction<OrderSearchRow> securityFunction;
  private static final String FX_BACKGROUND_COLOR = "-fx-background-color: ";

  public ColorStatusCellUpdater(ToFloatFunction<OrderSearchRow> securityFunction) {
    this.securityFunction = securityFunction;
  }

  @Override
  public void updateItem(TableCell<OrderSearchRow, String> cell, String item, boolean empty) {
    if (item == null) {
      cell.setText(null);
      cell.setStyle(null);
      cell.getStyleClass().clear();
      return;
    }

    cell.setText(item);
    OrderSearchRow row = (OrderSearchRow) cell.getTableRow().getItem();

    if (row == null) return;

    SecurityLevel securityLevel = SecurityLevel.fromValue(securityFunction.applyAsFloat(row));
    if (securityLevel != null) cell.setStyle(FX_BACKGROUND_COLOR + securityLevel.getColor());
  }
}
