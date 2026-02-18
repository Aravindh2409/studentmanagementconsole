<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>System Overview - Student Management System</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
    <style>
        .overview-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            padding: 2rem 1.5rem;
            border-radius: 12px;
            color: white;
            margin-bottom: 2rem;
            box-shadow: 0 4px 15px rgba(102, 126, 234, 0.3);
            text-align: center;
        }
        
        .overview-header h1 {
            margin: 0;
            font-size: 2.5rem;
        }
        
        .back-link {
            display: inline-block;
            margin-bottom: 1.5rem;
            padding: 0.75rem 1.5rem;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            text-decoration: none;
            border-radius: 8px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            font-weight: 500;
        }
        
        .back-link:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(102, 126, 234, 0.4);
        }
        
        .analytics-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 2rem;
            margin-bottom: 2rem;
        }
        
        .stat-card {
            background: white;
            padding: 2rem;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            border-left: 5px solid #667eea;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        
        .stat-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
        }
        
        .stat-card h3 {
            margin: 0 0 1rem 0;
            color: #555;
            font-size: 0.95rem;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }
        
        .stat-value {
            font-size: 3rem;
            font-weight: bold;
            color: #667eea;
            margin: 0;
        }
        
        .stat-unit {
            font-size: 0.9rem;
            color: #888;
            margin-top: 0.5rem;
        }
        
        .stat-card.total-students {
            border-left-color: #667eea;
        }
        
        .stat-card.total-branches {
            border-left-color: #764ba2;
        }
        
        .stat-card.students-per-branch {
            border-left-color: #f093fb;
        }
        
        .stat-card.fulltime-students {
            border-left-color: #4facfe;
        }
        
        .stat-card.parttime-students {
            border-left-color: #43e97b;
        }
        
        .analytics-table {
            background: white;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            margin-top: 2rem;
        }
        
        .analytics-table h2 {
            padding: 1.5rem;
            margin: 0;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        
        .table-wrapper {
            overflow-x: auto;
        }
        
        .analytics-table table {
            width: 100%;
            border-collapse: collapse;
        }
        
        .analytics-table thead {
            background: #f5f5f5;
        }
        
        .analytics-table th {
            padding: 1rem;
            text-align: left;
            font-weight: 600;
            color: #555;
            border-bottom: 2px solid #e0e0e0;
        }
        
        .analytics-table td {
            padding: 1rem;
            border-bottom: 1px solid #e0e0e0;
        }
        
        .analytics-table tbody tr:hover {
            background: #f9f9f9;
        }
        
        .branch-badge {
            display: inline-block;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 0.4rem 0.8rem;
            border-radius: 20px;
            font-size: 0.85rem;
            font-weight: 500;
        }
        
        .no-data {
            text-align: center;
            padding: 2rem;
            color: #888;
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="<c:url value='/'/>" class="back-link">← Back to Dashboard</a>
        
        <div class="overview-header">
            <h1>📊 System Overview</h1>
        </div>

        <div class="analytics-grid">
            <div class="stat-card total-students">
                <h3>Total Students</h3>
                <p class="stat-value">${totalStudents}</p>
                <p class="stat-unit">Active enrollments</p>
            </div>

            <div class="stat-card total-branches">
                <h3>Total Branches</h3>
                <p class="stat-value">${totalBranches}</p>
                <p class="stat-unit">Programs offered</p>
            </div>

            <c:if test="${totalBranches > 0 && totalStudents > 0}">
                <div class="stat-card students-per-branch">
                    <h3>Avg Students/Branch</h3>
                    <p class="stat-value">
                        <c:out value="${Math.round(totalStudents / totalBranches)}"/>
                    </p>
                    <p class="stat-unit">Per branch</p>
                </div>
            </c:if>

            <c:set var="fulltimeCount" value="0" scope="page"/>
            <c:set var="parttimeCount" value="0" scope="page"/>
            
            <c:forEach var="student" items="${students}" varStatus="loop">
                <c:choose>
                    <c:when test="${student.studentType eq 'full' or student.studentType eq 'Full-Time'}">
                        <c:set var="fulltimeCount" value="${fulltimeCount + 1}" scope="page"/>
                    </c:when>
                    <c:when test="${student.studentType eq 'part' or student.studentType eq 'Part-Time'}">
                        <c:set var="parttimeCount" value="${parttimeCount + 1}" scope="page"/>
                    </c:when>
                </c:choose>
            </c:forEach>

            <div class="stat-card fulltime-students">
                <h3>Full-Time Students</h3>
                <p class="stat-value"><c:out value="${fulltimeCount}"/></p>
                <p class="stat-unit">Regular enrollment</p>
            </div>

            <div class="stat-card parttime-students">
                <h3>Part-Time Students</h3>
                <p class="stat-value"><c:out value="${parttimeCount}"/></p>
                <p class="stat-unit">Flexible schedule</p>
            </div>
        </div>

        <c:if test="${not empty branches}">
            <div class="analytics-table">
                <h2>📚 Students by Branch</h2>
                <div class="table-wrapper">
                    <table>
                        <thead>
                            <tr>
                                <th>Branch Name</th>
                                <th>Branch Code</th>
                                <th>Students Enrolled</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="branch" items="${branches}">
                                <c:set var="studentCountForBranch" value="0"/>
                                <c:forEach var="student" items="${students}">
                                    <c:if test="${student.branchId eq branch.branchId}">
                                        <c:set var="studentCountForBranch" value="${studentCountForBranch + 1}"/>
                                    </c:if>
                                </c:forEach>
                                <tr>
                                    <td>
                                        <span class="branch-badge">${branch.branchName}</span>
                                    </td>
                                    <td>${branch.branchCode}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${studentCountForBranch == 0}">
                                                <span style="color: #888;">No students enrolled</span>
                                            </c:when>
                                            <c:otherwise>
                                                <strong>${studentCountForBranch}</strong> student<c:if test="${studentCountForBranch != 1}">s</c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>

        <c:if test="${empty branches}">
            <div class="analytics-table">
                <h2>📚 Students by Branch</h2>
                <div class="no-data">
                    No branch data available yet.
                </div>
            </div>
        </c:if>
    </div>
</body>
</html>
