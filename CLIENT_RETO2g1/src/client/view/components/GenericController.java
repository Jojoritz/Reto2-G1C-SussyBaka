/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.components;

import client.beans.User;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.stage.Stage;

/**
 *
 * @author Henri
 */
public class GenericController {

    /**
     */
    protected final Integer MAX_LENGTH = 255;

    /**
     * The stage where the scene is going to be displayed
     */
    protected Stage stage;

    /**
     * The scene that is going to be displayed
     */
    protected Scene scene;

    /**
     * The primary stage when the view opens in modal
     */
    protected Stage primaryStage;

    /**
     * The user object that is logged
     */
    protected static User user;

    protected static final Tooltip tooltip = new Tooltip();

    /**
     * The alert object
     */
    protected Alert alert = null;
    /**
     * Indicates to the tooltip how many is going to move from the original
     * position
     */
    private final Double offset = 35d;

    /**
     * Limit for varchar
     */
    protected final Integer VARCHAR_LIMIT = 10;

    /**
     * Get clipboard system
     */
    protected final Clipboard clipboard = Clipboard.getSystemClipboard();

    /**
     * CSS file
     */
    private final String CSS = "/client/view/css/style.css";

    /**
     * @param msg
     * @param type
     */
    protected void showAlert(String msg, Alert.AlertType type) {
        switch (type) {
            case CONFIRMATION:
                alert = new Alert(type, msg, ButtonType.YES, ButtonType.NO);
                break;
            case INFORMATION:
                alert = new Alert(type, msg, ButtonType.OK);
                break;
            case ERROR:
                alert = new Alert(type, msg, ButtonType.OK);
                break;
            default:
                alert = new Alert(Alert.AlertType.NONE, msg, ButtonType.OK);
                break;
        }
        alert.getDialogPane().getStylesheets().add(
                getClass().getResource(CSS).toExternalForm());
        alert.showAndWait();
    }

    public void setUser(User userLogged) {
        user = userLogged;
    }

    /**
     *
     * @param <T>
     * @param stage
     * @param txtFfield
     * @param text
     * @param tp
     */
    protected <T extends Control> void showTooltip(Stage stage, T txtFfield, String text, Tooltip tp) {
        tp.setText(text);
        //Setting the tooltip to the field and setting that when focus is lost is going to auto hide
        txtFfield.setTooltip(tp);
        tp.setAutoHide(true);

        //Setting the cordinates where is going to be showed
        tp.show(stage,
                txtFfield.getLayoutX() + txtFfield.getScene().getX() + txtFfield.getScene().getWindow().getX(),
                txtFfield.getLayoutY() + txtFfield.getScene().getY() + txtFfield.getScene().getWindow().getY() + offset);
    }

}
