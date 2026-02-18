package servlets;

import models.Branch;
import services.BranchService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/branch")
public class BranchServlet extends HttpServlet {
    private BranchService branchService;

    @Override
    public void init() throws ServletException {
        branchService = new BranchService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listBranches(req, resp);
                break;
            case "add":
                showAddForm(req, resp);
                break;
            default:
                listBranches(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("save".equals(action)) {
            saveBranch(req, resp);
        }
    }

    private void listBranches(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Branch> branches = branchService.getAllBranches();
        req.setAttribute("branches", branches);
        req.getRequestDispatcher("/jsp/branches.jsp").forward(req, resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/addBranch.jsp").forward(req, resp);
    }

    private void saveBranch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String branchName = req.getParameter("branchName");
        String branchCode = req.getParameter("branchCode");

        Branch branch = new Branch(branchName, branchCode);
        branchService.addBranch(branch);
        resp.sendRedirect("branch?action=list&msg=Branch added successfully!");
    }
}
