package servlets;

import models.Student;
import services.StudentService;
import services.BranchService;
import models.Branch;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private StudentService studentService;
    private BranchService branchService;

    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
        branchService = new BranchService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listStudents(req, resp);
                break;
            case "add":
                showAddForm(req, resp);
                break;
            case "view":
                viewStudent(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                deleteStudent(req, resp);
                break;
            default:
                listStudents(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("save".equals(action)) {
            saveStudent(req, resp);
        } else if ("update".equals(action)) {
            updateStudent(req, resp);
        }
    }

    private void listStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Student> students = studentService.getAllStudents();
        req.setAttribute("students", students);
        List<Branch> branches = branchService.getAllBranches();
        req.setAttribute("branches", branches);
        req.getRequestDispatcher("/jsp/students.jsp").forward(req, resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Branch> branches = branchService.getAllBranches();
        req.setAttribute("branches", branches);
        req.getRequestDispatcher("/jsp/addStudent.jsp").forward(req, resp);
    }

    private void viewStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int studentId = Integer.parseInt(req.getParameter("id"));
            Student student = studentService.getStudentById(studentId);
            if (student != null) {
                req.setAttribute("student", student);
                Branch branch = branchService.getBranchById(student.getBranchId());
                req.setAttribute("branch", branch);
            }
            req.getRequestDispatcher("/jsp/viewStudent.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendRedirect("student?action=list");
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int studentId = Integer.parseInt(req.getParameter("id"));
            Student student = studentService.getStudentById(studentId);
            if (student != null) {
                req.setAttribute("student", student);
                List<Branch> branches = branchService.getAllBranches();
                req.setAttribute("branches", branches);
                req.getRequestDispatcher("/jsp/editStudent.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("student?action=list");
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect("student?action=list");
        }
    }

    private void saveStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String branchIdStr = req.getParameter("branchId");
        String studentType = req.getParameter("studentType");

        try {
            int branchId = Integer.parseInt(branchIdStr);
            Student student = new Student(name, email, phone, branchId, studentType);
            studentService.enrollStudent(student);
            resp.sendRedirect("student?action=list&msg=Student added successfully!");
        } catch (NumberFormatException e) {
            resp.sendRedirect("student?action=add&error=Invalid branch ID");
        }
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int studentId = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String branchIdStr = req.getParameter("branchId");
            String studentType = req.getParameter("studentType");
            int branchId = Integer.parseInt(branchIdStr);

            Student student = new Student(studentId, name, email, phone, branchId, null, studentType);
            studentService.updateStudent(student);
            resp.sendRedirect("student?action=view&id=" + studentId + "&msg=Student updated successfully!");
        } catch (NumberFormatException e) {
            resp.sendRedirect("student?action=list&error=Invalid data");
        }
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int studentId = Integer.parseInt(req.getParameter("id"));
            studentService.deleteStudent(studentId);
            resp.sendRedirect("student?action=list&msg=Student deleted successfully!");
        } catch (NumberFormatException e) {
            resp.sendRedirect("student?action=list&error=Invalid ID");
        }
    }
}
