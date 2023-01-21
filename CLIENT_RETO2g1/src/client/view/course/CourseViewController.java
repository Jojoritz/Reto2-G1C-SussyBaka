/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.course;

import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joritz
 */
public class CourseViewController {

    /**
     * This is the logger of the class
     */
    private static final Logger LOG = Logger.getLogger("view.course.CourseViewController");

    /**
     * The socket to connect to the server
     */
    //private ClientSocket clientSocket;
    /**
     * The stage where the scene is going to be displayed
     */
    private Stage stage = null;

    /**
     * The scene that is going to be displayed
     */
    private Scene scene = null;

    private Stage primaryStage = null;

    /**
     * The table of the window
     */
    @FXML
    private TableView<?> tableCourses;

    /**
     * The column "Name" of the table
     */
    @FXML
    private TableColumn<?, ?> colName;

    /**
     * The column "Creation Date" of the table
     */
    @FXML
    private TableColumn<?, ?> colCreationDate;

    /**
     * The column "Teacher" of the table
     */
    @FXML
    private TableColumn<?, ?> colTeacher;

    /**
     * The column "Subject" of the table
     */
    @FXML
    private TableColumn<?, ?> colSubject;

    /**
     * The button to show all the table data
     */
    @FXML
    private Button btnPrint;

    /**
     * The button to return to the last window
     */
    @FXML
    private Button btnReturn;

    /**
     * The combo box to choose a filter
     */
    @FXML
    private ComboBox<?> cmbxFilter;

    /**
     * The text view of the filter text
     */
    @FXML
    private Label viewFilter;

    /**
     * The text that defines the title of the window
     */
    @FXML
    private Label txtTitle;

    /**
     * The text field to search the selected item in filter
     */
    @FXML
    private TextField txtFilter;

    /**
     * The image next to txtFilter
     */
    @FXML
    private ImageView imageSearch;

    /**
     * The button to delete selected Courses
     */
    @FXML
    private Button btnDelete;

    /**
     * The button to modify selected Courses
     */
    @FXML
    private Button btnModify;

    /**
     * The button to create Courses
     */
    @FXML
    private Button btnCreate;

    /**
     * The text view of Name
     */
    @FXML
    private Label viewCourseName;

    /**
     * The text field to put the Course's Name
     */
    @FXML
    private TextField txtCourseName;

    /**
     * The text view of Creation Date
     *
     */
    @FXML
    private Label viewCreationDate;

    /**
     * The text field to put the Course's Creation Date
     */
    @FXML
    private TextField txtCreatedDate;

    /**
     * The text field to put the Course's Subject
     */
    @FXML
    private TextField txtSubject;

    /**
     * The text view of Subject
     */
    @FXML
    private Label viewSubject;

    /**
     * The text field to put the Course's Teacher
     */
    @FXML
    private TextField txtTeacher;

    /**
     * The text view of Teacher
     */
    @FXML
    private Label viewTeacher;

    /**
     * The button to enter on a Selected Course
     */
    @FXML
    private Button btnEnter;

    /**
     * The button to show the Subjects on the Subject window
     */
    @FXML
    private Button btnShowSubjects;

    /**
     * The button to Join/Register on a Course
     */
    @FXML
    private Button btnJoin;

    /**
     * Initializes the controller class.
     */
    public void initStage(Parent root, Stage primaryStage, String css) {
        LOG.info("Starting the window and setting the components on the screen");
        //Setting the Window
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage = new Stage();
        primaryStage.hide();
        this.primaryStage = primaryStage;

        stage.setOnShowing((event) -> {
            //When the screen launch the onShowing event

            stage.setTitle("Course");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);

            btnCreate.setDisable(true);
            btnDelete.setDisable(true);
            btnModify.setDisable(true);
            btnEnter.setDisable(true);
            btnJoin.setDisable(true);
            btnPrint.setDisable(false);
            btnReturn.setDisable(false);
            btnShowSubjects.setDisable(false);
        });
    }
}
