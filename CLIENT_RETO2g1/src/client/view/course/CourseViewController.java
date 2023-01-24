/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.course;

import client.beans.Subject;
import client.beans.Teacher;
import client.beans.enumerations.FilterTypes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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

    private boolean correctDate = false;

    private boolean correctName = false;

    private List<Subject> subjects = null;

    private List<Teacher> teachers = null;

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
    private ComboBox<FilterTypes> cmbxFilter;

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
    private ComboBox<String> cmbxSubject;

    /**
     * The text view of Subject
     */
    @FXML
    private Label viewSubject;

    /**
     * The text field to put the Course's Teacher
     */
    @FXML
    private ComboBox cmbxTeacher;

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
        //scene.getStylesheets().add(css);
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
            cmbxFilter.getItems().addAll(FilterTypes.DATE, FilterTypes.NAME);
            cmbxFilter.getSelectionModel().select(-1);
            Subject sub = new Subject();
            sub.setSubjectId(1);
            sub.setName("Mondongo");
            sub.setLevel("Alto");
            sub.setCentury("VII");
            sub.setType("Difilic");
            subjects = new ArrayList();
            subjects.add(sub);
            for (Subject s : subjects) {
                cmbxSubject.getItems().add(s.getName());
            }
            cmbxSubject.getSelectionModel().select(-1);
            for (Teacher t : teachers) {
                cmbxTeacher.getItems().add(t.getFullName());
            }
            cmbxTeacher.getSelectionModel().select(-1);
        });

        txtCourseName.textProperty().addListener((event) -> {
            //When the text is being modified in the text field
            try {
                //If the course name is empty
                if (txtCourseName.getText().length() <= 0) {
                    throw new Exception("El nombre del curso no debe de estar vacio");
                }
                correctName = true;
                btnCreate.setDisable(!(correctName && correctDate));
            } catch (Exception e) {
                LOG.warning(e.getMessage());
                btnCreate.setDisable(false);
                correctName = false;
            }
        });

        txtCreatedDate.textProperty().addListener((event) -> {
            //When the text is being modified in the text field
            try {
                boolean res = true;
                String date = txtCreatedDate.getText();
                res = validateDate(date);
                if (!res && txtCreatedDate.getText().length() <= 0) {
                    throw new Exception("El formato de fecha no es valido y/o no debe estar vacio");
                }
                correctDate = true;
                btnCreate.setDisable(!(correctName && correctDate));
            } catch (Exception e) {
                LOG.warning(e.getMessage());
                btnCreate.setDisable(false);
                correctDate = false;
            }
        });
        stage.showAndWait();
    }

    private boolean validateDate(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
            format.setLenient(false);
            format.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(CourseViewController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
