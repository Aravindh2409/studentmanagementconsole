package com.studentmgmt.models;

import java.time.LocalDateTime;

/**
 * StudentType values: "full" or "part"
 */
public class Student {
    private int studentId;
    private String studentName;
    private String email;
    private String phone;
    private int branchId;
    private LocalDateTime enrollmentDate;
    private String studentType;

    /**
     * Constructor with all fields
     */
    public Student(int studentId, String studentName, String email, String phone, int branchId, LocalDateTime enrollmentDate) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.phone = phone;
        this.branchId = branchId;
        this.enrollmentDate = enrollmentDate;
        this.studentType = null; // Default value
    }

    /**
     * Constructor with all fields including studentType
     */
    public Student(int studentId, String studentName, String email, String phone, int branchId, LocalDateTime enrollmentDate, String studentType) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.phone = phone;
        this.branchId = branchId;
        this.enrollmentDate = enrollmentDate;
        this.studentType = studentType;
    }

    /**
     * Constructor without ID (for new students)
     */
    public Student(String studentName, String email, String phone, int branchId) {
        this.studentName = studentName;
        this.email = email;
        this.phone = phone;
        this.branchId = branchId;
    }

    /**
     * Constructor for new student with explicit type
     */
    public Student(String studentName, String email, String phone, int branchId, String studentType) {
        this.studentName = studentName;
        this.email = email;
        this.phone = phone;
        this.branchId = branchId;
        this.studentType = studentType;
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    @Override
    public String toString() {
        return String.format("Student ID: %d | Name: %s | Type: %s | Email: %s | Phone: %s | Branch ID: %d | Joined: %s",
                studentId, studentName, studentType == null ? "full" : studentType, email, phone, branchId,
                enrollmentDate == null ? "-" : enrollmentDate.toLocalDate().toString());
    }
}
