package com.github.jdtk0x5d.eve.jet.view.fx.dialog;

import com.github.jdtk0x5d.eve.jet.context.Context;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.springframework.context.MessageSource;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
public class ExceptionDialog extends Alert {

  private MessageSource messageSource = Context.getBean(MessageSource.class);

  public ExceptionDialog(Throwable ex) {
    super(AlertType.ERROR);

    if (messageSource != null) init(ex);
  }

  private void init(Throwable ex) {
    setTitle(getLocalizedMessage("dialog.exception.title"));
    setHeaderText(getLocalizedMessage("dialog.exception.header"));
    setContentText(ex.getLocalizedMessage());

    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    ex.printStackTrace(pw);
    String exceptionText = sw.toString();

    Label label = new Label(getLocalizedMessage("dialog.exception.stacktrace"));

    TextArea textArea = new TextArea(exceptionText);
    textArea.setEditable(false);
    textArea.setWrapText(true);

    textArea.setMaxWidth(Double.MAX_VALUE);
    textArea.setMaxHeight(Double.MAX_VALUE);
    GridPane.setVgrow(textArea, Priority.ALWAYS);
    GridPane.setHgrow(textArea, Priority.ALWAYS);

    GridPane expContent = new GridPane();
    expContent.setMaxWidth(Double.MAX_VALUE);
    expContent.add(label, 0, 0);
    expContent.add(textArea, 0, 1);

    getDialogPane().setExpandableContent(expContent);
  }

  private String getLocalizedMessage(String param) {
    return messageSource.getMessage(param, new Object[0], Locale.getDefault());
  }
}
