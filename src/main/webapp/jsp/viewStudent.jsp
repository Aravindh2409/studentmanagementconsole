<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Student - Student Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header class="header">
            <h1>📚 Student Management System</h1>
            <p>Student Details</p>
        </header>

        <nav class="navbar">
            <ul>
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/student?action=list" class="active">Students</a></li>
                <li><a href="${pageContext.request.contextPath}/branch?action=list">Branches</a></li>
            </ul>
        </nav>

        <main class="content">
            <% if (request.getParameter("msg") != null) { %>
                <div class="alert alert-success">
                    <%= request.getParameter("msg") %>
                </div>
            <% } %>

            <c:if test="${not empty student}">
                <div class="student-detail">
                    <h2><c:out value="${student.studentName}" /></h2>
                    
                    <div class="detail-section">
                        <div class="detail-item">
                            <label>Student ID:</label>
                            <span><c:out value="${student.studentId}" /></span>
                        </div>
                        <div class="detail-item">
                            <label>Email:</label>
                            <span><c:out value="${student.email}" /></span>
                        </div>
                        <div class="detail-item">
                            <label>Phone:</label>
                            <span><c:out value="${student.phone}" /></span>
                        </div>
                        <div class="detail-item">
                            <label>Branch:</label>
                            <span><c:out value="${branch.branchName} (${branch.branchCode})" /></span>
                        </div>
                        <div class="detail-item">
                            <label>Student Type:</label>
                            <span class="badge <c:if test='${student.studentType eq "full"}'>badge-blue</c:if><c:if test='${student.studentType eq "part"}'>badge-orange</c:if>">
                                <c:out value="${student.studentType}" />
                            </span>
                        </div>
                        <div class="detail-item">
                            <label>Enrollment Date:</label>
                            <span><c:out value="${student.enrollmentDate}" /></span>
                        </div>
                    </div>

                    <div class="form-actions">
                        <a href="${pageContext.request.contextPath}/student?action=edit&id=${student.studentId}" class="btn btn-warning">Edit</a>
                        <a href="${pageContext.request.contextPath}/student?action=delete&id=${student.studentId}" class="btn btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                        <a href="${pageContext.request.contextPath}/student?action=list" class="btn btn-secondary">Back to List</a>
                    </div>
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
