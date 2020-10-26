package sample.controllers;

/**
 *  * created by GGB 18/10/2020
 * KNOWME APP First window
 * Controller Class for Sigh In Window
 *
 **/

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animations.Shake;
import sample.databases.DatabaseHandler;
import sample.databases.User;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    void initialize() {

        // click to the LogIn button
        loginButton.setOnAction(event -> {
            String loginText = usernameField.getText().trim(); // TRIM -> delete spaces
            String loginPassword = passwordField.getText().trim();
            if(!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText,loginPassword);
            }
            else{
                System.out.println("ERROR! -> FIELD IS EMPTY!");
            }
        });

        // hide the window after clicking
        signUpButton.setOnAction(event -> {
            openNewScene("/sample/view/sighUp.fxml");
        });
    }

    // LogIn exist user , else show animation
    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet resultSet = dbHandler.getUserData(user);
        int counter = 0;
        try {
        while (resultSet.next()) {
            counter++;
        }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if(counter >= 1){
            openNewScene("/sample/view/app.fxml");
            System.out.println("SUCSSES -> USER EXIST");
        }
        else {
            Shake userLogFieldAnimation = new Shake(usernameField);
            Shake passwordFieldAnimtion = new Shake(passwordField);
            userLogFieldAnimation.playAnimation();
            passwordFieldAnimtion.playAnimation();
        }
    }

    // after correct logIn -> transfer to a new window
    public void openNewScene(String window){
        signUpButton.getScene().getWindow().hide();
        // load new view window
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // load , show , wait new window
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
