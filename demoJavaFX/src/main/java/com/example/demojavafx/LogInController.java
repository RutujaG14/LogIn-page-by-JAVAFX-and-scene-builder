package com.example.demojavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;


public class LogInController {

    @FXML
    private TextField userNameText;

    @FXML
    private TextField passwordText;

    private static final String LOGIN_URL = "http://localhost:8080/logInUser";

    RestTemplate restTemplate = new RestTemplate();

    @FXML
    private void onClickLogInButton() {
        String userName = userNameText.getText();
        String password = passwordText.getText();

        Map<String , String> loginData = new HashMap<>();

        loginData.put("Username ", userName);
        loginData.put("Password ", password);

        //Using DTO--

        logInDTO loginDTO = new logInDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);

        try {
            String response = restTemplate.postForObject(LOGIN_URL, loginDTO, String.class);
            showAlert(Alert.AlertType.INFORMATION, "Login Response", response);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "An error occurred while trying to log in: " + e.getMessage());
        }


//        try {
//            String response = restTemplate.postForObject(LOGIN_URL, loginData, String.class);
//            showAlert(Alert.AlertType.INFORMATION, "Login Response", response);
//
//        } catch (Exception e) {
//            showAlert(Alert.AlertType.ERROR, "Login Failed", "An error occurred while trying to log in: " + e.getMessage());
//        }

}

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onClickCancelButton() {
        userNameText.clear();
        passwordText.clear();

    }
}
























//Alternative--
// try{
//
//        String response = restTemplate.postForObject(LOGIN_URL + "?username=" + userName + "&password=" + password, null, String.class);
//        showAlert(Alert.AlertType.INFORMATION, "Login Successful", response);
//    }
//        catch(Exception e){
//
//            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
//    }

