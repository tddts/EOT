/*
 * Copyright 2017 Tigran Dadaiants
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

package com.github.tddts.jet.view.fx.controller;

import com.github.tddts.jet.config.spring.annotations.Message;
import com.github.tddts.jet.consts.AuthorizationType;
import com.github.tddts.jet.consts.RestDataSource;
import com.github.tddts.jet.context.events.AuthorizationEvent;
import com.github.tddts.jet.service.LoginService;
import com.github.tddts.jet.service.UserDataService;
import com.github.tddts.jet.view.fx.annotations.FXController;
import com.github.tddts.jet.view.fx.dialog.DevCredentialsDialog;
import com.github.tddts.jet.view.fx.spring.DialogProvider;
import com.github.tddts.jet.view.fx.tools.message.provider.MessageHelper;
import com.github.tddts.jet.util.ViewUtil;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.converter.IntegerStringConverter;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Optional;


/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@FXController("fxml/header.fxml")
public class LoginController {

  // TODO: remove it, use something else instead
  private static final Background BACKGROUND_RED = new Background(new BackgroundFill(Color.web("#ed4949"), CornerRadii.EMPTY, Insets.EMPTY));

  @Message("login.authorized")
  private String messageAuthorized;
  @Message("login.unauthorized")
  private String messageUnauthorized;
  @Message("dialog.auth.expiration")
  private String messageDialogExpiration;

  @FXML
  private HBox headerHbox;
  @FXML
  private ChoiceBox<RestDataSource> dataSourceBox;
  @FXML
  private ChoiceBox<AuthorizationType> loginChoiceBox;
  @FXML
  private Button loginButton;
  @FXML
  private Label loginStatusLabel;

  @Autowired
  private MessageHelper messageHelper;
  @Autowired
  private LoginService loginService;
  @Autowired
  private UserDataService userDataService;
  @Autowired
  private DialogProvider dialogProvider;

  @PostConstruct
  private void init() {
    loginChoiceBox.setConverter(messageHelper.getConverter(AuthorizationType.values()));
    loginChoiceBox.setItems(FXCollections.observableArrayList(AuthorizationType.values()));
    loginChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldval, val) -> processLoginTypeChange(val));
    loginChoiceBox.getSelectionModel().selectFirst();
    loginButton.setOnAction(event -> processLogin());

    dataSourceBox.setConverter(messageHelper.getConverter(RestDataSource.values()));
    dataSourceBox.setItems(FXCollections.observableArrayList(RestDataSource.values()));
    dataSourceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldval, val) -> processDataSourceChange(val));
    dataSourceBox.getSelectionModel().selectFirst();

    loginChoiceBox.managedProperty().bind(loginChoiceBox.visibleProperty());
    loginButton.managedProperty().bind(loginButton.visibleProperty());
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
    return dialogProvider.getDialog(DevCredentialsDialog.class).showAndWait();
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
    headerHbox.setBackground(BACKGROUND_RED);
    // Show elements
    loginChoiceBox.setVisible(true);
    loginButton.setVisible(true);
  }

}
