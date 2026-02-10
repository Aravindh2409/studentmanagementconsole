import database.DatabaseConnection;
import database.DatabaseInitializer;
import models.Branch;
import models.Student;
import services.BranchService;
import services.StudentService;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Comparator;
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
            System.out.println("1. Add Parttime Student");
            System.out.println("2. Add Full Time Student");
            System.out.println("3. Remove Student");
            System.out.println("4. View Student");
            System.out.println("5. View Students");
            System.out.println("6. Sort Date of Joining");
            System.out.println("7. Sort by ID");
            System.out.println("8. Sort by First Name");
            System.out.println("0. Exit");
            System.out.println("─────────────────────────────────────────");
            System.out.print("Enter your choice (0-8): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                case "2":
                    boolean isPart = choice.equals("1");
                    System.out.println(isPart ? "\n=== ADD PARTTIME STUDENT ===" : "\n=== ADD FULL TIME STUDENT ===");
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
                            String type = isPart ? "part" : "full";
                            studentService.enrollStudent(new Student(name, email, phone, bid, type));
                        } else {
                            System.out.println("✗ Invalid Branch ID!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("✗ Invalid input!");
                    }
                    break;

                case "3":
                    System.out.println("\n=== REMOVE STUDENT ===");
                    System.out.print("Enter Student ID: ");
                    try {
                        int sid = Integer.parseInt(scanner.nextLine().trim());
                        Student s = studentService.getStudentById(sid);
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

                case "4":
                    System.out.println("\n=== VIEW STUDENT ===");
                    System.out.print("Enter Student ID: ");
                    try {
                        int sid = Integer.parseInt(scanner.nextLine().trim());
                        Student s = studentService.getStudentById(sid);
                        if (s != null) System.out.println(s); else System.out.println("✗ Student not found!");
                    } catch (NumberFormatException e) {
                        System.out.println("✗ Invalid input!");
                    }
                    break;

                case "5":
                    System.out.println("\n=== VIEW STUDENTS ===");
                    Set<Student> students = studentService.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("✗ No students found!");
                    } else {
                        System.out.println("\nTotal: " + students.size() + "\n");
                        for (Student st : students) System.out.println(st);
                    }
                    break;

                case "6":
                    System.out.println("\n=== SORT BY DATE OF JOINING ===");
                    List<Student> byDate = new ArrayList<>(studentService.getAllStudents());
                    byDate.sort(Comparator.comparing(Student::getEnrollmentDate));
                    for (Student st : byDate) System.out.println(st);
                    break;

                case "7":
                    System.out.println("\n=== SORT BY ID ===");
                    List<Student> byId = new ArrayList<>(studentService.getAllStudents());
                    byId.sort(Comparator.comparingInt(Student::getStudentId));
                    for (Student st : byId) System.out.println(st);
                    break;

                case "8":
                    System.out.println("\n=== SORT BY FIRST NAME ===");
                    List<Student> byFirst = new ArrayList<>(studentService.getAllStudents());
                    byFirst.sort(Comparator.comparing(s -> s.getStudentName().split(" ")[0]));
                    for (Student st : byFirst) System.out.println(st);
                    break;

                case "0":
                    running = false;
                    System.out.println("\n✓ Goodbye!");
                    break;

                default:
                    System.out.println("✗ Invalid choice! Please try again.");
            }
        }

        DatabaseConnection.closeConnection();
        scanner.close();
    }
}
