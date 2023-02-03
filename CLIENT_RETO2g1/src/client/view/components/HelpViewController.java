/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.components;

import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller class for help window. It shows a help page, passing through in
 * the parameter the view of where is so when it opens the help window it will
 * be at the part where is the help for that view.
 *
 * @author Henri
 */
public class HelpViewController {

    @FXML
    private WebView webView;

    private static final Logger LOG = Logger.getLogger(HelpViewController.class.getName());

    /**
     * Initializes and show the help window.
     *
     * @param root The FXML document hierarchy root.
     * @param html the html document hiearchy root.
     */
    public void initAndShowStage(Parent root, String html) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Help window");
        stage.setResizable(false);
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setOnShowing(event -> {
            // Initializes window state. It implements behavior for WINDOW_SHOWING type event.
            WebEngine webEngine = webView.getEngine();
            // Load help page.
            webEngine.load(getClass().getResource(String.format(html)).toExternalForm());
        });
        stage.show();
    }
}
