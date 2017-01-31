package com.github.jdtk0x5d.eve.jet.fx.controller.impl;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Message;
import com.github.jdtk0x5d.eve.jet.config.spring.beans.UserBean;
import com.github.jdtk0x5d.eve.jet.consts.AuthorizationType;
import com.github.jdtk0x5d.eve.jet.context.events.AuthorizationEvent;
import com.github.jdtk0x5d.eve.jet.fx.dialog.DevCredentialsDialog;
import com.github.jdtk0x5d.eve.jet.fx.view.ViewUtil;
import com.github.jdtk0x5d.eve.jet.oauth.EmbeddedServer;
import com.github.jdtk0x5d.eve.jet.service.AuthService;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;


/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class LoginController {

  @Message("login.authorized")
  private String messageAuthorized;
  @Message("login.unauthorized")
  private String messageUnauthorized;
  @Message("msg.cancel")
  private String messageCancel;
  @Message("dialog.auth.expiration")
  private String messageDialogExpiration;

  @FXML
  private HBox headerHbox;
  @FXML
  private ChoiceBox<AuthorizationType> loginChoiceBox;
  @FXML
  private Button loginButton;
  @FXML
  private Label statusLabel;

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private UserBean userBean;
  @Autowired
  private AuthService authService;
  @Autowired
  private EmbeddedServer server;

  @PostConstruct
  private void init() {
    loginChoiceBox.setConverter(new LoginOptionStringConverter());
    loginChoiceBox.setItems(FXCollections.observableArrayList(AuthorizationType.values()));
    loginChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldval, val) -> processLoginTypeChange(val));
    loginChoiceBox.getSelectionModel().selectFirst();
    loginButton.setOnAction(event -> processLogin());

    loginChoiceBox.managedProperty().bind(loginChoiceBox.visibleProperty());
    loginButton.managedProperty().bind(loginButton.visibleProperty());
  }

  private void processLoginTypeChange(AuthorizationType value) {
    userBean.setAuthorizationType(value);
  }

  private void processLogin() {
    AuthorizationType authType = userBean.getAuthorizationType();

    server.start();

    if (authType.isImplicit()) {
      ViewUtil.openWebpage(authService.getLoginPageURI());
    }

    if (authType.isDev()) {
      DevCredentialsDialog dialog = new DevCredentialsDialog(messageCancel);
      Optional<Pair<String, String>> credentialsPair = dialog.showAndWait();

      if (credentialsPair.isPresent()) {
        Pair<String, String> credentials = credentialsPair.get();
        userBean.setClientId(credentials.getKey());
        userBean.setSercretKey(credentials.getValue());

        ViewUtil.openWebpage(authService.getLoginPageURI(userBean.getClientId()));
      }
    }
  }

  @Subscribe
  private void processAuthorizationEvent(AuthorizationEvent authorizationEvent) {
    Platform.runLater(() -> {
      if (authorizationEvent.isAuthorized()) {
        setAuthorizedStatus();
      }
      if (authorizationEvent.isExpired()) {
        setExpiredStatus();
        showExpirationDialog();
      }
    });
  }

  private void setAuthorizedStatus() {
    // Set message
    statusLabel.setText(messageAuthorized);
    // Set color to background and text
    headerHbox.getStyleClass().clear();
    headerHbox.setStyle(null);
    statusLabel.setTextFill(Color.GREEN);
    // Hide elements
    loginChoiceBox.setVisible(false);
    loginButton.setVisible(false);
  }

  private void setExpiredStatus() {
    // Set message
    statusLabel.setText(messageUnauthorized);
    // Set color to background and text
    statusLabel.setTextFill(Color.BLACK);
    headerHbox.setBackground(ViewUtil.BACKGROUND_RED);
    // Show elements
    loginChoiceBox.setVisible(true);
    loginButton.setVisible(true);
  }

  private void showExpirationDialog() {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setHeaderText(messageDialogExpiration);
    alert.showAndWait();
  }


  private class LoginOptionStringConverter extends StringConverter<AuthorizationType> {

    Map<AuthorizationType, String> values = new HashMap<>();

    LoginOptionStringConverter() {
      for (AuthorizationType type : AuthorizationType.values()) {
        values.put(type, messageSource.getMessage(type.getKey(), new Object[0], Locale.getDefault()));
      }
    }

    @Override
    public String toString(AuthorizationType object) {
      return values.get(object);
    }

    @Override
    public AuthorizationType fromString(String string) {
      for (AuthorizationType authorizationType : values.keySet()) {
        if (values.get(authorizationType).equals(string)) {
          return authorizationType;
        }
      }
      return null;
    }
  }

}
