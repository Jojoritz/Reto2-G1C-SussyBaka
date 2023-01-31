/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.principal;

import client.beans.User;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class PrincipalViewController {

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

    private User user;

    private Stage myStage = null;

    private Scene myScene = null;

    private static final Logger LOG = Logger.getLogger(PrincipalViewController.class.getName());

    /**
     * Initializes the controller class.
     *
     * @param root
     * @param primaryStage
     * @param user
     */
    public void initStage(Parent root, Stage primaryStage, User user) {
        LOG.info("Starting the principal view and setting the components on the screen");
        myScene = new Scene(root);
        myStage = new Stage();
        primaryStage.hide();
        this.user = user;

        myStage.setOnShowing((event) -> {

            myStage.setTitle("Ventana Principal");
            myStage.setScene(myScene);
            myStage.setResizable(false);
            myStage.initModality(Modality.WINDOW_MODAL);

            lblLoggedUser.setText(this.user.getLogin());

            btnCourse.setDisable(false);
            btnSubject.setDisable(false);
            btnProfile.setDisable(false);
            btnCloseSession.setDisable(false);
            btnCloseSession.setDefaultButton(true);
        });

        myStage.show();
    }

}
