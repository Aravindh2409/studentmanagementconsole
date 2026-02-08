package database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseInitializer {
    /**
     * Create tables for the student management system (MySQL)
     */
    public static void initializeTables() {
        Connection conn = DatabaseConnection.getConnection();
        try (Statement stmt = conn.createStatement()) {
            // Create Branch table
            String createBranchTable = "CREATE TABLE IF NOT EXISTS branches (" +
                    "branch_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "branch_name VARCHAR(100) NOT NULL UNIQUE," +
                    "branch_code VARCHAR(10) NOT NULL UNIQUE," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ") ENGINE=InnoDB";
            stmt.execute(createBranchTable);
            System.out.println("✓ Branches table created/verified");

            // Create Student table
            String createStudentTable = "CREATE TABLE IF NOT EXISTS students (" +
                    "student_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "student_name VARCHAR(100) NOT NULL," +
                    "email VARCHAR(100) UNIQUE," +
                    "phone VARCHAR(15)," +
                    "branch_id INT NOT NULL," +
                    "enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY(branch_id) REFERENCES branches(branch_id) ON DELETE CASCADE" +
                    ") ENGINE=InnoDB";
            stmt.execute(createStudentTable);
            System.out.println("✓ Students table created/verified");

        } catch (SQLException e) {
            System.err.println("Error initializing tables: " + e.getMessage());
        }
    }

    /**
     * Insert sample data into the database (only when empty)
     */
    public static void insertSampleData() {
        Connection conn = DatabaseConnection.getConnection();
        try (Statement stmt = conn.createStatement()) {
            // Verify tables exist
            if (!tableExists(conn, "branches") || !tableExists(conn, "students")) {
                System.out.println("Tables do not exist yet!");
                return;
            }

            // Insert branches only if none exist
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as cnt FROM branches")) {
                if (rs.next() && rs.getInt("cnt") == 0) {
                    String[] branches = {
                            "INSERT IGNORE INTO branches (branch_name, branch_code) VALUES ('Computer Science', 'CS')",
                            "INSERT IGNORE INTO branches (branch_name, branch_code) VALUES ('Electronics and Communication', 'ECE')",
                            "INSERT IGNORE INTO branches (branch_name, branch_code) VALUES ('Mechanical Engineering', 'ME')",
                            "INSERT IGNORE INTO branches (branch_name, branch_code) VALUES ('Civil Engineering', 'CE')",
                            "INSERT IGNORE INTO branches (branch_name, branch_code) VALUES ('Electrical Engineering', 'EE')"
                    };

                    for (String branch : branches) {
                        stmt.execute(branch);
                    }
                    System.out.println("✓ Sample branches inserted");
                }
            }

            // Insert students only if none exist
            try (ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) as cnt FROM students")) {
                if (rs2.next() && rs2.getInt("cnt") == 0) {
                    String[] students = {
                            "INSERT IGNORE INTO students (student_name, email, phone, branch_id) VALUES ('Raj Kumar', 'raj.kumar@example.com', '9876543210', 1)",
                            "INSERT IGNORE INTO students (student_name, email, phone, branch_id) VALUES ('Priya Singh', 'priya.singh@example.com', '9876543211', 1)",
                            "INSERT IGNORE INTO students (student_name, email, phone, branch_id) VALUES ('Amit Patel', 'amit.patel@example.com', '9876543212', 2)",
                            "INSERT IGNORE INTO students (student_name, email, phone, branch_id) VALUES ('Neha Sharma', 'neha.sharma@example.com', '9876543213', 3)",
                            "INSERT IGNORE INTO students (student_name, email, phone, branch_id) VALUES ('Vikram Desai', 'vikram.desai@example.com', '9876543214', 4)"
                    };

                    for (String student : students) {
                        stmt.execute(student);
                    }
                    System.out.println("✓ Sample students inserted");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error inserting sample data: " + e.getMessage());
        }
    }

    /**
     * Check if a table exists in the database
     */
    private static boolean tableExists(Connection conn, String tableName) {
        try (ResultSet rs = conn.getMetaData().getTables(null, null, tableName, new String[]{"TABLE"})) {
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
}
