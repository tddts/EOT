package com.github.jdtk0x5d.eve.jet.fx.controller;

import com.github.jdtk0x5d.eve.jet.config.spring.annotations.Message;
import com.github.jdtk0x5d.eve.jet.consts.AuthorizationType;
import com.github.jdtk0x5d.eve.jet.consts.RestDataSource;
import com.github.jdtk0x5d.eve.jet.context.events.AuthorizationEvent;
import com.github.jdtk0x5d.eve.jet.fx.dialog.DevCredentialsDialog;
import com.github.jdtk0x5d.eve.jet.fx.tools.message.MessageStringConverter;
import com.github.jdtk0x5d.eve.jet.fx.view.ViewUtil;
import com.github.jdtk0x5d.eve.jet.service.LoginService;
import com.github.jdtk0x5d.eve.jet.service.UserDataService;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.converter.IntegerStringConverter;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.annotation.PostConstruct;
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
  private ChoiceBox<RestDataSource> dataSourceBox;
  @FXML
  private ChoiceBox<AuthorizationType> loginChoiceBox;
  @FXML
  private TextField characterIdField;
  @FXML
  private Button loginButton;
  @FXML
  private Label loginStatusLabel;

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private LoginService loginService;
  @Autowired
  private UserDataService userDataService;

  @PostConstruct
  private void init() {
    loginChoiceBox.setConverter(new MessageStringConverter<>(messageSource, AuthorizationType.values()));
    loginChoiceBox.setItems(FXCollections.observableArrayList(AuthorizationType.values()));
    loginChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldval, val) -> processLoginTypeChange(val));
    loginChoiceBox.getSelectionModel().selectFirst();
    loginButton.setOnAction(event -> processLogin());

    dataSourceBox.setItems(FXCollections.observableArrayList(RestDataSource.values()));
    dataSourceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldval, val) -> processDataSourceChange(val));
    dataSourceBox.getSelectionModel().selectFirst();

    loginChoiceBox.managedProperty().bind(loginChoiceBox.visibleProperty());
    loginButton.managedProperty().bind(loginButton.visibleProperty());

    characterIdField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    characterIdField.setText(getCharacterId());
    characterIdField.setOnAction(event -> setCharacterId());
  }

  private void processLoginTypeChange(AuthorizationType value) {
    loginService.processLoginTypeChange(value);
  }

  private void processDataSourceChange(RestDataSource restDataSource) {
    loginService.processDataSourceChange(restDataSource);
  }

  private void processLogin() {
    loginService.processLogin(this::getCredentials, ViewUtil::openWebpage);
  }

  private Optional<Pair<String, String>> getCredentials() {
    return new DevCredentialsDialog(messageCancel).showAndWait();
  }

  private String getCharacterId() {
    return userDataService.getCharacterId();
  }

  private void setCharacterId() {
    userDataService.saveCharacterId(characterIdField.getText());
  }

  @Subscribe
  private void processAuthorizationEvent(AuthorizationEvent authorizationEvent) {
    Platform.runLater(() -> {
      if (authorizationEvent.isAuthorized()) {
        setAuthorizedStatus();
      }
      if (authorizationEvent.isExpired()) {
        setExpiredStatus();
      }
    });
  }

  private void setAuthorizedStatus() {
    // Set message
    loginStatusLabel.setText(messageAuthorized);
    // Set color to background and text
    headerHbox.getStyleClass().clear();
    headerHbox.setStyle(null);
    loginStatusLabel.setTextFill(Color.GREEN);
    // Hide elements
    loginChoiceBox.setVisible(false);
    loginButton.setVisible(false);
  }

  private void setExpiredStatus() {
    // Set message
    loginStatusLabel.setText(messageUnauthorized);
    // Set color to background and text
    loginStatusLabel.setTextFill(Color.BLACK);
    headerHbox.setBackground(ViewUtil.BACKGROUND_RED);
    // Show elements
    loginChoiceBox.setVisible(true);
    loginButton.setVisible(true);
  }

}
