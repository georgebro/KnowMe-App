package sample.controllers;

/**
 *  * created by GGB 18/10/2020
 * KNOWME APP First window
 * Controller Class for Sigh In Window
 *
 **/

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

        // нажатие на кнопку логин
        loginButton.setOnAction(event -> {
            String loginText = usernameField.getText().trim(); // TRIM -> убирает пробелы в строке
            String loginPassword = passwordField.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText,loginPassword);
            }
            else{
                System.out.println("FIELDS IS EMPTY!");
            }

        });


        // hide the window after clicking
        signUpButton.setOnAction(event -> {
            signUpButton.getScene().getWindow().hide();

            // load new view window
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/sighUp.fxml"));
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
        });

    }

    private void loginUser(String loginText, String loginPassword) {
        /////****//////
    }
}
