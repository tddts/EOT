package com.github.tddts.jet.view.fx.dialog;

import com.github.tddts.jet.config.spring.annotations.Message;
import com.github.tddts.jet.view.fx.annotations.FxDialog;
import javafx.scene.control.Alert;

import javax.annotation.PostConstruct;


@FxDialog
public class SearchCancelConfirmationDialog extends Alert {

  @Message("dialog.search.cancel.header")
  private String header;
  @Message("dialog.search.cancel.content")
  private String content;

  public SearchCancelConfirmationDialog() {
    super(AlertType.CONFIRMATION);
  }

  @PostConstruct
  public void init() {
    setHeaderText(header);
    setContentText(content);
  }
}
