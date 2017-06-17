package com.github.jdtk0x5d.eve.jet.view.fx.dialog;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Message;
import com.github.jdtk0x5d.eve.jet.view.fx.config.annotations.FXDialog;
import com.github.jdtk0x5d.eve.jet.view.fx.config.annotations.Init;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@FXDialog("fxml/dialog-devc.fxml")
public class DevCredentialsDialog extends Dialog<Pair<String, String>> {

  @FXML
  private TextField clientIdField;
  @FXML
  private PasswordField secretKeyField;

  @Message("msg.cancel")
  private String cancelMessage;

  public DevCredentialsDialog() {
  }

  @Init
  public void init() {
    // Set buttons
    ButtonType cancelButtonType = new ButtonType(cancelMessage, ButtonBar.ButtonData.CANCEL_CLOSE);
    getDialogPane().getButtonTypes().addAll(ButtonType.OK, cancelButtonType);
    // Set return value
    setResultConverter(this::getResult);
  }

  private Pair<String, String> getResult(ButtonType buttonType) {
    if (buttonType == ButtonType.OK) {
      return Pair.of(clientIdField.getText(), secretKeyField.getText());
    }
    return null;
  }
}
