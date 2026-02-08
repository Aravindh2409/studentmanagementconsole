package services;

import database.DatabaseConnection;
import models.Branch;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BranchService {
    private Connection conn;

    public BranchService() {
        this.conn = DatabaseConnection.getConnection();
    }

    /**
     * Add a new branch to the database
     */
    public boolean addBranch(Branch branch) {
        String sql = "INSERT INTO branches (branch_name, branch_code) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, branch.getBranchName());
            pstmt.setString(2, branch.getBranchCode());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("✓ Branch added successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding branch: " + e.getMessage());
        }
        return false;
    }

    /**
     * Get all branches from the database
     */
    public List<Branch> getAllBranches() {
        List<Branch> branches = new ArrayList<>();
        String sql = "SELECT branch_id, branch_name, branch_code FROM branches";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int branchId = rs.getInt("branch_id");
                String branchName = rs.getString("branch_name");
                String branchCode = rs.getString("branch_code");
                branches.add(new Branch(branchId, branchName, branchCode));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving branches: " + e.getMessage());
        }
        return branches;
    }

    /**
     * Get branch by ID
     */
    public Branch getBranchById(int branchId) {
        String sql = "SELECT branch_id, branch_name, branch_code FROM branches WHERE branch_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, branchId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Branch(
                            rs.getInt("branch_id"),
                            rs.getString("branch_name"),
                            rs.getString("branch_code")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving branch: " + e.getMessage());
        }
        return null;
    }

    /**
     * Update branch information
     */
    public boolean updateBranch(Branch branch) {
        String sql = "UPDATE branches SET branch_name = ?, branch_code = ? WHERE branch_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, branch.getBranchName());
            pstmt.setString(2, branch.getBranchCode());
            pstmt.setInt(3, branch.getBranchId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("✓ Branch updated successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error updating branch: " + e.getMessage());
        }
        return false;
    }

    /**
     * Delete branch by ID
     */
    public boolean deleteBranch(int branchId) {
        String sql = "DELETE FROM branches WHERE branch_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, branchId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("✓ Branch deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting branch: " + e.getMessage());
        }
        return false;
    }
}
