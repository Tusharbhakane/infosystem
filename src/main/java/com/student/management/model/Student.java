package com.student.management.model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Student {
    private final IntegerProperty studentId = new SimpleIntegerProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> dateOfBirth = new SimpleObjectProperty<>();
    private final StringProperty gender = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty phoneNumber = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> enrollmentDate = new SimpleObjectProperty<>();

    public Student() {
    }

    public Student(String firstName, String lastName, LocalDate dateOfBirth, String gender,
                  String address, String phoneNumber, String email, LocalDate enrollmentDate) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.dateOfBirth.set(dateOfBirth);
        this.gender.set(gender);
        this.address.set(address);
        this.phoneNumber.set(phoneNumber);
        this.email.set(email);
        this.enrollmentDate.set(enrollmentDate);
    }

    // Property getters
    public IntegerProperty studentIdProperty() {
        return studentId;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(getFirstName() + " " + getLastName());
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty phoneProperty() {
        return phoneNumber;
    }

    // Regular getters and setters
    public int getStudentId() {
        return studentId.get();
    }

    public void setStudentId(int studentId) {
        this.studentId.set(studentId);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth.get();
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate.get();
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate.set(enrollmentDate);
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }
} 