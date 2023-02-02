/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.beans.Course;
import client.beans.Post;
import client.beans.Student;
import client.beans.Teacher;
import client.beans.User;
import client.beans.enumerations.UserPrivilege;
import client.logic.ControllerFactory;
import client.logic.PostController;
import client.logic.exception.BusinessLogicException;
import client.view.post.PostViewController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Joritz
 */
public class MainTest extends Application {

    private static final Logger LOG = Logger.getLogger(MainTest.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            User user = new User();
            user.setPrivilege(UserPrivilege.TEACHER);
            user.setId(2);
            //user.setPrivilege(UserPrivilege.TEACHER);
            PostController postController = ControllerFactory.getPostController();
            List<Post> postList = postController.
                    getCoursePosts(new GenericType<ArrayList<Post>>() {
                    }, "1");

            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/post/PostView.fxml"));
            Parent root = (Parent) loader.load();
            PostViewController controller = ((PostViewController) loader.getController());
            controller.setUser(user);
            controller.initStage(root, postController, stage, postList, 1);
        } catch (IOException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BusinessLogicException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
