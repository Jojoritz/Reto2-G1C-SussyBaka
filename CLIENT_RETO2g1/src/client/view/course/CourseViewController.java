/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.course;

import client.beans.Course;
import client.beans.Post;
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
import client.view.components.GenericController;
import client.view.components.MenuBarController;
import client.view.post.PostViewController;
import client.view.subject.SubjectsViewController;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.util.Callback;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Joritz
 */
public class CourseViewController extends GenericController {

    /**
     * This is the logger of the class
     */
    private static final Logger LOG = Logger.getLogger("view.course.CourseViewController");

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

    @FXML
    private Button btnSearchCourse;

    /**
     * A alert for showing any alerts and errors to the user
     */
    private Alert alert;

    /**
     * The Applyed filter
     */
    private FilterTypes filterToApply;

    /**
     * Controller of the menu bar
     */
    private final MenuBarController menuBarController = new MenuBarController();

    /**
     * Initializes the controller class.
     *
     * @param root the parent scecne
     * @param primaryStage the parent stage
     */
    public void initStage(Parent root, Stage primaryStage) {
        LOG.info("Starting the window and setting the components on the screen");
        //Setting the Window
        scene = new Scene(root);
        stage = new Stage();
        //Hide the last window
        primaryStage.hide();
        this.primaryStage = primaryStage;
        stage.setTitle("Course");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        menuBarController.setStage(stage);

        //Disable & Enable the buttons
        btnCreate.setDisable(true);
        btnDelete.setDisable(true);
        btnModify.setDisable(true);
        btnEnter.setDisable(true);
        btnJoin.setDisable(true);
        btnPrint.setDisable(false);
        btnReturn.setDisable(false);
        btnSearchCourse.setDisable(false);
        btnShowSubjects.setDisable(false);

        //Show the table
        tableCourses.setVisible(true);

        //Set the Cell values
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCreationDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        //Let the Date to be visible in the table
        Callback<TableColumn<Course, Date>, TableCell<Course, Date>> dateCellFactory = new Callback<TableColumn<Course, Date>, TableCell<Course, Date>>() {
            @Override
            public TableCell call(final TableColumn<Course, Date> param) {
                return new TableCell<Course, Date>() {
                    private final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                    @Override
                    protected void updateItem(Date item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(format.format(item));
                        }
                    }
                };
            }
        };

