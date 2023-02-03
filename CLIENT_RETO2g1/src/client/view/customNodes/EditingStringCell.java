/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.customNodes;

import client.beans.Post;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Henri
 */
public class EditingStringCell<K> extends TableCell<K, String> {

    private TextField textField;
    private static final Logger LOG = Logger.getLogger(EditingStringCell.class.getName());
    private final Integer LIMIT;
    private Alert alert;

    public EditingStringCell(Integer limit) {
        this.LIMIT = limit;
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.requestFocus();
            textField.selectAll();
        }

    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText((String) getItem());
        setGraphic(null);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }

    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> arg0,
                        Boolean arg1, Boolean arg2) -> {
                    if (!arg2) {
                        if (textField.getText().length() > LIMIT) {
                            textField.setText(textField.getText().substring(0, LIMIT));
                            alert = new Alert(Alert.AlertType.ERROR, "No puedes escribiar mas de " + LIMIT + " caracteres", ButtonType.OK);
                            alert.showAndWait();
                        } else {
                            commitEdit(textField.getText());
                        }
                    }
                });
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (textField.getText().length() > LIMIT) {
                    textField.setText(textField.getText().substring(0, LIMIT));
                    alert = new Alert(Alert.AlertType.ERROR, "No puedes escribiar mas de " + LIMIT + " caracteres", ButtonType.YES);
                    alert.showAndWait();
                } else {
                    commitEdit(textField.getText());
                }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
