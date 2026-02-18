<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Student - Student Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header class="header">
            <h1>📚 Student Management System</h1>
            <p>Edit Student</p>
        </header>

        <nav class="navbar">
            <ul>
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/student?action=list" class="active">Students</a></li>
                <li><a href="${pageContext.request.contextPath}/branch?action=list">Branches</a></li>
            </ul>
        </nav>

        <main class="content">
            <c:if test="${not empty student}">
                <div class="form-container">
                    <h2>Edit Student Information</h2>
                    
                    <form method="POST" action="${pageContext.request.contextPath}/student" class="form">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="${student.studentId}">

                        <div class="form-group">
                            <label for="name">Student Name *</label>
                            <input type="text" id="name" name="name" value="${student.studentName}" required>
                        </div>

                        <div class="form-group">
                            <label for="email">Email *</label>
                            <input type="email" id="email" name="email" value="${student.email}" required>
                        </div>

                        <div class="form-group">
                            <label for="phone">Phone *</label>
                            <input type="tel" id="phone" name="phone" value="${student.phone}" required>
                        </div>

                        <div class="form-group">
                            <label for="branchId">Branch *</label>
                            <select id="branchId" name="branchId" required>
                                <c:forEach var="branch" items="${branches}">
                                    <option value="${branch.branchId}" <c:if test='${branch.branchId eq student.branchId}'>selected</c:if>>
                                        <c:out value="${branch.branchName} (${branch.branchCode})" />
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="studentType">Student Type *</label>
                            <select id="studentType" name="studentType" required>
                                <option value="full" <c:if test='${student.studentType eq "full"}'>selected</c:if>>Full Time</option>
                                <option value="part" <c:if test='${student.studentType eq "part"}'>selected</c:if>>Part Time</option>
                            </select>
                        </div>

                        <div class="form-actions">
                            <button type="submit" class="btn btn-success">Update Student</button>
                            <a href="${pageContext.request.contextPath}/student?action=list" class="btn btn-secondary">Cancel</a>
                        </div>
                    </form>
                </div>
            </c:if>

            <c:if test="${empty student}">
                <p class="no-data">Student not found. <a href="${pageContext.request.contextPath}/student?action=list">Go back to student list</a></p>
            </c:if>
        </main>

        <footer class="footer">
            <p>&copy; 2026 Student Management System. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
