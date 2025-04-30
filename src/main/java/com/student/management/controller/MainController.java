package com.student.management.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.student.management.model.Student;
import com.student.management.util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {
    @FXML private Label totalStudentsLabel;
    @FXML private Label totalCoursesLabel;
    @FXML private TableView<Student> studentsTable;
    @FXML private TableColumn<Student, Integer> studentIdColumn;
    @FXML private TableColumn<Student, String> studentNameColumn;
    @FXML private TableColumn<Student, String> studentEmailColumn;
    @FXML private TableColumn<Student, String> studentPhoneColumn;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadDashboardData();
        setupStudentTable();
    }

    private void loadDashboardData() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Load total students
            String studentCountQuery = "SELECT COUNT(*) FROM students";
            try (PreparedStatement stmt = conn.prepareStatement(studentCountQuery);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalStudentsLabel.setText(String.valueOf(rs.getInt(1)));
                }
            }

            // Load total courses
            String courseCountQuery = "SELECT COUNT(*) FROM courses";
            try (PreparedStatement stmt = conn.prepareStatement(courseCountQuery);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalCoursesLabel.setText(String.valueOf(rs.getInt(1)));
                }
            }
        } catch (SQLException e) {
            showError("Database Error", "Error loading dashboard data: " + e.getMessage());
        }
    }

    private void setupStudentTable() {
        studentIdColumn.setCellValueFactory(cellData -> cellData.getValue().studentIdProperty().asObject());
        studentNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        studentEmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        studentPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());

        loadStudents();
        studentsTable.setItems(studentList);
    }

    private void loadStudents() {
        studentList.clear();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM students";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setStudentId(rs.getInt("student_id"));
                    student.setFirstName(rs.getString("first_name"));
                    student.setLastName(rs.getString("last_name"));
                    student.setEmail(rs.getString("email"));
                    student.setPhoneNumber(rs.getString("phone_number"));
                    studentList.add(student);
                }
            }
        } catch (SQLException e) {
            showError("Database Error", "Error loading students: " + e.getMessage());
        }
    }

    @FXML
    private void handleAddStudent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_student.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add New Student");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showError("Error", "Could not open add student window: " + e.getMessage());
        }
    }

    @FXML
    private void handleAddCourse() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_course.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add New Course");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showError("Error", "Could not open add course window: " + e.getMessage());
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
} 