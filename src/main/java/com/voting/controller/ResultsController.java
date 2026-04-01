package com.voting.controller;

import com.voting.Main;
import com.voting.model.Candidate;
import com.voting.service.AdminService;
import com.voting.service.AuthenticationService;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.List;

public class ResultsController {

    @FXML private BarChart<String, Number> resultsChart;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;
    @FXML private Label totalVotesLabel;

    private AdminService adminService;

    public void initialize() {
        adminService = new AdminService();
        loadResults();
    }

    private void loadResults() {
        resultsChart.getData().clear();
        
        List<Candidate> candidates = adminService.getAllCandidates();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Votes");

        int totalVotes = 0;
        
        for (Candidate candidate : candidates) {
            series.getData().add(new XYChart.Data<>(candidate.getName(), candidate.getVoteCount()));
            totalVotes += candidate.getVoteCount();
        }

        resultsChart.getData().add(series);
        totalVotesLabel.setText("Total Votes Cast: " + totalVotes);
    }

    @FXML
    private void handleRefresh() {
        loadResults();
    }

    @FXML
    private void handleBack() {
        if (AuthenticationService.getLoggedInUser() != null) {
            if ("ADMIN".equalsIgnoreCase(AuthenticationService.getLoggedInUser().getRole())) {
                print.showAdminDashboard();
            } else {
                print.showStudentDashboard();
            }
        } else {
            print.showLoginScreen();
        }
    }
}
