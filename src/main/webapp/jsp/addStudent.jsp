<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Student - Student Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header class="header">
            <h1>📚 Student Management System</h1>
            <p>Add New Student</p>
        </header>

        <nav class="navbar">
            <ul>
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/student?action=list" class="active">Students</a></li>
                <li><a href="${pageContext.request.contextPath}/branch?action=list">Branches</a></li>
            </ul>
        </nav>

        <main class="content">
            <div class="form-container">
                <h2>Enroll New Student</h2>
                
                <form method="POST" action="${pageContext.request.contextPath}/student" class="form">
                    <input type="hidden" name="action" value="save">

                    <div class="form-group">
                        <label for="name">Student Name *</label>
                        <input type="text" id="name" name="name" required placeholder="Enter full name">
                    </div>

                    <div class="form-group">
                        <label for="email">Email *</label>
                        <input type="email" id="email" name="email" required placeholder="Enter email">
                    </div>

                    <div class="form-group">
                        <label for="phone">Phone *</label>
                        <input type="tel" id="phone" name="phone" required placeholder="Enter phone number">
                    </div>

                    <div class="form-group">
                        <label for="branchId">Branch *</label>
                        <select id="branchId" name="branchId" required>
                            <option value="">-- Select a Branch --</option>
                            <c:forEach var="branch" items="${branches}">
                                <option value="${branch.branchId}">
                                    <c:out value="${branch.branchName} (${branch.branchCode})" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="studentType">Student Type *</label>
                        <select id="studentType" name="studentType" required>
                            <option value="full">Full Time</option>
                            <option value="part">Part Time</option>
                        </select>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-success">Enroll Student</button>
                        <a href="${pageContext.request.contextPath}/student?action=list" class="btn btn-secondary">Cancel</a>
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
