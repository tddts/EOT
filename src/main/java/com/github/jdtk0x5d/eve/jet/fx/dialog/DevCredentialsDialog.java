package com.github.jdtk0x5d.eve.jet.fx.dialog;

import com.github.jdtk0x5d.eve.jet.fx.view.ViewUtil;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.Pair;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class DevCredentialsDialog extends Dialog<Pair<String, String>> {

  public DevCredentialsDialog(String cancelMessage) {
    // Set buttons
    ButtonType cancelButtonType = new ButtonType(cancelMessage, ButtonBar.ButtonData.CANCEL_CLOSE);
    getDialogPane().getButtonTypes().addAll(ButtonType.OK, cancelButtonType);
    // Load fx
    Parent viewRoot = ViewUtil.loadView("fxml/dialog-devc.fxml").getRoot();
    // Set vew as dialog content
    getDialogPane().setContent(viewRoot);
    // Get fields from fx
    TextField clientIdFiel = (TextField) viewRoot.lookup("#clientIdField");
    PasswordField secretKeyField = (PasswordField) viewRoot.lookup("#secretKeyField");
    // Set return value
    setResultConverter(dialogButton -> {
      if (dialogButton == ButtonType.OK) {
        return new Pair<>(clientIdFiel.getText(), secretKeyField.getText());
      }
      return null;
    });
  }
}
