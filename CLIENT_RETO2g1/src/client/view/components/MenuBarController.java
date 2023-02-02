/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.components;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Henri
 */
public class MenuBarController {

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menMenu;
    @FXML
    private MenuItem menitmInitialScreen;
    @FXML
    private MenuItem menitmCourses;
    @FXML
    private MenuItem menitmPost;
    @FXML
    private MenuItem menitmSubjects;
    @FXML
    private MenuItem menitmViewProfile;
    @FXML
    private MenuItem menitmCloseSession;
    @FXML
    private Menu menHelp;
    @FXML
    private MenuItem menitmHelp;

    private static Stage stage;

    private static String helpHtml;

    public void setHelpHtml(String url) {
        helpHtml = url;
    }

    /**
     * The logger of this class
     */
    private static final Logger LOG = Logger.getLogger(MenuBarController.class.getName());

    public void initialize() {
        menHelp.showingProperty().addListener((
                observableValue, oldValue, newValue) -> {
            if (newValue) {
                menHelp.getItems().get(0).fire();
            }
        });
    }

    public void setStage(Stage stageParam) {
        stage = stageParam;
    }

    @FXML
    private void handleMenuBarStart(ActionEvent event) {

    }

    @FXML
    private void handleMenuBarCourses(ActionEvent event) {
    }

    @FXML
    private void handleMenuBarPost(ActionEvent event) {
    }

    @FXML
    private void handleMenuBarSubjects(ActionEvent event) {
    }

    @FXML
    private void handleMenuBarProfile(ActionEvent event) {
    }

    @FXML
    private void handleMenuBarLogOff(ActionEvent event) {
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/signIn/SignInView.fxml"));
    }

    @FXML
    private void handleMenuBarHelp(ActionEvent event) {
        try {
            LOG.info("Opening help window...." + helpHtml);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HelpView.fxml"));
            Parent root = (Parent) loader.load();
            HelpViewController helpController = ((HelpViewController) loader.getController());
            helpController.initAndShowStage(root, helpHtml);
        } catch (IOException e) {
            LOG.severe(e.getMessage());
        }
    }
}
