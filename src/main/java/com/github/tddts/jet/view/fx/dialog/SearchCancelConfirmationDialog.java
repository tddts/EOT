package com.github.tddts.jet.view.fx.dialog;

import com.github.tddts.sprix.annotations.Message;
import com.github.tddts.sprix.annotations.SprixDialog;
import javafx.scene.control.Alert;

import javax.annotation.PostConstruct;


@SprixDialog()
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
