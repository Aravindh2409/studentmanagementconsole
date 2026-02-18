package com.studentmgmt.models;

public class Branch {
    private int branchId;
    private String branchName;
    private String branchCode;

    /**
     * Constructor for Branch
     */
    public Branch(int branchId, String branchName, String branchCode) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.branchCode = branchCode;
    }

    /**
     * Constructor without ID (for new branches)
     */
    public Branch(String branchName, String branchCode) {
        this.branchName = branchName;
        this.branchCode = branchCode;
    }

    // Getters and Setters
    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Override
    public String toString() {
        return String.format("Branch ID: %d | Name: %s | Code: %s",
                branchId, branchName, branchCode);
    }
}
