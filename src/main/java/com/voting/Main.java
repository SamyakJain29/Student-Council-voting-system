package com.voting;

import java.io.IOException;

import com.voting.util.DatabaseUtils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        DatabaseUtils.initializeDatabase();

        showLoginScreen();

        primaryStage.setTitle("AI-Powered Student Council Voting System");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    public static void showLoginScreen() {
        loadScene("ui/Login.fxml", "Login", 800, 600);
    }

    public static void showRegisterScreen() {
        loadScene("ui/Register.fxml", "Register", 800, 600);
    }

    public static void showStudentDashboard() {
        loadScene("ui/StudentDashboard.fxml", "Student Dashboard", 900, 700);
    }

    public static void showAdminDashboard() {
        loadScene("ui/AdminDashboard.fxml", "Admin Dashboard", 1000, 700);
    }

    public static void showResultsScreen() {
        loadScene("ui/ResultsDashboard.fxml", "Live Results", 900, 700);
    }

    private static void loadScene(String fxmlPath, String title, double width, double height) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/voting/" + fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);

            // Add modern CSS styles
            String cssPath = Main.class.getResource("/css/styles.css").toExternalForm();
            scene.getStylesheets().add(cssPath);

            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Failed to load " + title + ".");
        }
    }

    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
