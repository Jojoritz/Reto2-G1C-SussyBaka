/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.components;

import client.beans.Post;
import client.beans.User;
import client.logic.ControllerFactory;
import client.view.course.CourseViewController;
import client.view.post.PostViewController;
import client.view.principal.PrincipalViewController;
import client.view.signIn.SignInViewController;
import client.view.signUp.SignUpViewController;
import client.view.subject.SubjectsViewController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import javax.ws.rs.core.GenericType;

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

    private static User user;

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

        menitmHelp.setOnAction(event -> {
            try {
                LOG.info("Opening help window....");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("HelpView.fxml"));
                Parent root = (Parent) loader.load();
                HelpViewController helpController = ((HelpViewController) loader.getController());
                helpController.initAndShowStage(root, helpHtml);
            } catch (IOException e) {
                LOG.severe(e.getMessage());
                showError();
            }
        });
        menitmInitialScreen.setOnAction(event -> {
            try {
                LOG.info("Opening main window...");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/principal/PrincipalView.fxml"));
                Parent root = (Parent) loader.load();
                PrincipalViewController principalController = ((PrincipalViewController) loader.getController());
                stage.close();
                principalController.initStage(root, stage);
            } catch (IOException e) {
                LOG.info(e.getMessage());
                showError();
            }
        });
        menitmCourses.setOnAction(event -> {
            try {
                LOG.info("Opening subjects window...");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/course/Courses.fxml"));
                Parent root = (Parent) loader.load();
                CourseViewController courseViewController = ((CourseViewController) loader.getController());
                stage.close();
                courseViewController.initStage(root, stage);
            } catch (IOException e) {
                LOG.severe(e.getMessage());
                showError();
            }
        });
        // This is only a back door to enter to the post, using logged user and course ID 1
        menitmPost.setOnAction(event -> {
            try {
                /*
                user = ControllerFactory.getUserController().getUser_XML(new GenericType<User>() {
                }, "1");
                 */
                List<Post> postList = ControllerFactory.getPostController().
                        getCoursePosts(new GenericType<ArrayList<Post>>() {
                        }, "1");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/post/PostView.fxml"));
                Parent root = (Parent) loader.load();
                PostViewController controller = ((PostViewController) loader.getController());
                controller.setUser(user);
                stage.close();
                controller.initStage(root, ControllerFactory.getPostController(), stage, postList, 1);
            } catch (Exception e) {
                LOG.severe(e.getMessage());
                showError();
            }
        });
        menitmSubjects.setOnAction(event -> {
            try {
                LOG.info("Opening subjects window...");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/subject/Subjects.fxml"));
                Parent root = (Parent) loader.load();
                SubjectsViewController subjectViewController = ((SubjectsViewController) loader.getController());
                stage.close();
                subjectViewController.initStage(root, stage);
            } catch (IOException e) {
                LOG.severe(e.getMessage());
                showError();
            }
        });
        menitmViewProfile.setOnAction(event -> {
            showError();
        });
        menitmCloseSession.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/signIn/SignInView.fxml"));
                Parent root = (Parent) loader.load();
                SignInViewController controller = ((SignInViewController) loader.getController());
                stage.close();
                controller.initStage(root);
            } catch (IOException e) {
                showError();
            }
        });
    }

    private void showError() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Ha sucedido un error al abrir la ventana");
        alert.showAndWait();
    }

    public void setStage(Stage stageParam) {
        stage = stageParam;
    }

    public void setUser(User userLogged) {
        user = userLogged;
    }

}
