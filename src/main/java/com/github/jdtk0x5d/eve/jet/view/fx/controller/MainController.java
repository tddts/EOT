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

package com.github.jdtk0x5d.eve.jet.view.fx.controller;

import com.github.jdtk0x5d.eve.jet.context.events.SearchStatusEvent;
import com.github.jdtk0x5d.eve.jet.view.fx.config.annotations.FXController;
import com.github.jdtk0x5d.eve.jet.view.fx.tools.message.provider.MessageHelper;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@FXController("fxml/main.fxml")
public class MainController {

  @FXML
  private Label searchStatusLabel;
  @FXML
  private ProgressBar searchProgressBar;

  @FXML
  private LoginController headerController;
  @FXML
  private SearchTabController searchTabController;

  @Autowired
  private MessageHelper messageHelper;

  @PostConstruct
  public void init() {
    setStatusText(SearchStatusEvent.NO_ORDERS_FOUND);
  }

  @Subscribe
  private void processSearchStatusEvent(SearchStatusEvent event) {
    Platform.runLater(() -> {
      if (event.isFinished()) {
        searchProgressBar.setProgress(0);
      }
      else {
        searchProgressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
      }
      setStatusText(event);
    });
  }

  private void setStatusText(SearchStatusEvent event) {
    searchStatusLabel.textProperty().setValue(messageHelper.getMessage(event));
  }
}
