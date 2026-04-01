package com.voting.controller;

import com.voting.Main;
import com.voting.model.Candidate;
import com.voting.model.User;
import com.voting.service.AdminService;
import com.voting.service.AuthenticationService;
import com.voting.service.VotingService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

public class StudentDashboardController {

    @FXML private Label welcomeLabel;
    @FXML private FlowPane candidatesContainer;
    
    private AdminService adminService;
    private VotingService votingService;
    private User currentUser;

    public void initialize() {
        adminService = new AdminService();
        votingService = new VotingService();
        currentUser = AuthenticationService.getLoggedInUser();
        
        if (currentUser != null) {
            welcomeLabel.setText("Welcome, " + currentUser.getName() + "!");
        }
        
        loadCandidates();
    }

    private void loadCandidates() {
        candidatesContainer.getChildren().clear();
        List<Candidate> candidates = adminService.getAllCandidates();
        
        for (Candidate candidate : candidates) {
            VBox card = new VBox(10);
            card.getStyleClass().add("candidate-card");
            card.setPrefWidth(250);
            card.setPrefHeight(200);
            card.setAlignment(Pos.CENTER);
            
            Label nameLabel = new Label(candidate.getName());
            nameLabel.getStyleClass().add("candidate-name");
            
            Label deptLabel = new Label(candidate.getDepartment());
            deptLabel.getStyleClass().add("candidate-dept");
            
            Button voteButton = new Button("Vote");
            voteButton.getStyleClass().add("btn-vote");
            
            if (currentUser != null && currentUser.hasVoted()) {
                voteButton.setDisable(true);
                voteButton.setText("Voted");
            } else {
                voteButton.setOnAction(e -> handleVote(candidate));
            }
            
            card.getChildren().addAll(nameLabel, deptLabel, voteButton);
            candidatesContainer.getChildren().add(card);
        }
    }

    private void handleVote(Candidate candidate) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Vote");
        confirm.setHeaderText("You are about to vote for " + candidate.getName());
        confirm.setContentText("Are you sure? This action cannot be undone.");
        
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String ipAddress = "127.0.0.1";
            try {
                ipAddress = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException ignored) {}
            
            String systemInfo = System.getProperty("os.name") + " " + System.getProperty("os.version");
            
            VotingService.VotingResult voteResult = votingService.castVote(currentUser.getId(), candidate.getId(), ipAddress, systemInfo);
            
            if (voteResult.isSuccess()) {
                print.showAlert(Alert.AlertType.INFORMATION, "Success", voteResult.getMessage());
                loadCandidates(); // Refresh UI to disable buttons
            } else {
                print.showAlert(Alert.AlertType.ERROR, "Vote Failed", voteResult.getMessage());
            }
        }
    }

    @FXML
    private void viewResults() {
        print.showResultsScreen();
    }

    @FXML
    private void logout() {
        AuthenticationService.logout();
        print.showLoginScreen();
    }
}
