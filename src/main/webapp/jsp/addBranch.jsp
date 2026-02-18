<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Branch - Student Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header class="header">
            <h1>📚 Student Management System</h1>
            <p>Add New Branch</p>
        </header>

        <nav class="navbar">
            <ul>
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/student?action=list">Students</a></li>
                <li><a href="${pageContext.request.contextPath}/branch?action=list" class="active">Branches</a></li>
            </ul>
        </nav>

        <main class="content">
            <div class="form-container">
                <h2>Add New Branch</h2>
                
                <form method="POST" action="${pageContext.request.contextPath}/branch" class="form">
                    <input type="hidden" name="action" value="save">

                    <div class="form-group">
                        <label for="branchName">Branch Name *</label>
                        <input type="text" id="branchName" name="branchName" required placeholder="Enter branch name">
                    </div>

                    <div class="form-group">
                        <label for="branchCode">Branch Code *</label>
                        <input type="text" id="branchCode" name="branchCode" required placeholder="Enter branch code (e.g., CS, ECE)">
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-success">Add Branch</button>
                        <a href="${pageContext.request.contextPath}/branch?action=list" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </div>
        </main>

        <footer class="footer">
            <p>&copy; 2026 Student Management System. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
