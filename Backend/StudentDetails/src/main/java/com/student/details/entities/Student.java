package com.student.details.entities;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "students")
public class Student {

    @Id
    private String id;

    @NotBlank(message = "First Name is required")
    @Size(min = 3,max = 10, message = "FirstName must be between 3 and 10 characters")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Size(min = 3, max = 10, message = "LastName must be between 3 and 10 Characters")
    private String lastName;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Size(max = 100, message = "Email must be under 100 characters")
    private String email;

    @NotNull(message = "Phone number is required")
    @Min(value = 1000000000L, message = "Phone number must be at least 10 digits")
    @Max(value = 999999999995L, message = "Phone number is too long")
    private Long phoneNumber;

    @NotBlank(message = "Address is required")
    @Size(min = 3,max = 200, message = "Address must be between 3 and 200 characters")
    private String address;

    @NotNull(message = "enrollmentDate is required")
    private LocalDate enrollmentDate;

    public Student() {
    }

    public Student(String id, String firstName, String lastName, LocalDate dateOfBirth, String email, Long phoneNumber, String address, LocalDate enrollmentDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.enrollmentDate = enrollmentDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public void setStudentId(String id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String getStudentId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", enrollmentDate=" + enrollmentDate +
                '}';
    }
}
