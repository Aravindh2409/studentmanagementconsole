import database.DatabaseConnection;
import database.DatabaseInitializer;
import models.Branch;
import models.Student;
import services.BranchService;
import services.StudentService;

import java.util.List;
import java.util.Scanner;

public class StudentManagementApp {
    public static void main(String[] args) {
        DatabaseConnection.initConnection();
        DatabaseInitializer.initializeTables();
        DatabaseInitializer.insertSampleData();

        StudentService studentService = new StudentService();
        BranchService branchService = new BranchService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   Student Management System              ║");
        System.out.println("║   Welcome to the Student Portal          ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        boolean running = true;
        while (running) {
            System.out.println("\n─────────────────────────────────────────");
            System.out.println("         MAIN MENU");
            System.out.println("─────────────────────────────────────────");
            System.out.println("1. Enroll New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student Information");
            System.out.println("5. Delete Student");
            System.out.println("6. View All Branches");
            System.out.println("7. Add New Branch");
            System.out.println("8. Get Students by Branch");
            System.out.println("9. Display Statistics");
            System.out.println("0. Exit Application");
            System.out.println("─────────────────────────────────────────");
            System.out.print("Enter your choice (0-9): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("\n========== ENROLL NEW STUDENT ==========");
                    List<Branch> branches = branchService.getAllBranches();
                    if (branches.isEmpty()) {
                        System.out.println("✗ No branches available!");
                        break;
                    }
                    System.out.println("\nAvailable Branches:");
                    for (Branch branch : branches) System.out.println(branch);
                    System.out.print("\nEnter Student Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine().trim();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine().trim();
                    System.out.print("Enter Branch ID: ");
                    try {
                        int bid = Integer.parseInt(scanner.nextLine().trim());
                        if (branchService.getBranchById(bid) != null) {
                            studentService.enrollStudent(new Student(name, email, phone, bid));
                        } else {
                            System.out.println("✗ Invalid Branch ID!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("✗ Invalid input!");
                    }
                    break;

                case "2":
                    System.out.println("\n========== ALL STUDENTS ==========");
                    List<Student> students = studentService.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("✗ No students found!");
                    } else {
                        System.out.println("\nTotal: " + students.size() + "\n");
                        for (Student s : students) System.out.println(s);
                    }
                    break;

                case "3":
                    System.out.println("\n========== SEARCH STUDENT ==========");
                    System.out.print("Enter Student ID: ");
                    try {
                        Student s = studentService.getStudentById(Integer.parseInt(scanner.nextLine().trim()));
                        if (s != null) {
                            System.out.println("\n✓ Student Found:");
                            System.out.println(s);
                        } else {
                            System.out.println("✗ Student not found!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("✗ Invalid input!");
                    }
                    break;

                case "4":
                    System.out.println("\n========== UPDATE STUDENT ==========");
                    System.out.print("Enter Student ID: ");
                    try {
                        Student s = studentService.getStudentById(Integer.parseInt(scanner.nextLine().trim()));
                        if (s != null) {
                            System.out.println("Current: " + s);
                            System.out.print("New Name (or press Enter): ");
                            String newName = scanner.nextLine().trim();
                            if (!newName.isEmpty()) s.setStudentName(newName);
                            System.out.print("New Email (or press Enter): ");
                            String newEmail = scanner.nextLine().trim();
                            if (!newEmail.isEmpty()) s.setEmail(newEmail);
                            System.out.print("New Phone (or press Enter): ");
                            String newPhone = scanner.nextLine().trim();
                            if (!newPhone.isEmpty()) s.setPhone(newPhone);
                            studentService.updateStudent(s);
                        } else {
                            System.out.println("✗ Student not found!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("✗ Invalid input!");
                    }
                    break;

                case "5":
                    System.out.println("\n========== DELETE STUDENT ==========");
                    System.out.print("Enter Student ID: ");
                    try {
                        Student s = studentService.getStudentById(Integer.parseInt(scanner.nextLine().trim()));
                        if (s != null) {
                            System.out.println("Confirm deletion: " + s);
                            System.out.print("Are you sure? (yes/no): ");
                            if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                                studentService.deleteStudent(s.getStudentId());
                            } else {
                                System.out.println("✗ Cancelled!");
                            }
                        } else {
                            System.out.println("✗ Student not found!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("✗ Invalid input!");
                    }
                    break;

                case "6":
                    System.out.println("\n========== ALL BRANCHES ==========");
                    List<Branch> allBranches = branchService.getAllBranches();
                    if (allBranches.isEmpty()) {
                        System.out.println("✗ No branches found!");
                    } else {
                        System.out.println("\nTotal: " + allBranches.size() + "\n");
                        for (Branch b : allBranches) System.out.println(b);
                    }
                    break;

                case "7":
                    System.out.println("\n========== ADD NEW BRANCH ==========");
                    System.out.print("Enter Branch Name: ");
                    String bname = scanner.nextLine().trim();
                    System.out.print("Enter Branch Code: ");
                    String bcode = scanner.nextLine().trim();
                    branchService.addBranch(new Branch(bname, bcode));
                    break;

                case "8":
                    System.out.println("\n========== STUDENTS BY BRANCH ==========");
                    List<Branch> blist = branchService.getAllBranches();
                    if (blist.isEmpty()) {
                        System.out.println("✗ No branches available!");
                        break;
                    }
                    System.out.println("Available Branches:");
                    for (Branch b : blist) System.out.println(b);
                    System.out.print("\nEnter Branch ID: ");
                    try {
                        int bid = Integer.parseInt(scanner.nextLine().trim());
                        Branch b = branchService.getBranchById(bid);
                        if (b != null) {
                            List<Student> bstudents = studentService.getStudentsByBranch(bid);
                            System.out.println("\n--- Students in " + b.getBranchName() + " ---");
                            System.out.println("Total: " + bstudents.size());
                            for (Student s : bstudents) System.out.println(s);
                        } else {
                            System.out.println("✗ Branch not found!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("✗ Invalid input!");
                    }
                    break;

                case "9":
                    System.out.println("\n========== SYSTEM STATISTICS ==========");
                    System.out.println("Total Students: " + studentService.getTotalStudentCount());
                    List<Branch> statBranches = branchService.getAllBranches();
                    System.out.println("Total Branches: " + statBranches.size());
                    System.out.println("\nStudents per Branch:");
                    for (Branch b : statBranches) {
                        int count = studentService.getStudentsByBranch(b.getBranchId()).size();
                        System.out.println(b.getBranchName() + ": " + count);
                    }
                    break;

                case "0":
                    running = false;
                    System.out.println("\n✓ Thank you for using Student Management System!");
                    break;

                default:
                    System.out.println("✗ Invalid choice! Please try again.");
            }
        }

        DatabaseConnection.closeConnection();
        scanner.close();
    }
}
