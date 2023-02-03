/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.signUp;

import client.beans.Student;
import client.beans.Teacher;
import client.beans.User;
import client.beans.enumerations.UserPrivilege;
import client.beans.enumerations.UserStatus;
import client.logic.ControllerFactory;
import client.logic.EncryptDecrypt;
import client.logic.UserController;
import client.logic.exception.BusinessLogicException;
import client.logic.exception.EncriptionException;
import client.logic.exception.UserAllReadyExistException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class This is the SignUpView scene controller
 *
 * @author ioritz
 */
public class SignUpViewController {

    /**
     * The field for the user full name
     */
    @FXML
    private TextField txtFullName;
    /**
     * The field for the username
     */
    @FXML
    private TextField txtUsername;
    /**
     * THe field for the email
     */
    @FXML
    private TextField txtEmail;
    /**
     * The field for the password
     */
    @FXML
    private PasswordField txtPassword;
    /**
     * The signUp button
     */
    @FXML
    private Button btnSignUp;
    /**
     * The button to go back to the main window
     */
    @FXML
    private Button btnBack;
    /**
     * The label to show error messages of the full name field
     */
    @FXML
    private Label txtFullNameError;
    /**
     * The label to show error messages of the username field
     */
    @FXML
    private Label txtUsernameError;
    /**
     * The label to show error messages of the email field
     */
    @FXML
    private Label txtEmailError;
    /**
     * The label to show error messages of the password field
     */
    @FXML
    private Label txtPasswordError;
    /**
     * The label to show error messages of the password confirmation field
     */
    @FXML
    private Label txtPasswordConfirmError;
    /**
     * The field for the password confirmation
     */
    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private ComboBox<UserPrivilege> cmbxUserType;
    /**
     * The stage where the scene is going to be displayed
     */
    private Stage myStage = null;
    /**
     * The scene that is going to be displayed
     */
    private Scene myScene = null;
    /**
     * Indicates if the email is valid
     */
    private Boolean correctEmail = false;
    /**
     * Indicates if the password is valid
     */
    private Boolean correctPassword = false;
    /**
     * Indicates if the full name is valid
     */
    private Boolean correctFullName = false;
    /**
     * Indicates if the user name is valid
     */
    private Boolean correctUserName = false;
    /**
     * Indicates if the password confirmation is valid
     */
    private Boolean correctPasswordConfirmation = false;

    /**
     * Check if combo box any value is selected
     */
    private Boolean comboboxSelected = false;
    /**
     * The alert window
     */
    private static Alert alert = null;
    /**
     * The pattern to validate diferent fields
     */
    private Pattern pattern = null;
    /**
     * The matcher that validates if the field text matches the pattern
     */
    private Matcher matcher = null;

    /**
     * The user type
     */
    private UserPrivilege userType;

    /**
     * the user controller interface
     */
    private UserController userController;
    /**
     * The logger of this class
     */
    private static final Logger LOG = Logger.getLogger(SignUpViewController.class.getName());

    /**
     * Primary stage
     */
    private Stage primaryStage;

