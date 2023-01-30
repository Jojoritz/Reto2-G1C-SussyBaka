/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.components;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
     * The alert object
     */
    protected Alert alert = null;

    /**
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
}
