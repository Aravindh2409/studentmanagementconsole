package com.studentmgmt.controller;

import com.studentmgmt.models.Branch;
import com.studentmgmt.models.Student;
import com.studentmgmt.services.BranchService;
import com.studentmgmt.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/branch")
public class BranchController {
    
    private BranchService branchService = new BranchService();
    private StudentService studentService = new StudentService();

    @GetMapping
    public String handleBranchRequest(
            @RequestParam(value = "action", defaultValue = "list") String action,
            @RequestParam(value = "id", required = false) String id,
            Model model) {
        
        switch (action) {
            case "list":
                return listBranches(model);
            case "add":
                return showAddForm(model);
            case "delete":
                return deleteBranch(id, model);
            case "students":
                return studentsByBranch(id, model);
            default:
                return listBranches(model);
        }
    }

    private String listBranches(Model model) {
        List<Branch> branches = branchService.getAllBranches();
        model.addAttribute("branches", branches);
        return "branches";
    }

    private String showAddForm(Model model) {
        return "addBranch";
    }

    private String deleteBranch(String id, Model model) {
        if (id != null) {
            branchService.deleteBranch(Integer.parseInt(id));
            return "redirect:/branch?action=list";
        }
        return "redirect:/branch?action=list";
    }

    private String studentsByBranch(String id, Model model) {
        if (id != null) {
            Branch branch = branchService.getBranchById(Integer.parseInt(id));
            Set<Student> students = studentService.getStudentsByBranch(Integer.parseInt(id));
            model.addAttribute("branch", branch);
            model.addAttribute("students", students);
            return "branchStudents";
        }
        return "redirect:/branch?action=list";
    }

    @PostMapping
    public String handleBranchForm(
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "branchName", required = false) String branchName,
            @RequestParam(value = "branchCode", required = false) String branchCode,
            RedirectAttributes redirectAttributes) {
        
        if ("save".equals(action)) {
            Branch branch = new Branch(branchName, branchCode);
            
            if (branchService.addBranch(branch)) {
                redirectAttributes.addAttribute("msg", "Branch added successfully!");
            }
            return "redirect:/branch?action=list";
        }
        
        return "redirect:/branch?action=list";
    }
}
