<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Students by Branch - Student Management System</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
    <style>
        .branch-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            padding: 2.5rem 1.5rem;
            border-radius: 12px;
            color: white;
            margin-bottom: 2rem;
            box-shadow: 0 8px 25px rgba(102, 126, 234, 0.3);
        }
        
        .branch-header h1 {
            margin: 0 0 0.5rem 0;
            font-size: 2.5rem;
            font-weight: 700;
        }
        
        .branch-code-badge {
            display: inline-block;
            background: rgba(255, 255, 255, 0.2);
            padding: 0.5rem 1rem;
            border-radius: 20px;
            font-size: 0.95rem;
            margin-top: 0.5rem;
            border: 1px solid rgba(255, 255, 255, 0.4);
            backdrop-filter: blur(10px);
        }
        
        .back-link {
            display: inline-flex;
            align-items: center;
            gap: 0.5rem;
            margin-bottom: 1.5rem;
            padding: 0.75rem 1.5rem;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            text-decoration: none;
            border-radius: 8px;
            transition: all 0.3s ease;
            font-weight: 500;
        }
        
        .back-link:hover {
            transform: translateX(-5px);
            box-shadow: 0 6px 20px rgba(102, 126, 234, 0.4);
        }
        
        .empty-state {
            text-align: center;
            padding: 3rem 2rem;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            border-radius: 12px;
            margin: 2rem 0;
        }
        
        .empty-state-icon {
            font-size: 4rem;
            margin-bottom: 1rem;
        }
        
        .empty-state h3 {
            color: #555;
            margin-bottom: 0.5rem;
        }
        
        .empty-state p {
            color: #888;
            margin: 0;
        }
        
        .students-info {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 1.5rem;
            padding: 1rem;
            background: #f9f9f9;
            border-radius: 8px;
            border-left: 4px solid #667eea;
        }
        
        .students-count {
            font-size: 1.2rem;
            color: #555;
        }
        
        .students-count strong {
            color: #667eea;
            font-size: 1.5rem;
        }
        
        .table-container {
            background: white;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }
        
        .table-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            padding: 1.5rem;
            color: white;
        }
        
        .table-header h2 {
            margin: 0;
            font-size: 1.3rem;
        }
        
        .data-table {
            width: 100%;
            border-collapse: collapse;
        }
        
        .data-table thead {
            background: #f5f5f5;
        }
        
        .data-table th {
            padding: 1rem;
            text-align: left;
            font-weight: 600;
            color: #555;
            border-bottom: 2px solid #e0e0e0;
        }
        
        .data-table td {
            padding: 1rem;
            border-bottom: 1px solid #e0e0e0;
        }
        
        .data-table tbody tr:hover {
            background: #f9f9f9;
        }
        
        .badge {
            display: inline-block;
            padding: 0.4rem 0.8rem;
            border-radius: 20px;
            font-size: 0.85rem;
            font-weight: 500;
        }
        
        .badge-primary {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        
        .badge-secondary {
            background: #e3f2fd;
            color: #1976d2;
        }
        
        .btn-small {
            padding: 0.5rem 1rem;
            margin: 0 0.25rem;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 0.85rem;
            text-decoration: none;
            display: inline-block;
            transition: all 0.3s ease;
            font-weight: 500;
        }
        
        .btn-primary {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        
        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(102, 126, 234, 0.4);
        }
        
        .btn-warning {
            background: #ffc107;
            color: #333;
        }
        
        .btn-warning:hover {
            background: #ffb300;
            transform: translateY(-2px);
        }
        
        .btn-danger {
            background: #f44336;
            color: white;
        }
        
        .btn-danger:hover {
            background: #da190b;
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="<c:url value='/branch?action=list'/>" class="back-link">
            ← Back to All Branches
        </a>
        
        <c:if test="${not empty branch}">
            <div class="branch-header">
                <h1>📚 ${branch.branchName}</h1>
                <span class="branch-code-badge">Branch Code: <strong>${branch.branchCode}</strong></span>
            </div>
        </c:if>

        <c:choose>
            <c:when test="${empty students}">
                <div class="empty-state">
                    <div class="empty-state-icon">📭</div>
                    <h3>No Students Enrolled</h3>
                    <p>There are currently no students enrolled in this branch.
                    <br><a href="<c:url value='/student?action=add'/>" style="color: #667eea; text-decoration: none; font-weight: bold;">Enroll a student</a></p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="students-info">
                    <div class="students-count">
                        Total Students: <strong><c:out value="${fn:length(students)}"/></strong>
                    </div>
                    <a href="<c:url value='/student?action=add'/>" class="btn-small btn-primary">+ Enroll New Student</a>
                </div>
                
                <div class="table-container">
                    <div class="table-header">
                        <h2>👥 Enrolled Students</h2>
                    </div>
                    <table class="data-table">
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
                                    <td><strong>#${student.studentId}</strong></td>
                                    <td>${student.studentName}</td>
                                    <td>${student.email}</td>
                                    <td>${student.phone}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${student.studentType eq 'full' or student.studentType eq 'Full-Time'}">
                                                <span class="badge badge-primary">Full-Time</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge badge-secondary">Part-Time</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${student.enrollmentDate}</td>
                                    <td>
                                        <a href="<c:url value='/student?action=view&id=${student.studentId}'/>" class="btn-small btn-primary">View</a>
                                        <a href="<c:url value='/student?action=edit&id=${student.studentId}'/>" class="btn-small btn-warning">Edit</a>
                                        <a href="<c:url value='/student?action=delete&id=${student.studentId}'/>" class="btn-small btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>

