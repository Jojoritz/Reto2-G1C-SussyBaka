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
import client.view.components.MenuBarController;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.lang.Class;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    private ComboBox<String> cmbxFilterOptions;
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
     * The filter to apply
     */
    private FilterTypes filterToApply = null;
    /**
     * This is the logger for the subjects view controller
     */
    private static final Logger LOGGER = Logger.getLogger(SubjectsViewController.class.getName());
    /**
     * Controller of the menu bar
     */
    private final MenuBarController menuBarController = new MenuBarController();

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
        menuBarController.setStage(myStage);
        primaryStage.hide();

        //Initializing variable for validations and filters
        subjectNameEmpty = true;
        creationCenturyEmpty = true;
        levelEmpty = true;
        typeEmpty = true;
        comboSelectedTeacher = null;
        filterToApply = null;

        //Setting the combobox state
        cmbxFilterOptions.setEditable(false);
        cmbxTeacher.setEditable(false);

        //Setting the buttons state
        btnCreateSubject.setDisable(true);
        btnDeleteSubject.setDisable(true);
        btnModifySubject.setDisable(true);
        btnSearchSubject.setDisable(false);
        btnSearchSubject.getStyleClass().add("buttonSearch");
        btnSubjectPrint.setDisable(false);
        btnSubjectReturn.setDisable(false);
        //Setting filter text field state
        txtFilter.setDisable(true);
        //Setting the table state and cell value factory
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
                        "",
                        FilterTypes.NOMBRE.toString(),
                        FilterTypes.NIVEL.toString(),
                        FilterTypes.TIPO.toString()
                );
                //charging the data of the teachers combo box
                userController = ControllerFactory.getUserController();
                subjectController = ControllerFactory.getSubjectController();

                usersData = FXCollections.observableArrayList(userController.findAll_XML(new GenericType<Collection<User>>() {
                }));

                usersData.stream().forEach(u -> {
                    if (u.getPrivilege().equals(UserPrivilege.TEACHER)) {
                        cmbxTeacher.getItems().add(u.getFullName());
                    }
                });

                //Charging the subjects data to the table
                subjectsData = FXCollections.observableArrayList(subjectController.findAll_XML(new GenericType<Collection<Subject>>() {
                }));
                tableSubjects.setItems(subjectsData);
            } catch (BusinessLogicException ex) {
                LOGGER.severe("An error happened while loading data: " + ex.getMessage());
                LOGGER.severe("Its posible that the server is shut down, or a connection error had happened");
                alert = new Alert(Alert.AlertType.ERROR, "Ha sucedido un error al cargar los datos, intentelo de nuevo mas tarde");
                alert.showAndWait();
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
        //Seleccting witch filter type will be applyed 
        cmbxFilterOptions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            LOGGER.info("Seleccting the filter type to apply");
            if (newValue.equals(FilterTypes.NOMBRE.toString())) {
                filterToApply = FilterTypes.NOMBRE;
                txtFilter.setDisable(false);

            } else if (newValue.equals(FilterTypes.TIPO.toString())) {
                filterToApply = FilterTypes.TIPO;
                txtFilter.setDisable(false);
            } else if (newValue.equals(FilterTypes.NIVEL.toString())) {
                filterToApply = FilterTypes.NIVEL;
                txtFilter.setDisable(false);
            } else {
                txtFilter.setDisable(true);
                filterToApply = null;
            }

        });

        btnSearchSubject.setOnAction(actionEvent -> {
            try {
                LOGGER.info("Applying the filters and searching the subjects");
                //If filter to apply is set to null
                if (filterToApply == null) {
                    try {
                        //Charge again all the data and put it in the table
                        LOGGER.info("If is not applyed any filters");
                        subjectsData.clear();
                        subjectsData = FXCollections.observableArrayList(subjectController.findAll_XML(new GenericType<Collection<Subject>>() {
                        }));
                        tableSubjects.setItems(subjectsData);
                    } catch (BusinessLogicException e) {
                        LOGGER.severe(e.getMessage());
                        throw new Exception(e.getMessage());
                    }

                } else {
                    //If a filter is going to be applyed
                    LOGGER.info("If any filter is being applyed");
                    applyFilter();
                }
            } catch (Exception ex) {
                try {
                    //If a exception happened while applying a filter charge all the data like if isn't being applyed any filter
                    LOGGER.severe(ex.getMessage());
                    alert = new Alert(Alert.AlertType.ERROR, "Ha sucedido un error al aplicar un filtro por lo que se desaplicaran todos");
                    txtFilter.setDisable(true);
                    cmbxFilterOptions.getSelectionModel().select(-1);
                    subjectsData.clear();
                    subjectsData = FXCollections.observableArrayList(subjectController.findAll_XML(new GenericType<Collection<Subject>>() {
                    }));
                    tableSubjects.setItems(subjectsData);
                } catch (BusinessLogicException ex1) {
                    LOGGER.severe(ex.getMessage());
                    alert = new Alert(Alert.AlertType.ERROR, "Ha sucedido un error al cargar los datos de la tabla, intentelo de nuevo despues");
                }
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

                Teacher teacher = userController.getTeacher_XML(Teacher.class, String.valueOf(comboSelectedTeacher.getId()));

                subject.getTeachersSpecializedInSubject().add(teacher);
                subjectController.createSubject_XML(subject);

                clearFields();
                subjectsData.add(subject);
                tableSubjects.refresh();
                btnCreateSubject.setDisable(true);

                subjectNameEmpty = true;
                typeEmpty = true;
                creationCenturyEmpty = true;
                levelEmpty = true;
                comboSelectedTeacher = null;

            } catch (Exception e) {
                LOGGER.severe(e.getMessage());
                e.printStackTrace();
                alert = new Alert(Alert.AlertType.ERROR, "Ha sucedido un error al crear la asignatura, intentelo de nuevo otra vez");
            }

        });

        tableSubjects.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            LOGGER.info("Table selection event handling");

            if (newValue != null) {
                LOGGER.info("A row was selected");
                Subject selectedSubject = (Subject) tableSubjects.getSelectionModel().getSelectedItem();

                txtSubjectName.setText(selectedSubject.getName());
                txtLevel.setText(selectedSubject.getLevel());
                txtCreatedCentury.setText(selectedSubject.getCentury());
                txtType.setText(selectedSubject.getType());

                subjectNameEmpty = false;
                typeEmpty = false;
                levelEmpty = false;
                creationCenturyEmpty = false;

                if (!subjectNameEmpty && !creationCenturyEmpty && !levelEmpty
                        && !typeEmpty) {
                    btnModifySubject.setDisable(false);
                    btnDeleteSubject.setDisable(false);
                }
            } else if (newValue == null) {
                LOGGER.info("The row was diselected");
                clearFields();
                btnModifySubject.setDisable(true);
                btnDeleteSubject.setDisable(true);
                subjectNameEmpty = true;
                typeEmpty = true;
                levelEmpty = true;
                creationCenturyEmpty = true;
            }
        });

        btnSubjectPrint.setOnAction(actionEvent -> {
            try {
                LOGGER.info("Printing a report");

                JasperReport report
                        = JasperCompileManager.compileReport(getClass().getResourceAsStream("/client/view/subject/SubjectReport.jrxml"));

                JRBeanCollectionDataSource dataItems = new JRBeanCollectionDataSource((Collection<Subject>) this.tableSubjects.getItems());
                Map<String, Object> parameters = new HashMap<>();

                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataItems);

                JasperViewer jasperViewer = new JasperViewer(jasperPrint);
                jasperViewer.setVisible(true);
            } catch (JRException ex) {
                LOGGER.severe("An error happened while trying to print a report");
                ex.printStackTrace();
                alert = new Alert(Alert.AlertType.ERROR, "Ha sucedido un error al tratar de imprimir el informe");
                alert.showAndWait();
            }

        });
        btnModifySubject.setOnAction(actionEvent -> {
            LOGGER.info("Modifying the subject");

            alert = new Alert(Alert.AlertType.CONFIRMATION, "Esta seguro de que quiere modificar la asignatura?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    LOGGER.info("Modify confirmed");
                    try {
                        Subject subject = (Subject) tableSubjects.getSelectionModel().getSelectedItem();
                        subject.setName(txtSubjectName.getText());
                        subject.setType(txtType.getText());
                        subject.setCentury(txtCreatedCentury.getText());
                        subject.setLevel(txtLevel.getText());

                        if (comboSelectedTeacher != null) {
                            Teacher selectedTeacher = userController.getTeacher_XML(Teacher.class, String.valueOf(comboSelectedTeacher.getId()));
                            subject.getTeachersSpecializedInSubject().add(selectedTeacher);
                        }

                        subjectController.modifySubject_XML(subject);
                        for (int i = 0; i < subjectsData.size(); i++) {
                            if (subjectsData.get(i).getSubjectId().equals(subject.getSubjectId())) {
                                subjectsData.set(i, subject);
                            }
                        }
                        tableSubjects.refresh();

                        alert = new Alert(Alert.AlertType.INFORMATION, "La modificacion se ha hecho correctamenete");
                        clearFields();
                    } catch (BusinessLogicException ex) {
                        LOGGER.severe(ex.getMessage());
                        alert = new Alert(Alert.AlertType.ERROR, "A sucedido un error al modificar la asignatura, intentelo de nuevo mas tarde");

                    }
                } else {
                    alert = new Alert(Alert.AlertType.WARNING, "La modificacion se ha cancelado");
                    LOGGER.info("Cancelling the modification");
                }
            });

        });

        btnDeleteSubject.setOnAction(actionEvent -> {

            LOGGER.info("Deleting the subject");
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Esta seguro que quiere eliminar la asignatura", ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    try {
                        LOGGER.info("Deleting confirmed");
                        Subject selectedSubject = (Subject) tableSubjects.getSelectionModel().getSelectedItem();
                        subjectController.removeSubject(String.valueOf(selectedSubject.getSubjectId()));

                        subjectsData.remove(selectedSubject);
                        tableSubjects.getItems().remove(selectedSubject);

                        tableSubjects.refresh();
                        alert = new Alert(Alert.AlertType.WARNING, "El borrado se ha realizado correctamente");
                        clearFields();
                    } catch (BusinessLogicException e) {
                        LOGGER.severe(e.getMessage());
                        alert = new Alert(Alert.AlertType.ERROR, "A sucedido un error al borrar la asignatura, intentelo de nuevo mas tarde");
                    }
                } else {
                    LOGGER.info("Deleting aborted");
                    alert = new Alert(Alert.AlertType.WARNING, "El borrado ha sido cancelado");
                }
            });

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

    /**
     * A method tha applyes a filter
     *
     * @throws Exception throws an exception if any error happened while trying
     * to apply any filter
     */
    private void applyFilter() throws Exception {
        try {
            LOGGER.info("Applying a filter");
            switch (filterToApply) {
                case NOMBRE:
                    subjectsData.clear();
                    subjectsData = FXCollections.observableArrayList(subjectController.searchByName_XML(new GenericType<Collection<Subject>>() {
                    }, txtFilter.getText()));
                    tableSubjects.setItems(subjectsData);
                    break;
                case NIVEL:
                    subjectsData.clear();
                    subjectsData = FXCollections.observableArrayList(subjectController.searchByLevel_XML(new GenericType<Collection<Subject>>() {
                    }, txtFilter.getText()));
                    tableSubjects.setItems(subjectsData);
                    break;
                case TIPO:
                    subjectsData.clear();
                    subjectsData = FXCollections.observableArrayList(subjectController.searchByType_XML(new GenericType<Collection<Subject>>() {
                    }, txtFilter.getText()));
                    tableSubjects.setItems(subjectsData);

                    break;
            }

        } catch (BusinessLogicException e) {
            LOGGER.severe("An error happened while applying a filter: " + e.getMessage());
            throw new Exception("An error happened while applying a filter");
        }
    }

}
