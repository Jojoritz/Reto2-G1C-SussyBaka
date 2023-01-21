/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.subject;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author GAME
 */
public class SubjectsViewController{

    /**
     * The table view for the subjects
     */
    @FXML
    private TableView<?> tableSubjects;
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
    private ComboBox<?> cmbxTeacher;
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
     * This is the stage of the subjects view
     */
    private Stage myStage;
    /***
     * The scene of the subjects view
     */
    private Scene myScene;
    /**
     * This is the logger for the subjects view controller
     */
    private static final Logger LOGGER = Logger.getLogger(SubjectsViewController.class.getName());
    
    
    public void initStage(Parent root, Stage primaryStage){
        LOGGER.info("Starting the window and setting the components on the screen");
        
        //Setting the scene
        myScene = new Scene(root);
        myStage = new Stage();
        
        myStage.initModality(Modality.APPLICATION_MODAL);
        myStage.setScene(myScene);
        myStage.setTitle("Asignaturas");
        myStage.setResizable(false);
        
        primaryStage.hide();
        
    }
     
    
}
