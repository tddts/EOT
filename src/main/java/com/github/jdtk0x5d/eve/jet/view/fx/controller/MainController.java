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
