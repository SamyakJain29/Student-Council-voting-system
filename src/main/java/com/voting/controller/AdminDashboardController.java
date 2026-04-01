package com.voting.controller;

import com.voting.Main;
import com.voting.model.Candidate;
import com.voting.service.AdminService;
import com.voting.service.AuthenticationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminDashboardController {

    @FXML private TextField nameField;
    @FXML private TextField departmentField;
    @FXML private TableView<Candidate> candidateTable;
    @FXML private TableColumn<Candidate, Integer> colId;
    @FXML private TableColumn<Candidate, String> colName;
    @FXML private TableColumn<Candidate, String> colDepartment;
    @FXML private TableColumn<Candidate, Integer> colVotes;

    private AdminService adminService;
    private ObservableList<Candidate> candidateObservableList;

    public void initialize() {
        adminService = new AdminService();
        candidateObservableList = FXCollections.observableArrayList();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colVotes.setCellValueFactory(new PropertyValueFactory<>("voteCount"));

        candidateTable.setItems(candidateObservableList);
        loadCandidates();
    }

    private void loadCandidates() {
        candidateObservableList.clear();
        candidateObservableList.addAll(adminService.getAllCandidates());
    }

    @FXML
    private void handleAddCandidate() {
        String name = nameField.getText();
        String dept = departmentField.getText();

        if (name.isEmpty() || dept.isEmpty()) {
            print.showAlert(Alert.AlertType.WARNING, "Input Error", "Please provide name and department.");
            return;
        }

        if (adminService.addCandidate(name, dept)) {
            nameField.clear();
            departmentField.clear();
            loadCandidates();
        } else {
            print.showAlert(Alert.AlertType.ERROR, "Error", "Failed to add candidate.");
        }
    }

    @FXML
    private void handleDeleteCandidate() {
        Candidate selected = candidateTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            print.showAlert(Alert.AlertType.WARNING, "Selection Error", "Please select a candidate to delete.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + selected.getName() + "?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            if (adminService.removeCandidate(selected.getId())) {
                loadCandidates();
            } else {
                print.showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete candidate.");
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
