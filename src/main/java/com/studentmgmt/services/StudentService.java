package com.studentmgmt.services;

import com.studentmgmt.database.DatabaseConnection;
import com.studentmgmt.models.Student;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class StudentService {
    private Connection conn;

    public StudentService() {
        this.conn = DatabaseConnection.getConnection();
    }

    /**
     * Enroll a new student to the database
     */
    public boolean enrollStudent(Student student) {
        String sql = "INSERT INTO students (student_name, email, phone, branch_id, student_type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getStudentName());
            pstmt.setString(2, student.getEmail());
            pstmt.setString(3, student.getPhone());
            pstmt.setInt(4, student.getBranchId());
            pstmt.setString(5, student.getStudentType() == null ? "full" : student.getStudentType());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("✓ Student enrolled successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error enrolling student: " + e.getMessage());
        }
        return false;
    }

    /**
     * Get all students from the database
     */
    public Set<Student> getAllStudents() {
        Set<Student> students = new LinkedHashSet<>();
        String sql = "SELECT student_id, student_name, email, phone, branch_id, student_type, enrollment_date FROM students";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int branchId = rs.getInt("branch_id");
                LocalDateTime enrollmentDate = rs.getTimestamp("enrollment_date").toLocalDateTime();
                String studentType = rs.getString("student_type");
                students.add(new Student(studentId, studentName, email, phone, branchId, enrollmentDate, studentType));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving students: " + e.getMessage());
        }
        return students;
    }

    /**
     * Get student by ID
     */
    public Student getStudentById(int studentId) {
        String sql = "SELECT student_id, student_name, email, phone, branch_id, student_type, enrollment_date FROM students WHERE student_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("student_id"),
                            rs.getString("student_name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getInt("branch_id"),
                            rs.getTimestamp("enrollment_date").toLocalDateTime(),
                            rs.getString("student_type")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving student: " + e.getMessage());
        }
        return null;
    }

    /**
     * Get students by branch ID
     */
    public Set<Student> getStudentsByBranch(int branchId) {
        Set<Student> students = new LinkedHashSet<>();
        String sql = "SELECT student_id, student_name, email, phone, branch_id, student_type, enrollment_date FROM students WHERE branch_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, branchId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int studentId = rs.getInt("student_id");
                    String studentName = rs.getString("student_name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    LocalDateTime enrollmentDate = rs.getTimestamp("enrollment_date").toLocalDateTime();
                    String studentType = rs.getString("student_type");
                    students.add(new Student(studentId, studentName, email, phone, branchId, enrollmentDate, studentType));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving students by branch: " + e.getMessage());
        }
        return students;
    }

    /**
     * Update student information
     */
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET student_name = ?, email = ?, phone = ?, branch_id = ?, student_type = ? WHERE student_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getStudentName());
            pstmt.setString(2, student.getEmail());
            pstmt.setString(3, student.getPhone());
            pstmt.setInt(4, student.getBranchId());
            pstmt.setString(5, student.getStudentType() == null ? "full" : student.getStudentType());
            pstmt.setInt(6, student.getStudentId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("✓ Student updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
        }
        return false;
    }

    /**
     * Delete student by ID
     */
    public boolean deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("✓ Student deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
        }
        return false;
    }

    /**
     * Get total count of students
     */
    public int getTotalStudentCount() {
        String sql = "SELECT COUNT(*) as total FROM students";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Error counting students: " + e.getMessage());
        }
        return 0;
    }
}
