/*
 * Copyright 2018 Tigran Dadaiants
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tddts.evetrader.view.fx.misc.table;

import com.github.tddts.evetrader.consts.SecurityLevel;
import com.github.tddts.evetrader.model.app.OrderSearchRow;
import com.github.tddts.tools.fx.cell.updater.TableCellUpdater;
import javafx.scene.control.TableCell;

import java.util.function.ToDoubleFunction;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class SecurityColorTableCellUpdater implements TableCellUpdater<OrderSearchRow, String> {

  private static final String FX_BACKGROUND_COLOR = "-fx-background-color: ";

  private final ToDoubleFunction<OrderSearchRow> securityFunction;

  public SecurityColorTableCellUpdater(ToDoubleFunction<OrderSearchRow> securityFunction) {
    this.securityFunction = securityFunction;
  }

  @Override
  public void updateItem(TableCell<OrderSearchRow, String> cell, String item, boolean empty) {
    if (item == null) {
      cell.setText(null);
      cell.setStyle(null);
      return;
    }

    cell.setText(item);
    OrderSearchRow row = (OrderSearchRow) cell.getTableRow().getItem();

    if (row == null) return;

    SecurityLevel securityLevel = SecurityLevel.fromValue(securityFunction.applyAsDouble(row));
    if (securityLevel != null) cell.setStyle(FX_BACKGROUND_COLOR + securityLevel.getColor());
  }
}
