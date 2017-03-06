package com.github.jdtk0x5d.eve.jet.fx.controller.impl;

import com.github.jdtk0x5d.eve.jet.context.events.SearchStatusEvent;
import com.github.jdtk0x5d.eve.jet.fx.controller.NestedControllerAware;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class MainController implements NestedControllerAware {

  @FXML
  private Label searchStatusLabel;
  @FXML
  private ProgressBar searchProgressBar;

  @FXML
  private LoginController headerController;
  @FXML
  private SearchTabController searchTabController;

  @Autowired
  private MessageSource messageSource;

  @PostConstruct
  public void init() {
    setStatusText(SearchStatusEvent.NO_ORDERS_FOUND);
  }

  @Override
  public Object[] getNestedControllers() {
    return new Object[]{headerController, searchTabController};
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
    searchStatusLabel.textProperty().setValue(messageSource.getMessage(event.getMessageKey(), new Object[0], Locale.getDefault()));
  }
}
