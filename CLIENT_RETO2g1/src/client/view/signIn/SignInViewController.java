/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view.signIn;

import client.beans.User;
import client.logic.exception.BusinessLogicException;
import client.view.signUp.SignUpViewController;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class This class is the controller of the Sign In view
 *
 * @author Joritz
 * @author Henrique
 */
public class SignInViewController {

    /**
     * This is the logger of the class
     */
    private static final Logger LOG = Logger.getLogger(SignInViewController.class.getName());
    /**
     * This is the pattern that is going to be used to validate some fields
     */
    private Pattern pattern = null;
    /**
     * This is the matcher to compare that the field to validate that the field
     * data matches to a valid pattern
     */
    private Matcher matcher = null;
    /**
     * Indicates that if the username field is filled
     */
    private Boolean usernameFilled = false;
    /**
     * Indicates that if the password field is filled
     */
    private Boolean passwordFilled = false;
    /**
     * The constant tooltip of the user field
     */
    private final Tooltip userTooltip = new Tooltip();
    /**
     * The constant tooltip of the password field
     */
    private final Tooltip passTooltip = new Tooltip();
    /**
     * Indicates to the tooltip how many is going to move from the original
     * position
     */
    private final Double offset = 35d;
    /**
     * The primary stage
     */
    private Stage primaryStage;

    /**
     * The user name text field
     */
    @FXML
    private TextField txtUser;
    /**
     * The passwor field
     */
    @FXML
    private PasswordField txtPassword;
    /**
     * The button to sign up
     */
    @FXML
    private Button btnSignIn;
    /**
     * The button to go to the sign up window
     */
    @FXML
    private Button btnSignUpView;
    /**
     * The label where the sign in error will be showed
     */
    @FXML
    private Label txtLogInError;

