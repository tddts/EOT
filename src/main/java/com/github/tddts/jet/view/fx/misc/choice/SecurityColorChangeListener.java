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

package com.github.tddts.jet.view.fx.misc.choice;

import com.github.tddts.jet.consts.SecurityLevel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ChoiceBox;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class SecurityColorChangeListener implements ChangeListener<SecurityLevel> {

  private static final String FX_BACKGROUND_COLOR = "-fx-background-color: ";

  private final ChoiceBox<SecurityLevel> choiceBox;

  public SecurityColorChangeListener(ChoiceBox<SecurityLevel> choiceBox) {
    this.choiceBox = choiceBox;
  }

  @Override
  public void changed(ObservableValue<? extends SecurityLevel> observable, SecurityLevel oldValue, SecurityLevel newValue) {
    if (newValue != null) {
      choiceBox.setStyle(FX_BACKGROUND_COLOR + newValue.getColor());
    }
    else {
      choiceBox.setStyle(null);
    }
  }
}
