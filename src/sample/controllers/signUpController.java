package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.databases.DatabaseHandler;
import sample.databases.User;

public class signUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpUserName;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private Button signUpLogInButton;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField signUpLocation;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    void initialize() {

        signUpLogInButton.setOnAction(event -> signUpNewUser());
    }

        // Registration to new user
        private void signUpNewUser(){
            DatabaseHandler dbHandler = new DatabaseHandler();
            String firstName = signUpFirstName.getText();
            String lastName = signUpLastName.getText();
            String userName = signUpUserName.getText();
            String password = signUpPassword.getText();
            String location = signUpLocation.getText();
            String gender;
            if (!signUpCheckBoxMale.isSelected() && signUpCheckBoxFemale.isSelected())
                gender = "Female";
            else {
                gender = "Male";
            }
            User user = new User(firstName, lastName, userName, password, location, gender);
            dbHandler.signUpUserRecorder(user);
        }
    }
