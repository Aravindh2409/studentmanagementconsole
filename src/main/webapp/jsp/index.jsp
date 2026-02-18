<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header class="header">
            <h1>📚 Student Management System</h1>
            <p>Welcome to the Student Portal</p>
        </header>

        <nav class="navbar">
            <ul>
                <li><a href="${pageContext.request.contextPath}/" class="active">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/student?action=list">Students</a></li>
                <li><a href="${pageContext.request.contextPath}/branch?action=list">Branches</a></li>
            </ul>
        </nav>

        <main class="content">
            <section class="dashboard">
                <h2>📊 Welcome to Your Dashboard</h2>
                
                <div class="cards">
                    <!-- Student Management Cards -->
                    <div class="card">
                        <div class="card-icon">👥</div>
                        <h3>View All Students</h3>
                        <p>Browse the complete list of enrolled students in the system</p>
                        <a href="${pageContext.request.contextPath}/student?action=list" class="btn btn-primary">View List</a>
                    </div>

                    <div class="card">
                        <div class="card-icon">➕</div>
                        <h3>Enroll New Student</h3>
                        <p>Add a new student to the database with all details</p>
                        <a href="${pageContext.request.contextPath}/student?action=add" class="btn btn-success">Add Student</a>
                    </div>

                    <div class="card">
                        <div class="card-icon">🔍</div>
                        <h3>Student Details</h3>
                        <p>Search and view detailed information about any student</p>
                        <a href="${pageContext.request.contextPath}/student?action=list" class="btn btn-primary">Search</a>
                    </div>

                    <!-- Branch Management Cards -->
                    <div class="card">
                        <div class="card-icon">🏛️</div>
                        <h3>View All Branches</h3>
                        <p>See all academic branches and departments available</p>
                        <a href="${pageContext.request.contextPath}/branch?action=list" class="btn btn-primary">View Branches</a>
                    </div>

                    <div class="card">
                        <div class="card-icon">🆕</div>
                        <h3>Create New Branch</h3>
                        <p>Add a new branch or department to the system</p>
                        <a href="${pageContext.request.contextPath}/branch?action=add" class="btn btn-success">Add Branch</a>
                    </div>

                    <div class="card">
                        <div class="card-icon">📚</div>
                        <h3>Students by Branch</h3>
                        <p>Filter and view students enrolled in each branch</p>
                        <a href="${pageContext.request.contextPath}/branch?action=list" class="btn btn-primary">Select Branch</a>
                    </div>

                    <!-- Quick Actions -->
                    <div class="card">
                        <div class="card-icon">✏️</div>
                        <h3>Edit Student</h3>
                        <p>Update student information and enrollment details</p>
                        <a href="${pageContext.request.contextPath}/student?action=list" class="btn btn-warning">Select & Edit</a>
                    </div>

                    <div class="card">
                        <div class="card-icon">🗑️</div>
                        <h3>Delete Records</h3>
                        <p>Remove students or branches from the system</p>
                        <a href="${pageContext.request.contextPath}/student?action=list" class="btn btn-danger">Manage</a>
                    </div>

                    <div class="card">
                        <div class="card-icon">📊</div>
                        <h3>System Overview</h3>
                        <p>View statistics and system information</p>
                        <a href="${pageContext.request.contextPath}/overview" class="btn btn-primary">View Analytics</a>
                    </div>
                </div>
            </section>
        </main>

        <footer class="footer">
            <p>&copy; 2026 Student Management System. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