    /**
     * This method starts the Sign Up window
     *
     * @param root The scene that is going to be loaded in the stage
     * @param primaryStage Primary stage
     */
    public void initStage(Parent root, Stage primaryStage) {
        LOG.info("Starting the window and setting the components on the screen");
        //Setting the scene, the css and the client socket
        myScene = new Scene(root);
        myStage = new Stage();
        this.primaryStage = primaryStage;
        primaryStage.hide();

        myStage.setOnShowing((event) -> {
            //When the screen launch the onShowing event

            myStage.setTitle("Registro");
            myStage.setScene(myScene);
            myStage.setResizable(false);
            myStage.initModality(Modality.WINDOW_MODAL);

            btnSignUp.setDisable(true);
            btnSignUp.setDefaultButton(true);
            btnBack.setDisable(false);
            txtFullName.requestFocus();

            txtEmailError.setVisible(false);
            txtFullNameError.setVisible(false);
            txtPasswordError.setVisible(false);
            txtUsernameError.setVisible(false);
            txtPasswordConfirmError.setVisible(false);

            cmbxUserType.setItems(FXCollections.observableArrayList(UserPrivilege.STUDENT, UserPrivilege.TEACHER));

        });

        LOG.info("Setting validator for the full name field");

        txtFullName.textProperty().addListener((Observable) -> {
            //When the text is being modified in the text field
            try {
                txtFullNameError.setVisible(false);

                //Validating the full name
                String fullNamePattern
                        = "([A-Z\\u00d1ÁÉÍÓÚÜ][a-z\\u00f1áéíóúü]*((\\s)))+[A-Z\\u00d1ÁÉÍÓÚÜ][a-z\\u00f1áéíóúü]*";
                String fullName = txtFullName.getText();

                pattern = Pattern.compile(fullNamePattern);
                matcher = pattern.matcher(fullName);

                //If the full name is empty or the length is less than 5
                if (fullName.length() == 0 || fullName.length() < 5) {
                    throw new Exception("Este campo debe de tener mínimo 5 carácteres");
                } //If the full names length is bigger than 100
                else if (fullName.length() > 100) {
                    txtFullName.setText(txtFullName.getText().substring(0, 100));
                    throw new Exception("Este campo no puede contener más de 100 carácteres");
                } //If it doesn't match to the pattern
                else if (!matcher.matches()) {
                    throw new Exception("El nombre y apellido deben de estar separados con un espacio,"
                            + "\ncontener solo carácteres desde A-Z, y la primera letra en mayúscula ");
                }
                //Else
                correctFullName = true;
                btnSignUp.setDisable((!(correctFullName && correctEmail && correctPassword && correctUserName && correctPasswordConfirmation && comboboxSelected)));

            } catch (Exception e) {
                LOG.warning(e.getMessage());
                btnSignUp.setDisable(true);
                txtFullNameError.setVisible(true);
                txtFullNameError.setText(e.getMessage());
                correctFullName = false;
            }
        });

        LOG.info("Setting validator for the email field");
        txtEmail.textProperty().addListener((Observable) -> {
            //When the text is being modified in the text field

            try {
                txtEmailError.setVisible(false);

                //Validating that the text field is not empty
                if (txtEmail.getText().length() == 0) {
                    throw new Exception("El campo no puede estar vacio");
                } else if (txtEmail.getText().length() > 100) {
                    txtFullName.setText(txtFullName.getText().substring(0, 100));
                    throw new Exception("El campo no puede tener más de 100 carácteres");
                }
                correctEmail = emailValidator(txtEmail.getText());
                //if the email is not correct, show an error message
                if (!correctEmail) {
                    throw new Exception("El email no es correcto\nEj: usuario@ejemplo.com");
                }
                btnSignUp.setDisable((!(correctFullName && correctEmail && correctPassword && correctUserName && correctPasswordConfirmation && comboboxSelected)));

            } catch (Exception e) {
                LOG.warning(e.getMessage());
                btnSignUp.setDisable(true);
                txtEmailError.setVisible(true);
                txtEmailError.setText(e.getMessage());
                correctEmail = false;
            }

        }
        );

        LOG.info("Setting validator for the username field");
        txtUsername.textProperty().addListener((Observable) -> {
            //When the text is being modified in the text field

            try {
                txtUsernameError.setVisible(false);
                //Validating the username
                String usernamePattern = "^[a-zA-Z0-9\\u00D1\\u00f1\\u00C7\\u00e7]+$";
                pattern = Pattern.compile(usernamePattern);
                matcher = pattern.matcher(txtUsername.getText());

                //If the username lenghth is less than 5
                if (txtUsername.getText().length() < 5) {
                    throw new Exception("El nombre de usuario debe tener mínimo 5 carácteres");
                } //If the username length is more than 50
                else if (txtUsername.getText().length() > 50) {
                    txtUsername.setText(txtUsername.getText().substring(0, 50));
                    throw new Exception("El nombre de usuario no puede tener mas de 50 caracteres");
                } //If the text don't match with the pattern
                else if (!matcher.matches()) {
                    throw new Exception("El nombre de usuario solo puede tener carácteres alfanuméricos");
                }
                //Else
                correctUserName = true;
                btnSignUp.setDisable((!(correctFullName && correctEmail && correctPassword && correctUserName && correctPasswordConfirmation && comboboxSelected)));

            } catch (Exception e) {
                LOG.warning(e.getMessage());
                btnSignUp.setDisable(true);
                txtUsernameError.setVisible(true);
                txtUsernameError.setText(e.getMessage());
                correctUserName = false;
            }
        }
        );

        LOG.info("Setting validator for the password field");
        txtPassword.textProperty().addListener((Observable) -> {
            //When the text is being modified in the text field

            try {
                txtPasswordError.setVisible(false);
                correctPassword = passwordValidator(txtPassword.getText());
            } catch (Exception e) {
                btnSignUp.setDisable(true);
                txtPasswordError.setVisible(true);
                txtPasswordError.setText(e.getMessage());
                correctPassword = false;
            }

            btnSignUp.setDisable((!(correctFullName && correctEmail && correctPassword && correctUserName && correctPasswordConfirmation && comboboxSelected)));
        }
        );

        LOG.info("Setting validator to check both the password and the confirmation is the same");
        txtConfirmPassword.textProperty().addListener((Observable) -> {
            //When the text is being modified in the text field

            try {
                txtPasswordConfirmError.setVisible(false);

                // If the password confirmation don't match with the password
                if (!txtConfirmPassword.getText().equals(txtPassword.getText())) {
                    throw new Exception("La contraseña no coincide");
                }
                // If the password confirmation is empty
                if (txtConfirmPassword.getText().length() == 0) {
                    throw new Exception("El campo no puede estar vacio");
                }
                //Else
                correctPasswordConfirmation = true;
                btnSignUp.setDisable((!(correctFullName && correctEmail && correctPassword && correctUserName && correctPasswordConfirmation && comboboxSelected)));

            } catch (Exception e) {
                txtPasswordConfirmError.setVisible(true);
                txtPasswordConfirmError.setText(e.getMessage());
                correctPasswordConfirmation = false;
                btnSignUp.setDisable(true);
            }

        }
        );

        btnBack.setOnAction((ActionEvent) -> {
            LOG.info("Closing the window");
            myStage.close();
            primaryStage.show();
        }
        );

        btnSignUp.setOnAction(this::signUp);

        //Atiende al evento de cerrar la ventana
        myStage.setOnCloseRequest(
                (WindowEvent windowEvent) -> {
                    LOG.info("Opening exit alert confirmation");
                    alert = new Alert(Alert.AlertType.CONFIRMATION, "Quieres cerrar el programa?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait();

                    if (alert.getResult().equals(ButtonType.YES)) {
                        LOG.info("Closing the application");
                        Platform.exit();
                    } else {
                        LOG.info("Canceled application close");
                        windowEvent.consume();
                    }
                }
        );

        cmbxUserType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            LOG.info("Selecting a user type");
            try {
                userType = cmbxUserType.getSelectionModel().getSelectedItem();

                if (userType == null) {
                    throw new Exception();
                } else {
                    comboboxSelected = true;
                    btnSignUp.setDisable((!(correctFullName && correctEmail && correctPassword && correctUserName && correctPasswordConfirmation && comboboxSelected)));
                }

            } catch (Exception e) {
                LOG.severe("A user type must be selected");
                btnSignUp.setDisable(true);
                userType = null;
            }

        });
        myStage.showAndWait();
    }

    /**
     * This method validates that the introduced email is syntactically correct
     *
     * @param email The email we are going to check
     * @return Returns True if the email matches with the pattern, False if
     * doesn't match
     */
    private Boolean emailValidator(String email) {
        if (email.length() <= 100) {
            return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                    .matcher(email)
                    .matches();
        }
        return false;
    }

    /**
     * this method validates that the password is correct
     *
     * @param password The password we are going to validate
     * @return Returns True if the password is correct, False if is not.
     * @throws Exception if the password length is less than 8 characters or if
     * the password is not correct
     */
    private Boolean passwordValidator(String password) throws Exception {
        String PASSWORD_PATTERN
                = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\\$%^&\\*\\?]).{8,100}$";

        if (password.length() < 8) {
            throw new Exception("La contraseña debe de tener al menos 8 caracteres");
        } else if (password.length() > 100) {
            throw new Exception("La contraseña no puede tener más de 100 carácteres");
        } else {
            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(password);

            if (matcher.matches()) {
                return true;
            }
        }
        throw new Exception("La contraseña no es válida, debe tener al menos una mayúscula, \n una minúscula, un número y un carácter especial");
    }

    /**
     * This method sends de user to the server to register it
     *
     * @param event the event that method is going to be launched by
     */
    private void signUp(ActionEvent event) {
        LOG.info("Starting the sign up and setting up all equired objects");
        User user = null;

        try {
            //TODO connection and user types

            if (userType.equals(UserPrivilege.STUDENT)) {
                user = new Student();
            } else if (userType.equals(UserPrivilege.TEACHER)) {
                user = new Teacher();
            }

            //Setting the user data and the message data
            user.setFullName(txtFullName.getText());
            user.setEmail(txtEmail.getText());
            user.setPassword(txtPassword.getText());
            user.setLogin(txtUsername.getText().trim());
            user.setPrivilege(userType);
            user.setStatus(UserStatus.ENABLED);
            user.setPassword(user.getPassword());

            //Sign up   
            if (user.getPrivilege().equals(UserPrivilege.STUDENT)) {
                userController.createStudent_XML(user);
            } else if (user.getPrivilege().equals(UserPrivilege.TEACHER)) {
                userController.createTeacher_XML(user);
            }

            alert = new Alert(Alert.AlertType.INFORMATION, "El usuario ha sido correctamente creado");
            alert.showAndWait();
            /*
                myStage.close();
                this.primaryStage.show();
             */

        } catch (BusinessLogicException e) {
            LOG.severe("An error happened while registering the user: " + e.getMessage());
            alert = new Alert(Alert.AlertType.ERROR, "Ha sucedido un error al registrarse, intentelo de nuevo mas tarde por favor");
            alert.showAndWait();
        }

        /*
        try {
            message = clientSocket.connectToServer(message);

            switch (message.getOperation()) {
                case USER_EXISTS:
                    throw new UserAlreadyExistsException("El usuario ya existe, pruebe con otro");
                case OK:
                    alert = new Alert(Alert.AlertType.CONFIRMATION, "El usuario ha sido registrado correctamente", ButtonType.OK);
                    alert.showAndWait();
                    LOG.info("Se ha registrado el usuario correctamente. Saliendo al anterior ventana...");
                    this.myStage.close();
                    this.primaryStage.show();
                    break;
                case SERVER_FULL:
                    throw new ServerFullException("El servdor esta lleno, intentelo mas tarde");
            }
        } catch (ServerErrorException e) {
            LOG.severe(e.getMessage());
            alert = new Alert(Alert.AlertType.ERROR, "Error al conectarse con el servidor, intentelo de nuevo mas tarde", ButtonType.OK);
            alert.showAndWait();
        } catch (UserAlreadyExistsException ex) {
            LOG.warning(ex.getMessage());
            alert = new Alert(Alert.AlertType.ERROR, "El usuario ya existe, pruebe con otro", ButtonType.OK);
            alert.showAndWait();

        } catch (ServerFullException ex) {
            LOG.severe(ex.getMessage());
            alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
         */
    }
}
