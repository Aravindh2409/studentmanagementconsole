<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Students - Student Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header class="header">
            <h1>📚 Student Management System</h1>
            <p>Manage Students</p>
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
            <% if (request.getParameter("error") != null) { %>
                <div class="alert alert-error">
                    <%= request.getParameter("error") %>
                </div>
            <% } %>

            <div class="actions">
                <h2>All Students</h2>
                <a href="${pageContext.request.contextPath}/student?action=add" class="btn btn-success">+ Add New Student</a>
            </div>

            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Type</th>
                        <th>Enrollment Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td><c:out value="${student.studentId}" /></td>
                            <td><c:out value="${student.studentName}" /></td>
                            <td><c:out value="${student.email}" /></td>
                            <td><c:out value="${student.phone}" /></td>
                            <td>
                                <span class="badge <c:if test='${student.studentType eq "full"}'>badge-blue</c:if><c:if test='${student.studentType eq "part"}'>badge-orange</c:if>">
                                    <c:out value="${student.studentType}" />
                                </span>
                            </td>
                            <td><c:out value="${student.enrollmentDate}" /></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/student?action=view&id=${student.studentId}" class="btn btn-sm btn-primary">View</a>
                                <a href="${pageContext.request.contextPath}/student?action=edit&id=${student.studentId}" class="btn btn-sm btn-warning">Edit</a>
                                <a href="${pageContext.request.contextPath}/student?action=delete&id=${student.studentId}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <c:if test="${empty students}">
                <p class="no-data">No students found. <a href="${pageContext.request.contextPath}/student?action=add">Add one now!</a></p>
            </c:if>
        </main>

        <footer class="footer">
            <p>&copy; 2026 Student Management System. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