    /**
     * This method initialize the stage (@code SignInView)
     *
     * @param root the principal stage where this window will be showed
     * @param css The css file reference
     */
    public void initStage(Parent root) {
        LOG.info("Initiating Sign In View stage");

        //Set the scene, add the css and setting the client socket and the primary stage
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Iniciar Sesion");
        this.primaryStage = stage;

        LOG.info("Setting validator for the username field");
        txtUser.textProperty().addListener((Observable) -> {
            try {
                String error;
                //If the user field is empty
                if (txtUser.getText().length() == 0) {
                    userTooltip.hide();
                    throw new Exception("El nombre de usuario no puede estar vacío");
                }//If the length of the text is less than 5 
                else if (txtUser.getText().length() < 5) {
                    error = "El nombre de usuario debe tener mínimo 5 carácteres ";
                    showTooltip(stage, txtUser, error, userTooltip);
                    throw new Exception(error);
                }//If the length of the text is bigger than 50 
                else if (txtUser.getText().length() > 50) {
                    txtUser.setText(txtUser.getText().substring(0, 50));
                    error = "El nombre de usuario no puede tener más de 50 carácteres";
                    showTooltip(stage, txtUser, error, userTooltip);
                    throw new Exception(error);
                }
                //Else
                userTooltip.hide();
                usernameFilled = true;

                btnSignIn.setDisable(!(passwordFilled && usernameFilled));

            } catch (Exception ex) {
                //LOG.info(ex.getMessage());
                btnSignIn.setDisable(true);
                usernameFilled = false;
            }

        });

        LOG.info("Setting validator for the password field");
        txtPassword.textProperty().addListener((Observable) -> {
            try {
                String error;
                //If the password field is empty
                if (txtPassword.getText().length() == 0) {
                    passTooltip.hide();
                    throw new Exception("La contraseña no puede estar vacío");
                } //If the legth of the password is less than 8
                else if (txtPassword.getText().length() < 8) {
                    error = "La contraseña debe tener mínimo 8 carácteres";
                    showTooltip(stage, txtPassword, error, passTooltip);
                    throw new Exception(error);
                }//If the length of the password is bigger than 100
                else if (txtPassword.getText().length() > 100) {
                    txtPassword.setText(txtPassword.getText().substring(0, 100));
                    error = "La contraseña no puede tener más de 100 carácteres";
                    showTooltip(stage, txtPassword, error, passTooltip);
                    throw new Exception(error);
                }
                //Else
                passTooltip.hide();
                passwordFilled = true;

                btnSignIn.setDisable(!(usernameFilled && passwordFilled));

            } catch (Exception e) {
                //LOG.info(e.getMessage());
                btnSignIn.setDisable(true);
                passwordFilled = false;
            }

        });

        stage.setOnCloseRequest((WindowEvent WindowEvent) -> {
            LOG.info("Opening exit alert confirmation");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Quieres cerrar el programa?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            //If the user click on yes 
            if (alert.getResult() == ButtonType.YES) {
                Platform.exit();
            } else {
                WindowEvent.consume();
            }
        });

        stage.setOnShowing((Event) -> {
            LOG.info("Setting the status of the items shown on scene");
            txtUser.setDisable(false);
            txtPassword.setDisable(false);
            btnSignIn.setDisable(true);
            btnSignIn.setDefaultButton(true);
            btnSignUpView.setDisable(false);
            txtLogInError.setVisible(false);
            txtLogInError.setText("LogIn incorrecto, usuario y/o contraseña incorrecto");
            stage.setResizable(false);

        });
        //When the Sign in button is clicked
        btnSignIn.setOnAction(this::signIn);

        //When the sign up buttton is clicked
        btnSignUpView.setOnAction((Event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientProject/view/signUp/SignUpView.fxml"));
                Parent rootSignUp = (Parent) loader.load();
                SignUpViewController signUp = ((SignUpViewController) loader.getController());
                signUp.initStage(rootSignUp, stage);
            } catch (IOException ex) {
                LOG.info("No se puede abrir la ventana " + ex.getLocalizedMessage());
            }
        });
        stage.show();
    }

    /**
     * This is the method that permit to sign in if the user and password are
     * correct and the server returned that the operation is done correctly
     *
     * @param e The event object
     */
    private void signIn(ActionEvent e) {
        try {
            LOG.info("Starting the sign in and getting data from required fields");
            //Setting the user required data
            User user = new User();

            user.setLogin(txtUser.getText());
            user.setPassword(txtPassword.getText());

            String error = "Login incorrecto, compruebe el usuario y/o la contraseña";

            //Validating that the username is valid
            String usernamePattern = "^[a-zA-Z0-9]+$";
            pattern = Pattern.compile(usernamePattern);
            matcher = pattern.matcher(txtUser.getText());

            //If is not valid
            if (!matcher.matches()) {
                LOG.info(error);
            }

            //validate password
            String PASSWORD_PATTERN
                    = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!¡@#$%&¿?]).{8,100}$";
            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(txtPassword.getText());

            //If the password is not valid
            if (!matcher.matches()) {
                LOG.info("La contraseña no es válida, debe tener al menos una mayuscula, una minuscula, un número y un caracter especial");
            }

            //TODO
            /*
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clientProject/view/logged/LoggedView.fxml"));
            Parent root = (Parent) loader.load();
            LoggedViewController controller = ((LoggedViewController) loader.getController());
            controller.initStage(root, message.getUserData(), primaryStage, this.css);
            txtUser.setText("");
            txtPassword.setText("");
             */
            throw new BusinessLogicException("hola");
        } catch (BusinessLogicException ex) {
            LOG.severe(ex.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        } catch (Exception ex) {
            LOG.severe(ex.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ha ocurrido un error al iniciar la ventana de inicio de sesión", ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * Shows the corresponding tooltip in the corresponding field
     *
     * @param stage The stage where is going to be showed
     * @param txtFfield The textField where is going to be attached
     * @param text The text that is going to be showed
     * @param tp The tooltip
     */
    private void showTooltip(Stage stage, TextField txtFfield, String text, Tooltip tp) {

        tp.setText(text);
        //Setting the tooltip to the field and setting that when focus is lost is going to auto hide
        txtFfield.setTooltip(tp);
        tp.setAutoHide(true);

        //Setting the cordinates where is going to be showed
        tp.show(stage,
                txtFfield.getLayoutX() + txtFfield.getScene().getX() + txtFfield.getScene().getWindow().getX(),
                txtFfield.getLayoutY() + txtFfield.getScene().getY() + txtFfield.getScene().getWindow().getY() + offset);
    }

}
