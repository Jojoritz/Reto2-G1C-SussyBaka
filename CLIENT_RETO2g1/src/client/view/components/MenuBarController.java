/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.components;

import client.view.signUp.SignUpViewController;
import client.view.subject.SubjectsViewController;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
        try {
            LOG.info("Opening subjects window...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/subject/Subjects.fxml"));
            Parent root = (Parent) loader.load();
            SubjectsViewController subjectViewController = ((SubjectsViewController) loader.getController());
            subjectViewController.initStage(root, stage);
        } catch (Exception e) {
            LOG.severe("An error happened while opening the subjects window");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ha sucedido un error al abrir la ventana");
            alert.showAndWait();
        }
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
            LOG.info("Opening help window....");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HelpView.fxml"));
            Parent root = (Parent) loader.load();
            HelpViewController helpController = ((HelpViewController) loader.getController());
            helpController.initAndShowStage(root);

        } catch (Exception e) {
            LOG.severe(e.getMessage());
        }
    }
}
