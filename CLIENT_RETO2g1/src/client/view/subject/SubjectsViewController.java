/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.subject;

import client.beans.Subject;
import client.beans.Teacher;
import client.beans.User;
import client.beans.enumerations.FilterTypes;
import client.beans.enumerations.UserPrivilege;
import client.logic.ControllerFactory;
import client.logic.SubjectController;
import client.logic.UserController;
import client.logic.exception.BusinessLogicException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.lang.Class;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;

/**
 * FXML Controller class
 *
 * @author GAME
 */
public class SubjectsViewController {

    /**
     * The table view for the subjects
     */
    @FXML
    private TableView<Subject> tableSubjects;
    /**
     * The table column for the name of the subjects
     */
    @FXML
    private TableColumn<?, ?> colName;
    /**
     * The column for the creation century of the subjects
     */
    @FXML
    private TableColumn<?, ?> colCreationCentury;
    /**
     * The column for the type of the subjects
     */
    @FXML
    private TableColumn<?, ?> colType;
    /**
     * The column for the level of the subjects
     */
    @FXML
    private TableColumn<?, ?> colLevel;
    /**
     * The text field for the subject name
     */
    @FXML
    private TextField txtSubjectName;
    /**
     * The text field for the creation century of the subject
     */
    @FXML
    private TextField txtCreatedCentury;
    /**
     * The text field for the level of the subject
     */
    @FXML
    private TextField txtLevel;
    /**
     * The text field for the type of the subject
     */
    @FXML
    private TextField txtType;
    /**
     * The combo box for the teacher of the subject
     */
    @FXML
    private ComboBox<String> cmbxTeacher;
    /**
     * The combo box with the filter type options
     */
    @FXML
    private ComboBox<FilterTypes> cmbxFilterOptions;
    /**
     * A button for printing the data of the table view
     */
    @FXML
    private Button btnSubjectPrint;
    /**
     * A button for deleting the subject data
     */
    @FXML
    private Button btnDeleteSubject;
    /**
     * A button for modifying the subject data
     */
    @FXML
    private Button btnModifySubject;
    /**
     * A button for creating a subject
     */
    @FXML
    private Button btnCreateSubject;
    /**
     * A button with search icon to apply the filters
     */
    @FXML
    private Button btnSearchSubject;
    /**
     * A button that close the actuall window
     */
    @FXML
    private Button btnSubjectReturn;
    /**
     * A text field to search the subject
     */
    @FXML
    private TextField txtFilter;
    /**
     * This is the stage of the subjects view
     */
    private Stage myStage;
    /**
     * *
     * The scene of the subjects view
     */
    private Scene myScene;

    /**
     * A alert for showing any alerts and errors to the user
     */
    private Alert alert;

    /**
     * Indicates if the subject name text field is empty or not
     */
    private Boolean subjectNameEmpty;
    /**
     * Indicates if the creation century text field is empty or not
     */
    private Boolean creationCenturyEmpty;
    /**
     * Indicates if the type text field is empty or not
     */
    private Boolean typeEmpty;
    /**
     * Indicates if level text field is empty or not
     */
    private Boolean levelEmpty;

    /**
     * An object to save the selected teacher from the combo box
     */
    private User comboSelectedTeacher;
    /**
     * A list of users for the teachers combobox
     */
    private List<User> usersData;
    /**
     * A observable list of subjects for showing it in the table
     */
    private ObservableList<Subject> subjectsData;
    /**
     * An object of SubjectController interface
     */
    private SubjectController subjectController;
    
    /**
     * A object of UserController interface
     */
    private UserController userController;
    /**
     * This is the logger for the subjects view controller
     */
    private static final Logger LOGGER = Logger.getLogger(SubjectsViewController.class.getName());

