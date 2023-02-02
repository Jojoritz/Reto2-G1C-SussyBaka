/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.course;

import client.beans.Course;
import client.beans.Subject;
import client.beans.Teacher;
import client.beans.User;
import client.beans.enumerations.FilterTypes;
import client.beans.enumerations.UserPrivilege;
import client.logic.ControllerFactory;
import client.logic.CourseController;
import client.logic.SubjectController;
import client.logic.UserController;
import client.logic.exception.BusinessLogicException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;

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

    /**
     * The stage of the antecesor window
     */
    private Stage primaryStage = null;

    /**
     * Boolean that verifies if the Date is correct or not
     */
    private boolean correctDate = false;

    /**
     * Boolean that verifies if the Name is correct or not
     */
    private boolean correctName = false;

    /**
     * List for the existing Subjects
     */
    private List<Subject> subjects = null;

    /**
     * List for the existing Teachers
     */
    private List<Teacher> teachers = null;

    /**
     * A list of users for the teachers combobox
     */
    private List<User> teachersData;

    /**
     * A list of subjects for the subjects combobox
     */
    private List<Subject> subjectsData;

    /**
     * A object of SubjectController interface
     */
    private SubjectController subjectController;

    /**
     * A object of UserController interface
     */
    private UserController userController;

    /**
     * Object that confirm if there are teachers
     */
    private User comboSelectedTeacher;

    /**
     * Object that confirm if there are subjects
     */
    private Subject comboSelectedSubject;

    /**
     * List that gets the existing Courses
     */
    private ObservableList<Course> coursesData;

    /**
     *
     */
    private List<User> users;

    /**
     * Course REST Controller
     */
    private CourseController courseController;

    /**
     * The table of the window
     */
    @FXML
    private TableView<Course> tableCourses;

    /**
     * The column "Name" of the table
     */
    @FXML
    private TableColumn<Course, String> colName;

    /**
     * The column "Creation Date" of the table
     */
    @FXML
    private TableColumn<Course, Date> colCreationDate;

    /**
     * The column "Teacher" of the table
     */
    @FXML
    private TableColumn<Course, Teacher> colTeacher;

    /**
     * The column "Subject" of the table
     */
    @FXML
    private TableColumn<Course, Subject> colSubject;

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
     * A alert for showing any alerts and errors to the user
     */
    private Alert alert;

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

        tableCourses.setVisible(true);

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCreationDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCreationDate.setCellFactory(column -> {
            TableCell<Course, Date> cell = new TableCell<Course, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        if (item != null) {
                            setText(format.format(item));
                        }
                    }
                }
            };
            return cell;
        });
        colTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));

        stage.setOnShowing((event) -> {
            try {
                //When the screen launch the onShowing event

                cmbxFilter.getItems().addAll(FilterTypes.FECHA, FilterTypes.NOMBRE);
                cmbxFilter.getSelectionModel().select(-1);

                subjectController = ControllerFactory.getSubjectController();
                subjectsData = FXCollections.observableArrayList(subjectController.findAll_XML(new GenericType<Collection<Subject>>() {
                }));

                subjectsData.stream().forEach(s -> {
                    cmbxSubject.getItems().add(s.getName());
                });

                userController = ControllerFactory.getUserController();
                teachersData = FXCollections.observableArrayList(userController.findAll_XML(new GenericType<Collection<User>>() {
                }));

                teachersData.stream().forEach(t -> {
                    if (t.getPrivilege().equals(UserPrivilege.TEACHER)) {
                        cmbxTeacher.getItems().add(t.getFullName());
                    }
                });

                courseController = ControllerFactory.getCourseController();
                coursesData = FXCollections.observableArrayList(courseController.findAll_XML(new GenericType<Collection<Course>>() {
                }));
                tableCourses.setItems(coursesData);
            } catch (BusinessLogicException ex) {
                LOG.severe(ex.getMessage());
            }
        });

        txtCourseName.textProperty().addListener((event) -> {
            //When the text is being modified in the text field
            try {
                //If the course name is empty
                if (txtCourseName.getText().length() == 0 || txtCourseName.getText().trim().equals("")) {
                    correctName = false;
                    throw new Exception("The course name can't be empty");
                } else {
                    correctName = true;
                }
                btnCreate.setDisable(!(correctName && correctDate && comboSelectedTeacher != null && comboSelectedSubject != null));
            } catch (Exception e) {
                LOG.warning(e.getMessage());
                btnCreate.setDisable(false);
                correctName = false;
            }
        });

        txtCreatedDate.textProperty().addListener((event) -> {
            //When the text is being modified in the text field
            try {
                boolean res = false;
                String date = txtCreatedDate.getText();
                if (date.length() == 10) {
                    res = validateDate(date);
                    if (!res && date.length() == 0 || date.trim().equals("")) {
                        throw new Exception("The Date can't be empty or the Date Format is not valid");
                    }
                    correctDate = true;
                } else {
                    correctDate = false;
                }
                btnCreate.setDisable(!(correctName && correctDate && comboSelectedTeacher != null && comboSelectedSubject != null));
            } catch (Exception e) {
                LOG.warning("The Date is empty or the Date Format is not valid: " + e.getMessage());
                btnCreate.setDisable(false);
                correctDate = false;
            }
        });

        cmbxTeacher.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                teachersData.stream().forEach(t -> {
                    if (t.getFullName().equals(newValue)) {
                        comboSelectedTeacher = t;
                    }
                });
                if (comboSelectedTeacher == null) {
                    throw new Exception();
                }
                btnCreate.setDisable(!(correctName && correctDate && comboSelectedTeacher != null && comboSelectedSubject != null));
            } catch (Exception e) {
                LOG.severe("An error ocurred while getting the teacher data");
                btnCreate.setDisable(true);
                comboSelectedTeacher = null;
            }
        });

        cmbxSubject.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                subjectsData.stream().forEach(s -> {
                    if (s.getName().equals(newValue)) {
                        comboSelectedSubject = s;
                    }
                });
                if (comboSelectedSubject == null) {
                    throw new Exception();
                }
                btnCreate.setDisable(!(correctName && correctDate && comboSelectedTeacher != null && comboSelectedSubject != null));
            } catch (Exception e) {
                LOG.severe("An error ocurred while getting the subject data");
                btnCreate.setDisable(true);
                comboSelectedSubject = null;
            }
        });

        btnReturn.setOnAction(actionEvent -> {
            LOG.info("Closing Window");
            stage.close();
            primaryStage.show();
        });

        stage.setOnCloseRequest(windowEvent -> {
            LOG.info("Opening exit alert confitmation");

            alert = new Alert(Alert.AlertType.CONFIRMATION, "Quieres cerrar el programa?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    LOG.info("Closing the application");
                    Platform.exit();
                } else {
                    LOG.info("Canceled application close");
                    windowEvent.consume();
                }
            });
        });

        btnCreate.setOnAction(actionEvent -> {
            try {
                LOG.info("Creating Course");

                SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
                Date formatedDate = format.parse(txtCreatedDate.getText());

                Course course = new Course();
                course.setName(txtCourseName.getText());
                course.setStartDate(formatedDate);

                Teacher teacher = userController.getTeacher_XML(Teacher.class, comboSelectedTeacher.getId().toString());
                course.setTeacher(teacher);

                Subject subject = subjectController.find_XML(Subject.class, comboSelectedSubject.getSubjectId().toString());
                course.setSubject(subject);

                courseController.create_XML(course);

                clearFields();
                coursesData.add(course);
                btnCreate.setDisable(true);

            } catch (ParseException ex) {
                LOG.severe(ex.getMessage());
            } catch (BusinessLogicException ex) {
                LOG.severe(ex.getMessage());
            }
        });

        stage.showAndWait();
    }

    private boolean validateDate(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
            format.setLenient(false);
            format.parse(date);
        } catch (ParseException ex) {
            LOG.severe(ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * A method for clearing the fields and checkboxes
     */
    private void clearFields() {
        txtCourseName.setText("");
        txtCreatedDate.setText("");
        txtFilter.setText("");
        cmbxFilter.getSelectionModel().select(-1);
        cmbxSubject.getSelectionModel().select(-1);
        cmbxTeacher.getSelectionModel().select(-1);
    }
}