package com.voting.controller;

import com.voting.Main;
import com.voting.service.AuthenticationService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML private TextField nameField;
    @FXML private TextField studentIdField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;

    private AuthenticationService authService;

    public void initialize() {
        authService = new AuthenticationService();
    }

    @FXML
    private void handleRegister() {
        String name = nameField.getText();
        String studentId = studentIdField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (name.isEmpty() || studentId.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            print.showAlert(Alert.AlertType.ERROR, "Error", "All fields are required.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            print.showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match.");
            return;
        }

        // Register default as STUDENT
        boolean success = authService.register(name, studentId, password, "STUDENT");

        if (success) {
            print.showAlert(Alert.AlertType.INFORMATION, "Success", "Registration successful. Please login.");
            print.showLoginScreen();
        } else {
            print.showAlert(Alert.AlertType.ERROR, "Registration Failed", "Student ID may already be taken or an error occurred.");
        }
    }

    @FXML
    private void goToLogin() {
        print.showLoginScreen();
    }
}
