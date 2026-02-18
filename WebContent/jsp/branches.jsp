<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Branches - Student Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header class="header">
            <h1>📚 Student Management System</h1>
            <p>Manage Branches</p>
        </header>

        <nav class="navbar">
            <ul>
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/student?action=list">Students</a></li>
                <li><a href="${pageContext.request.contextPath}/branch?action=list" class="active">Branches</a></li>
            </ul>
        </nav>

        <main class="content">
            <% if (request.getParameter("msg") != null) { %>
                <div class="alert alert-success">
                    <%= request.getParameter("msg") %>
                </div>
            <% } %>

            <div class="actions">
                <h2>All Branches</h2>
                <a href="${pageContext.request.contextPath}/branch?action=add" class="btn btn-success">+ Add New Branch</a>
            </div>

            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Branch Name</th>
                        <th>Code</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="branch" items="${branches}">
                        <tr>
                            <td><c:out value="${branch.branchId}" /></td>
                            <td><c:out value="${branch.branchName}" /></td>
                            <td><span class="badge badge-blue"><c:out value="${branch.branchCode}" /></span></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <c:if test="${empty branches}">
                <p class="no-data">No branches found.</p>
            </c:if>
        </main>

        <footer class="footer">
            <p>&copy; 2026 Student Management System. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
