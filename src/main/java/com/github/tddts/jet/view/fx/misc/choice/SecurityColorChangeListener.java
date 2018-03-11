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