    /**
     * The method that initialize the window
     *
     * @param root The parent scene of the actual window scene
     * @param primaryStage The parent stage of the actual window stage
     */
    public void initStage(Parent root, Stage primaryStage) {
        LOGGER.info("Starting the window and setting the components on the screen");

        //Setting the scene
        myScene = new Scene(root);
        myStage = new Stage();

        myStage.initModality(Modality.APPLICATION_MODAL);
        myStage.setScene(myScene);
        myStage.setTitle("Asignaturas");
        myStage.setResizable(false);

        primaryStage.hide();

        subjectNameEmpty = true;
        creationCenturyEmpty = true;
        levelEmpty = true;
        typeEmpty = true;
        comboSelectedTeacher = null;
        LOGGER.info("Charging t");

        cmbxFilterOptions.setEditable(false);
        cmbxTeacher.setEditable(false);

        btnCreateSubject.setDisable(true);
        btnDeleteSubject.setDisable(true);
        btnModifySubject.setDisable(true);
        btnSearchSubject.setDisable(false);
        btnSearchSubject.getStyleClass().add("buttonSearch");
        btnSubjectPrint.setDisable(false);
        btnSubjectReturn.setDisable(false);

        tableSubjects.setVisible(true);

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
        colCreationCentury.setCellValueFactory(new PropertyValueFactory<>("century"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

        myStage.setOnShowing(event -> {
            try {
                LOGGER.info("Charging the teachers combo box data and the table data");
                //Charging the filter options combobox data
                cmbxFilterOptions.getItems().addAll(
                        FilterTypes.NOMBRE,
                        FilterTypes.NIVEL,
                        FilterTypes.TIPO
                );
                //charging the data of the teachers combo box
                userController = ControllerFactory.getUserController();
                subjectController = ControllerFactory.getSubjectController();
                
                usersData = FXCollections.observableArrayList(userController.findAll_XML(new GenericType<Collection<User>>(){}));
                
                usersData.stream().forEach(u -> {
                    if (u.getPrivilege().equals(UserPrivilege.TEACHER)) {
                        cmbxTeacher.getItems().add(u.getFullName());
                    }
                });
                
                subjectsData = FXCollections.observableArrayList(subjectController.findAll_XML(new GenericType<Collection<Subject>>(){}));
                tableSubjects.setItems(subjectsData);
            } catch (BusinessLogicException ex) {
                Logger.getLogger(SubjectsViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        });

        txtSubjectName.textProperty().addListener(observable -> {
            try {
                LOGGER.info("Validating subject name text field is not empty");
                if (txtSubjectName.getText().length() == 0
                        || txtSubjectName.getText().trim().equals("")) {

                    throw new Exception();
                }
                subjectNameEmpty = false;
                if (!subjectNameEmpty && !creationCenturyEmpty && !levelEmpty
                        && !typeEmpty && comboSelectedTeacher != null) {
                    btnCreateSubject.setDisable(false);
                }
            } catch (Exception e) {
                LOGGER.info("The subject name text field is empty");
                btnCreateSubject.setDisable(true);
                subjectNameEmpty = true;
            }
        });

        txtType.textProperty().addListener(observable -> {
            try {
                LOGGER.info("Validating the type text field is not empty");
                if (txtType.getText().length() == 0 || txtType.getText().trim().equals("")) {
                    throw new Exception();
                }
                typeEmpty = false;
                if (!subjectNameEmpty && !creationCenturyEmpty && !levelEmpty
                        && !typeEmpty && comboSelectedTeacher != null) {
                    btnCreateSubject.setDisable(false);
                }
            } catch (Exception e) {
                LOGGER.info("The type text field is empty");
                btnCreateSubject.setDisable(true);
                typeEmpty = true;
            }
        });
        
        
        
        txtLevel.textProperty().addListener(observable -> {
            try {
                LOGGER.info("Validating the level text field is not empty");
                if (txtLevel.getText().length() == 0 || txtLevel.getText().trim().equals("")) {
                    throw new Exception();
                }
                levelEmpty = false;
                if (!subjectNameEmpty && !creationCenturyEmpty && !levelEmpty
                        && !typeEmpty && comboSelectedTeacher != null) {
                    btnCreateSubject.setDisable(false);
                }
            } catch (Exception e) {
                LOGGER.severe("The level text field is empty");
                btnCreateSubject.setDisable(true);
                levelEmpty = true;
            }
        });

        txtCreatedCentury.textProperty().addListener(observable -> {
            try {
                LOGGER.info("Validating the created century is not empty");
                String createdCentury = txtCreatedCentury.getText();

                if (createdCentury.length() == 0
                        || createdCentury.trim().equals("")) {

                    throw new Exception();

                }
                creationCenturyEmpty = false;
                if (!subjectNameEmpty && !creationCenturyEmpty && !levelEmpty
                        && !typeEmpty && comboSelectedTeacher != null) {
                    btnCreateSubject.setDisable(false);
                }
            } catch (Exception e) {
                LOGGER.info("The created century text field is empty");
                btnCreateSubject.setDisable(true);
                creationCenturyEmpty = true;
            }
        });

        cmbxTeacher.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                LOGGER.info("Seleccting and getting the selected teachers data");
                
                usersData.stream().forEach(t -> {
                    if (t.getFullName().equalsIgnoreCase(newValue)) {
                        comboSelectedTeacher = t;
                    }
                });
                if (comboSelectedTeacher == null) {
                    throw new Exception();
                }
                if (!subjectNameEmpty && !creationCenturyEmpty && !levelEmpty
                        && !typeEmpty && comboSelectedTeacher != null) {
                    btnCreateSubject.setDisable(false);
                }
            } catch (Exception e) {
                LOGGER.severe("An error ocurred while getting the teacher data");
                btnCreateSubject.setDisable(true);
                comboSelectedTeacher = null;
            }
        });

        //Handle an window close request event
        myStage.setOnCloseRequest(windowEvent -> {
            LOGGER.info("Opening exit alert confitmation");

            alert = new Alert(Alert.AlertType.CONFIRMATION, "Quieres cerrar el programa?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    LOGGER.info("Closing the application");
                    Platform.exit();
                } else {
                    LOGGER.info("Canceled application close");
                    windowEvent.consume();
                }
            });
        });

