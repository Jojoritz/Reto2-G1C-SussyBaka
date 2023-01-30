/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.customNodes;

import client.beans.Post;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import jfxtras.scene.control.LocalDateTimePicker;

/**
 *
 * @author Henri
 */
public class EditingDateCell extends TableCell<Post, Date> {

    private LocalDateTimePicker datePicker;
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withZone(ZoneId.systemDefault());

    public EditingDateCell() {
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createDatePicker();
            setText(null);
            setGraphic(datePicker);
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getLocalDate().format(dateFormat));
        setGraphic(null);
    }

    @Override
    public void updateItem(Date item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(getLocalDate().format(dateFormat));
            setGraphic(datePicker);
        }
    }

    private void createDatePicker() {
        datePicker = new LocalDateTimePicker(getLocalDate());
        datePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        datePicker.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                commitEdit(Date.from(datePicker.getLocalDateTime().atZone(ZoneId.systemDefault()).toInstant()));
            }
        });
    }

    private LocalDateTime getLocalDate() {
        return getItem() == null ? LocalDateTime.now() : getItem().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
