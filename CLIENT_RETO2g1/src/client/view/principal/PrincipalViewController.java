/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.principal;

import client.beans.User;
import client.view.components.GenericController;
import client.view.components.MenuBarController;
import client.view.course.CourseViewController;
import client.view.signIn.SignInViewController;
import client.view.subject.SubjectsViewController;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Henri
 */
public class PrincipalViewController extends GenericController {

    @FXML
    private Label lblLoggedUser;
    @FXML
    private Button btnCourse;
    @FXML
    private Button btnSubject;
    @FXML
    private Button btnProfile;
    @FXML
    private Button btnCloseSession;
    @FXML
    private MenuBar menuBarContainer;
    @FXML
    private final MenuBarController menuBarController = new MenuBarController();

    private static final Logger LOG = Logger.getLogger(PrincipalViewController.class.getName());

    private static Stage mainStage;

    /**
     * Initializes the controller class.
     *
     * @param root the parent scene
     * @param primaryStage the principal stage
     */
    public void initStage(Parent root, Stage primaryStage) {
        LOG.info("Starting the principal view and setting the components on the screen");
        scene = new Scene(root);
        mainStage = new Stage();
        primaryStage.hide();
        this.setUser(user);
        menuBarController.setStage(mainStage);
        menuBarController.setUser(user);

        mainStage.setOnShowing((event) -> {

            mainStage.setTitle("Ventana Principal");
            mainStage.setScene(scene);
            mainStage.setResizable(false);
            if (!mainStage.isShowing()) {
                mainStage.initModality(Modality.WINDOW_MODAL);
            }

            lblLoggedUser.setText(user.getLogin());
            btnCourse.setDisable(false);
            btnSubject.setDisable(false);
            btnProfile.setDisable(false);
            btnCloseSession.setDisable(false);
            btnCloseSession.setDefaultButton(true);
        });

        btnCourse.setOnAction(event -> {
            try {
                LOG.info("Opening subjects window...");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/course/Courses.fxml"));
                Parent root2 = (Parent) loader.load();
                CourseViewController courseViewController = ((CourseViewController) loader.getController());
                mainStage.hide();
                courseViewController.setUser(user);
                courseViewController.initStage(root2, mainStage);
            } catch (IOException e) {
                LOG.severe(e.getMessage());
                showAlert(e.getMessage(), Alert.AlertType.ERROR);
            }
        });

        btnSubject.setOnAction(event -> {
            try {
                LOG.info("Opening subjects window...");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/subject/Subjects.fxml"));
                Parent root2 = (Parent) loader.load();
                SubjectsViewController subjectViewController = ((SubjectsViewController) loader.getController());
                mainStage.hide();
                subjectViewController.initStage(root2, mainStage);
            } catch (IOException e) {
                LOG.severe(e.getMessage());
                showAlert(e.getMessage(), Alert.AlertType.ERROR);
            }
        });

        btnProfile.setOnAction(event -> {
            showAlert("No se puede abrir esta ventana", Alert.AlertType.ERROR);
        });

        btnCloseSession.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/signIn/SignInView.fxml"));
                Parent root2 = (Parent) loader.load();
                SignInViewController controller = ((SignInViewController) loader.getController());
                mainStage.close();
                controller.initStage(root2);
            } catch (IOException e) {
                LOG.severe(e.getMessage());
                showAlert("Error al intentar abrir la ventana", Alert.AlertType.ERROR);
            }
        });

        mainStage.show();
    }

}
