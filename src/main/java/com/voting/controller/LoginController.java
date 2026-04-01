package com.voting.controller;

import com.voting.Main;
import com.voting.model.User;
import com.voting.service.AuthenticationService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField studentIdField;
    @FXML private PasswordField passwordField;

    private AuthenticationService authService;

    public void initialize() {
        authService = new AuthenticationService();
    }

    @FXML
    private void handleLogin() {
        String studentId = studentIdField.getText();
        String password = passwordField.getText();

        if (studentId.isEmpty() || password.isEmpty()) {
            print.showAlert(Alert.AlertType.ERROR, "Login Error", "Please fill in all fields.");
            return;
        }

        User user = authService.login(studentId, password);

        if (user != null) {
            print.showAlert(Alert.AlertType.INFORMATION, "Login Success", "Welcome, " + user.getName() + "!");
            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                print.showAdminDashboard();
            } else {
                print.showStudentDashboard();
            }
        } else {
            print.showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid Student ID or Password.");
        }
    }

    @FXML
    private void goToRegister() {
        print.showRegisterScreen();
    }
}
