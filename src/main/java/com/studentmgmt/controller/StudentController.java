package com.studentmgmt.controller;

import com.studentmgmt.database.DatabaseConnection;
import com.studentmgmt.models.Student;
import com.studentmgmt.models.Branch;
import com.studentmgmt.services.StudentService;
import com.studentmgmt.services.BranchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/student")
public class StudentController {
    
    private StudentService studentService = new StudentService();
    private BranchService branchService = new BranchService();

    @GetMapping
    public String handleStudentRequest(
            @RequestParam(value = "action", defaultValue = "list") String action,
            @RequestParam(value = "id", required = false) String id,
            Model model) {
        
        switch (action) {
            case "list":
                return listStudents(model);
            case "add":
                return showAddForm(model);
            case "view":
                return viewStudent(id, model);
            case "edit":
                return showEditForm(id, model);
            case "delete":
                return deleteStudent(id, model);
            default:
                return listStudents(model);
        }
    }

    private String listStudents(Model model) {
        Set<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    private String showAddForm(Model model) {
        List<Branch> branches = branchService.getAllBranches();
        model.addAttribute("branches", branches);
        return "addStudent";
    }

    private String viewStudent(String id, Model model) {
        if (id != null) {
            Student student = studentService.getStudentById(Integer.parseInt(id));
            if (student != null) {
                model.addAttribute("student", student);
                Branch branch = branchService.getBranchById(student.getBranchId());
                model.addAttribute("branch", branch);
            }
        }
        return "viewStudent";
    }

    private String showEditForm(String id, Model model) {
        if (id != null) {
            Student student = studentService.getStudentById(Integer.parseInt(id));
            List<Branch> branches = branchService.getAllBranches();
            model.addAttribute("student", student);
            model.addAttribute("branches", branches);
        }
        return "editStudent";
    }

    private String deleteStudent(String id, Model model) {
        if (id != null) {
            studentService.deleteStudent(Integer.parseInt(id));
            return "redirect:/student?action=list";
        }
        return "redirect:/student?action=list";
    }

    @PostMapping
    public String handleStudentForm(
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "branchId", required = false) String branchId,
            @RequestParam(value = "studentType", required = false) String studentType,
            RedirectAttributes redirectAttributes) {
        
        if ("save".equals(action)) {
            Student student = new Student(name, email, phone, Integer.parseInt(branchId), studentType);
            
            if (studentService.enrollStudent(student)) {
                redirectAttributes.addAttribute("msg", "Student added successfully!");
            }
            return "redirect:/student?action=list";
        } else if ("update".equals(action) && id != null) {
            Student student = new Student(Integer.parseInt(id), name, email, phone, Integer.parseInt(branchId), java.time.LocalDateTime.now(), studentType);
            
            if (studentService.updateStudent(student)) {
                redirectAttributes.addAttribute("msg", "Student updated successfully!");
            }
            return "redirect:/student?action=list";
        }
        
        return "redirect:/student?action=list";
    }
}
