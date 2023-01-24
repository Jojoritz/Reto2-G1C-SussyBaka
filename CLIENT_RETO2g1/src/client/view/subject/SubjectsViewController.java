/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.subject;

import client.beans.Subject;
import client.beans.Teacher;
import client.beans.enumerations.FilterTypes;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Teacher comboSelectedTeacher;
    
    List<Teacher> testingTeachersData;
    ObservableList<Subject> testingSubjetsData;

    /**
     * This is the logger for the subjects view controller
     */
    private static final Logger LOGGER = Logger.getLogger(SubjectsViewController.class.getName());

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
        
        cmbxFilterOptions.getItems().addAll(
                FilterTypes.NAME,
                FilterTypes.LEVEL,
                FilterTypes.TYPE
        );
        cmbxFilterOptions.setEditable(false);
        cmbxTeacher.setEditable(false);

        btnCreateSubject.setDisable(true);
        btnDeleteSubject.setDisable(true);
        btnModifySubject.setDisable(true);
        btnSearchSubject.setDisable(false);
        btnSubjectPrint.setDisable(false);
        btnSubjectReturn.setDisable(false);
        
        
         tableSubjects.setVisible(true);
         
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
        colCreationCentury.setCellValueFactory(new PropertyValueFactory<>("century"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        
         myStage.setOnShowing(event -> {
            //charging the data of the teachers combo box
            testingTeachersData = new ArrayList<>();
             for (int i = 1; i <= 3; i++) {
                 Teacher t = new Teacher();
                 t.setEmail("email" + i);
                 t.setId(i);
                 t.setFullName("nombre" + i);
                 t.setLogin("login" + i);
                 testingTeachersData.add(t);
             }
             testingTeachersData.forEach(teacher -> {
                 cmbxTeacher.getItems().add(teacher.getFullName());
             }); 
             
             //Charging the data of the table
             
             testingSubjetsData = FXCollections.observableArrayList(createSubjectTestData());
             tableSubjects.setItems(testingSubjetsData);
            
        });
        

        txtSubjectName.textProperty().addListener(observable -> {
            try {
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
                btnCreateSubject.setDisable(true);
                subjectNameEmpty = true;
            }
        });
        
        txtType.textProperty().addListener(observable -> {
            try {
                if (txtType.getText().length() == 0 || txtType.getText().trim().equals("")) {
                    throw new Exception();
                }
                typeEmpty = false;
                if (!subjectNameEmpty && !creationCenturyEmpty && !levelEmpty 
                        && !typeEmpty && comboSelectedTeacher != null) {
                    btnCreateSubject.setDisable(false);
                }
            } catch (Exception e) {
                btnCreateSubject.setDisable(true);
                typeEmpty = true;
            }
        });
        
        txtLevel.textProperty().addListener(observable ->{
            try {
                if (txtLevel.getText().length() == 0 || txtLevel.getText().trim().equals("")) {
                    throw new Exception();
                }
                levelEmpty = false;
                if (!subjectNameEmpty && !creationCenturyEmpty && !levelEmpty 
                        && !typeEmpty && comboSelectedTeacher != null) {
                    btnCreateSubject.setDisable(false);
                }
            } catch (Exception e) {
                btnCreateSubject.setDisable(true);
                levelEmpty = true;
            }
        });
        
        txtCreatedCentury.textProperty().addListener(observable ->{
            try {
                String prueba = txtCreatedCentury.getText();
                System.out.println(prueba.length());
                System.out.println(txtCreatedCentury.getText().length());
                System.out.println(txtCreatedCentury.getText().trim());
                if (prueba.length() == 0 
                        || prueba.trim().equals("")) {
                    
                    throw new Exception();
                    
                }
                creationCenturyEmpty = false;
                if (!subjectNameEmpty && !creationCenturyEmpty && !levelEmpty 
                        && !typeEmpty && comboSelectedTeacher != null) {
                    btnCreateSubject.setDisable(false);
                }
            } catch (Exception e) {
                btnCreateSubject.setDisable(true);
                creationCenturyEmpty = true;
            }
        });
        
        cmbxTeacher.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                testingTeachersData.stream().forEach(t -> {
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
       
        
        
        myStage.showAndWait();
    }
    
    private Collection createSubjectTestData(){
        List<Subject> ret = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Subject subject = new Subject();
            subject.setName("name" + i);
            subject.setLevel("level" + i);
            subject.setCentury("century" + i);
            subject.setType("type" + i);
            ret.add(subject);
        }
        return ret;
    }

}