        colCreationDate.setCellFactory(dateCellFactory);
        colTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));

        stage.setOnShowing(
                (event) -> {
                    try {
                        //When the screen launch the onShowing event
                        //Charge the Filter options
                        cmbxFilter.getItems().addAll(FilterTypes.FECHA, FilterTypes.NOMBRE);
                        cmbxFilter.getSelectionModel().select(-1);

                        //Charge the Subjects on the combobox
                        subjectController = ControllerFactory.getSubjectController();
                        subjectsData = FXCollections.observableArrayList(subjectController.findAll_XML(new GenericType<Collection<Subject>>() {
                        }));

                        subjectsData.stream().forEach(s -> {
                            cmbxSubject.getItems().add(s.getName());
                        });

                        //Charge the Teachers on the combobox
                        userController = ControllerFactory.getUserController();
                        teachersData = FXCollections.observableArrayList(userController.findAll_XML(new GenericType<Collection<User>>() {
                        }));

                        teachersData.stream().forEach(t -> {
                            if (t.getPrivilege().equals(UserPrivilege.TEACHER)) {
                                cmbxTeacher.getItems().add(t.getFullName());
                            }
                        });

                        //Charge the courses on the table
                        courseController = ControllerFactory.getCourseController();
                        coursesData = FXCollections.observableArrayList(courseController.findAll_XML(new GenericType<Collection<Course>>() {
                        }));
                        tableCourses.setItems(coursesData);
                    } catch (BusinessLogicException ex) {
                        LOG.severe(ex.getMessage());
                    }
                }
        );

        /**
         * Verify that the course name is filled
         *
         */
        txtCourseName.textProperty()
                .addListener((event) -> {
                    //When the text is being modified in the text field
                    try {
                        //If the course name is empty
                        if (txtCourseName.getText().length() == 0 || txtCourseName.getText().trim().equals("")) {
                            correctName = false;
                            throw new Exception("The course name can't be empty");
                            //If the course is not empty
                        } else {
                            correctName = true;
                        }
                        //The button disables if the name isn't filled
                        btnCreate.setDisable(!(correctName && correctDate && comboSelectedTeacher != null && comboSelectedSubject != null));
                    } catch (Exception e) {
                        LOG.warning(e.getMessage());
                        btnCreate.setDisable(false);
                        correctName = false;
                    }
                }
                );

        /**
         * Verify that the course date is filled
         *
         */
        txtCreatedDate.textProperty()
                .addListener((event) -> {
                    //When the text is being modified in the text field
                    try {
                        boolean res = false;
                        String date = txtCreatedDate.getText();
                        //The date needs to be 10 digits long (01/01/2000)
                        if (date.length() == 10) {
                            res = validateDate(date);
                            //If the course date is empty
                            if (!res && date.length() == 0 || date.trim().equals("")) {
                                throw new Exception("The Date can't be empty or the Date Format is not valid");
                            }
                            correctDate = true;
                            //If the course date is not empty
                        } else {
                            correctDate = false;
                        }
                        //The button disables if the date isn't filled
                        btnCreate.setDisable(!(correctName && correctDate && comboSelectedTeacher != null && comboSelectedSubject != null));
                    } catch (Exception e) {
                        LOG.warning("The Date is empty or the Date Format is not valid: " + e.getMessage());
                        btnCreate.setDisable(false);
                        correctDate = false;
                    }
                }
                );

        /**
         * Verify that the combobox has items selected
         *
         */
        cmbxTeacher.getSelectionModel()
                .selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    try {
                        teachersData.stream().forEach(t -> {
                            //Get the selected item
                            if (t.getFullName().equals(newValue)) {
                                comboSelectedTeacher = t;
                            }
                        });
                        //Check if the item is really selected
                        if (comboSelectedTeacher == null) {
                            throw new Exception();
                        }
                        //The button disables if the combobox is empty
                        btnCreate.setDisable(!(correctName && correctDate && comboSelectedTeacher != null && comboSelectedSubject != null));
                    } catch (Exception e) {
                        LOG.severe("An error ocurred while getting the teacher data");
                        btnCreate.setDisable(true);
                        comboSelectedTeacher = null;
                    }
                }
                );

        /**
         * Verify that the combobox has items selected
         *
         */
        cmbxSubject.getSelectionModel()
                .selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    try {
                        subjectsData.stream().forEach(s -> {
                            //Get the selected item
                            if (s.getName().equals(newValue)) {
                                comboSelectedSubject = s;
                            }
                        });
                        //Check if the item is really selected
                        if (comboSelectedSubject == null) {
                            throw new Exception();
                        }
                        //The button disables if the combobox is empty
                        btnCreate.setDisable(!(correctName && correctDate && comboSelectedTeacher != null && comboSelectedSubject != null));
                    } catch (Exception e) {
                        LOG.severe("An error ocurred while getting the subject data");
                        btnCreate.setDisable(true);
                        comboSelectedSubject = null;
                    }
                }
                );

        /**
         * Verify that the combobox has filters selected
         *
         */
        cmbxFilter.getSelectionModel()
                .selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    LOG.info("Selecting the filter");
                    //Get the selected filter
                    if (newValue.equals(FilterTypes.NOMBRE)) {
                        filterToApply = FilterTypes.NOMBRE;
                    } else if (newValue.equals(FilterTypes.FECHA)) {
                        filterToApply = FilterTypes.FECHA;
                    } else {
                        filterToApply = null;
                    }
                }
                );

        /**
         * Searches the course with the filter aplyed
         *
         */
        btnSearchCourse.setOnAction(actionEvent
                -> {
            try {
                LOG.info("Searching Courses");
                //There is no filter selected
                if (filterToApply == null) {
                    try {
                        LOG.info("There is no filter to apply");
                        coursesData.clear();
                        coursesData = FXCollections.observableArrayList(courseController.findAll_XML(new GenericType<Collection<Course>>() {
                        }));
                    } catch (ClientErrorException ex) {
                        LOG.severe(ex.getMessage());
                    }
                    //There is a filter selected
                } else {
                    LOG.info("There is a filter to apply");
                    applyFilter();
                }
            } catch (Exception e) {
                try {
                    LOG.info(e.getMessage());
                    alert = new Alert(Alert.AlertType.ERROR, "An error has ocurred during the filter applying");
                    cmbxFilter.getSelectionModel().select(-1);
                    coursesData.clear();
                    coursesData = FXCollections.observableArrayList(courseController.findAll_XML(new GenericType<Collection<Course>>() {
                    }));
                    tableCourses.setItems(coursesData);
                } catch (ClientErrorException ex) {
                    LOG.severe(ex.getMessage());
                    alert = new Alert(Alert.AlertType.ERROR, "There was an Error while charging the Data on the table");
                }
            }
        }
        );

        /**
         * Goes to the Subject window
         */
        btnShowSubjects.setOnAction(actionEvent -> {
            try {
                LOG.info("Entering on Subject Window");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/subject/Subjects.fxml"));
                Parent rootSubject = (Parent) loader.load();
                SubjectsViewController controller = ((SubjectsViewController) loader.getController());
                controller.initStage(rootSubject, stage);
            } catch (IOException ex) {
                Logger.getLogger(CourseViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnEnter.setOnAction(actionEvent -> {
            try {
                /*
                user = ControllerFactory.getUserController().getUser_XML(new GenericType<User>() {
                }, "1");
                 */
                List<Post> postList = ControllerFactory.getPostController().
                        getCoursePosts(new GenericType<ArrayList<Post>>() {
                        }, tableCourses.getSelectionModel().getSelectedItem().getCourseId().toString());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/post/PostView.fxml"));
                Parent rootPost = (Parent) loader.load();
                PostViewController controller = ((PostViewController) loader.getController());
                controller.setUser(user);
                stage.close();
                controller.initStage(rootPost, ControllerFactory.getPostController(), stage, postList, tableCourses.getSelectionModel().getSelectedItem().getCourseId());
            } catch (Exception e) {
                LOG.severe(e.getMessage());
            }
        });

        /**
         * Closes the Window and goes to the one before
         */
        btnReturn.setOnAction(actionEvent
                -> {
            LOG.info("Closing Window");
            stage.close();
            primaryStage.show();
        }
        );

        /**
         * Closes the Aplication
         */
        stage.setOnCloseRequest(windowEvent
                -> {
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
        }
        );

        /**
         * Validates if some table row has been selected
         */
        tableCourses.getSelectionModel()
                .selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    LOG.info("Table selection event handling");

                    //If it is not empty gets all the cambs of the table
                    if (newValue != null) {
                        LOG.info("A row was selected");
                        Course selectedCourse = (Course) tableCourses.getSelectionModel().getSelectedItem();

                        txtCourseName.setText(selectedCourse.getName());
                        txtCreatedDate.setText(selectedCourse.getStartDate().toString());

                        correctName = true;
                        correctDate = true;

                        if (correctName && correctDate) {
                            btnModify.setDisable(false);
                            btnDelete.setDisable(false);
                            btnEnter.setDisable(false);
                            btnJoin.setDisable(false);
                        }
                    } else {
                        LOG.info("The row was diselected");
                        clearFields();
                        btnModify.setDisable(true);
                        btnDelete.setDisable(true);
                        btnEnter.setDisable(true);
                        btnJoin.setDisable(true);
                        correctName = false;
                        correctDate = false;
                    }
                }
                );

        /**
         * Creates a Course
         */
        btnCreate.setOnAction(actionEvent
                -> {
            try {
                LOG.info("Creating Course");

                SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
                Date formatedDate = format.parse(txtCreatedDate.getText());

                //Creates a new course and takes all camps
                Course course = new Course();
                course.setName(txtCourseName.getText());
                course.setStartDate(formatedDate);

                Teacher teacher = userController.getTeacher_XML(Teacher.class, String.valueOf(comboSelectedTeacher.getId()));
                course.setTeacher(teacher);

                Subject subject = subjectController.find_XML(Subject.class, String.valueOf(comboSelectedSubject.getSubjectId()));
                course.setSubject(subject);

                //Creates the course
                courseController.create_XML(course);

                //The fields gets cleared and the course appears on the table
                clearFields();
                coursesData.add(course);
                tableCourses.refresh();
                btnCreate.setDisable(true);

                correctName = false;
                correctDate = false;
                comboSelectedTeacher = null;
                comboSelectedSubject = null;
            } catch (ParseException ex) {
                LOG.severe(ex.getMessage());
            } catch (BusinessLogicException ex) {
                LOG.severe(ex.getMessage());
                ex.printStackTrace();
            }
        }
        );

        /**
         * Modify the Course
         */
        btnModify.setOnAction(actionEvent
                -> {
            LOG.info("Modifying the course");
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to modify the course?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    LOG.info("Modify Confirmed");
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
                        Date formatedDate = format.parse(txtCreatedDate.getText());

                        //Selects a Row on the table and gets the course, modify the camps
                        Course course = (Course) tableCourses.getSelectionModel().getSelectedItem();
                        course.setName(txtCourseName.getText());
                        course.setStartDate(formatedDate);
                        if (comboSelectedSubject != null) {
                            Subject selectedSubject = userController.getTeacher_XML(Subject.class, String.valueOf(comboSelectedSubject.getSubjectId()));
                            course.setSubject(selectedSubject);
                        }

                        if (comboSelectedTeacher != null) {
                            Teacher selectedTeacher = userController.getTeacher_XML(Teacher.class, String.valueOf(comboSelectedTeacher.getId()));
                            course.setTeacher(selectedTeacher);
                        }

                        //Modify the course
                        courseController.edit_XML(course);
                        for (int i = 0; i < coursesData.size(); i++) {
                            if (coursesData.get(i).getCourseId().equals(course.getCourseId())) {
                                coursesData.set(i, course);
                            }
                        }
                        //Appears on the table
                        tableCourses.refresh();

                        alert = new Alert(Alert.AlertType.INFORMATION, "Course Modified");
                        //Clears all the fields
                        clearFields();
                    } catch (BusinessLogicException ex) {
                        LOG.severe(ex.getMessage());
                        alert = new Alert(Alert.AlertType.ERROR, "An error has ocurred while modify the course");
                    } catch (ParseException ex) {
                        LOG.severe(ex.getMessage());
                    }
                }
            });
        }
        );

        /**
         * Delete a Course
         */
        btnDelete.setOnAction(actionEvent
                -> {
            LOG.info("Deleting the Course");
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete the course?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    try {
                        LOG.info("Deleting confirmed");
                        //Selects a Row on the table and gets the course
                        Course course = (Course) tableCourses.getSelectionModel().getSelectedItem();
                        courseController.remove(String.valueOf(course.getCourseId()));

                        //Delete the course
                        coursesData.remove(course);
                        tableCourses.getItems().remove(course);

                        //The Course is not in the table anymore
                        tableCourses.refresh();
                        alert = new Alert(Alert.AlertType.WARNING, "Course Deleted");
                        clearFields();
                    } catch (ClientErrorException e) {
                        LOG.severe(e.getMessage());
                        alert = new Alert(Alert.AlertType.ERROR, "An error has ocurred while modify the course");
                    }
                } else {
                    LOG.info("Deleting cancelled");
                    alert = new Alert(Alert.AlertType.WARNING, "Delete Cancelled");
                }
            });
        }
        );

        /**
         * Print the report
         */
        btnPrint.setOnAction(actionEvent -> {
            try {
                LOG.info("Printing Report");

                JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/client/view/course/CourseReport.jrxml"));
                JRBeanCollectionDataSource dataItems = new JRBeanCollectionDataSource((Collection<Course>) this.tableCourses.getItems());
                Map<String, Object> parameters = new HashMap<>();

                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataItems);

                JasperViewer view = new JasperViewer(jasperPrint);
                view.setVisible(true);
            } catch (JRException ex) {
                Logger.getLogger(CourseViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        stage.showAndWait();
    }

    /**
     * Method that Parses the date with a format
     *
     * @param date the date to parse
     * @return the parsed date
     */
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

    /**
     * Method that searches for the Course using the filters
     *
     */
    private void applyFilter() {
        try {
            LOG.info("Applying a Filter");
            switch (filterToApply) {
                case NOMBRE:
                    //Clears the courses data
                    coursesData.clear();
                    //Charges the Courses data using the Name filter
                    coursesData = FXCollections.observableArrayList(courseController.findByName_XML(new GenericType<Collection<Course>>() {
                    }, txtFilter.getText()));
                    tableCourses.setItems(coursesData);
                    break;
                case FECHA:
                    //Clears the courses data
                    coursesData.clear();
                    //Charges the Courses data using the Date filter
                    coursesData = FXCollections.observableArrayList(courseController.findByDate_XML(new GenericType<Collection<Course>>() {
                    }, txtFilter.getText()));
                    tableCourses.setItems(coursesData);
                    break;
            }
        } catch (ClientErrorException ex) {
            LOG.severe("Error: " + ex.getMessage());
        }
    }
}
