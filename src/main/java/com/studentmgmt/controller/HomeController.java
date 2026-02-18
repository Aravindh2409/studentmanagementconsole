package com.studentmgmt.controller;

import com.studentmgmt.services.StudentService;
import com.studentmgmt.services.BranchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    
    private StudentService studentService = new StudentService();
    private BranchService branchService = new BranchService();

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/overview")
    public String systemOverview(@RequestParam(value = "action", defaultValue = "dashboard") String action, Model model) {
        if ("dashboard".equals(action)) {
            int totalStudents = studentService.getTotalStudentCount();
            int totalBranches = branchService.getAllBranches().size();
            
            model.addAttribute("totalStudents", totalStudents);
            model.addAttribute("totalBranches", totalBranches);
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("branches", branchService.getAllBranches());
            
            return "overview";
        }
        return "redirect:/";
    }
}
