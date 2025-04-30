package com.student.management.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.student.management.util.DatabaseConnection;
import com.student.management.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddStudentController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private DatePicker dobPicker;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private TextArea addressField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;

    @FXML
    public void initialize() {
        genderComboBox.getItems().addAll("Male", "Female", "Other");
    }

    @FXML
    private void handleSave() {
        if (validateInput()) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "INSERT INTO students (first_name, last_name, date_of_birth, gender, address, phone_number, email, enrollment_date) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, firstNameField.getText());
                    stmt.setString(2, lastNameField.getText());
                    stmt.setDate(3, java.sql.Date.valueOf(dobPicker.getValue()));
                    stmt.setString(4, genderComboBox.getValue());
                    stmt.setString(5, addressField.getText());
                    stmt.setString(6, phoneField.getText());
                    stmt.setString(7, emailField.getText());
                    stmt.setDate(8, java.sql.Date.valueOf(LocalDate.now()));

                    stmt.executeUpdate();
                    
                    showSuccess("Success", "Student added successfully!");
                    closeWindow();
                }
            } catch (SQLException e) {
                showError("Database Error", "Error adding student: " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private boolean validateInput() {
        StringBuilder errors = new StringBuilder();

        if (firstNameField.getText().isEmpty()) {
            errors.append("First name is required\n");
        }
        if (lastNameField.getText().isEmpty()) {
            errors.append("Last name is required\n");
        }
        if (dobPicker.getValue() == null) {
            errors.append("Date of birth is required\n");
        }
        if (genderComboBox.getValue() == null) {
            errors.append("Gender is required\n");
        }
        if (emailField.getText().isEmpty()) {
            errors.append("Email is required\n");
        } else if (!emailField.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.append("Invalid email format\n");
        }

        if (errors.length() > 0) {
            showError("Validation Error", errors.toString());
            return false;
        }

        return true;
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeWindow() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
} 