        btnSubjectReturn.setOnAction(actionEvent -> {
            LOGGER.info("Closing the window");
            myStage.close();
            primaryStage.show();
        });

        
        btnCreateSubject.setOnAction(actionEvent -> {
            try {
                LOGGER.info("Creating the subject");
                Subject subject = new Subject();
                subject.setName(txtSubjectName.getText());
                subject.setLevel(txtLevel.getText());
                subject.setType(txtType.getText());
                subject.setCentury(txtCreatedCentury.getText());

                Teacher teacher = userController.getTeacher_XML(Teacher.class, comboSelectedTeacher.getId().toString());
                
                subject.getTeachersSpecializedInSubject().add(teacher);
                subjectController.createSubject_XML(subject);

                clearFields();
                subjectsData.add(subject);
                btnCreateSubject.setDisable(true);
                
                subjectNameEmpty = true;
                typeEmpty = true;
                creationCenturyEmpty = true;
                levelEmpty = true;
                comboSelectedTeacher = null;

            } catch (Exception e) {
                LOGGER.severe(e.getMessage());
                e.printStackTrace();
                alert = new Alert(Alert.AlertType.ERROR, "Ha sucedido un error al crear la asignatura, intentelo otra vez");
            }

        });

        myStage.showAndWait();
    }

    /**
     * A method for clearing the fields and checkboxes
     */
    private void clearFields() {
        txtCreatedCentury.setText("");
        txtFilter.setText("");
        txtLevel.setText("");
        txtSubjectName.setText("");
        txtType.setText("");
        cmbxFilterOptions.getSelectionModel().select(-1);
        cmbxTeacher.getSelectionModel().select(-1);
    }

}
