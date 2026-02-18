<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Branches - Student Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .page-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            padding: 2.5rem 1.5rem;
            border-radius: 12px;
            color: white;
            margin-bottom: 2rem;
            box-shadow: 0 8px 25px rgba(102, 126, 234, 0.3);
        }
        
        .page-header h1 {
            margin: 0 0 0.5rem 0;
            font-size: 2rem;
            font-weight: 700;
        }
        
        .page-header p {
            margin: 0;
            opacity: 0.95;
            font-size: 1.1rem;
        }
        
        .back-home {
            display: inline-block;
            margin-bottom: 1.5rem;
            padding: 0.75rem 1.5rem;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-weight: 500;
            transition: all 0.3s ease;
        }
        
        .back-home:hover {
            transform: translateX(-5px);
            box-shadow: 0 6px 20px rgba(102, 126, 234, 0.4);
        }
        
        .section-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 1.5rem;
            padding-bottom: 1rem;
            border-bottom: 2px solid #e0e0e0;
        }
        
        .section-header h2 {
            margin: 0;
            color: #333;
            font-size: 1.5rem;
        }
        
        .branch-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
            gap: 1.5rem;
            margin-bottom: 2rem;
        }
        
        .branch-card {
            background: white;
            border-radius: 12px;
            padding: 1.5rem;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            transition: all 0.3s ease;
            border-top: 4px solid #667eea;
            display: flex;
            flex-direction: column;
        }
        
        .branch-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
        }
        
        .branch-name {
            font-size: 1.3rem;
            font-weight: 700;
            color: #333;
            margin: 0 0 0.5rem 0;
        }
        
        .branch-code {
            display: inline-block;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 0.4rem 0.8rem;
            border-radius: 20px;
            font-size: 0.85rem;
            font-weight: 600;
            margin-bottom: 1rem;
            width: fit-content;
        }
        
        .branch-actions {
            display: flex;
            gap: 0.75rem;
            margin-top: auto;
            padding-top: 1rem;
            border-top: 1px solid #e0e0e0;
        }
        
        .btn-small {
            flex: 1;
            padding: 0.65rem 1rem;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 0.9rem;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            justify-content: center;
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
        
        .btn-danger {
            background: #f44336;
            color: white;
        }
        
        .btn-danger:hover {
            background: #da190b;
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(244, 67, 54, 0.4);
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
            margin: 0 0 0.5rem 0;
        }
        
        .empty-state a {
            color: #667eea;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="${pageContext.request.contextPath}/" class="back-home">← Back to Home</a>
        
        <div class="page-header">
            <h1>🏛️ Branch Management</h1>
            <p>View and manage all academic branches</p>
        </div>

        <main class="content">
            <% if (request.getParameter("msg") != null) { %>
                <div class="alert alert-success">
                    <%= request.getParameter("msg") %>
                </div>
            <% } %>

            <div class="section-header">
                <h2>All Branches</h2>
                <a href="${pageContext.request.contextPath}/branch?action=add" class="btn btn-success">+ Add New Branch</a>
            </div>

            <c:choose>
                <c:when test="${empty branches}">
                    <div class="empty-state">
                        <div class="empty-state-icon">📭</div>
                        <h3>No Branches Found</h3>
                        <p>There are no branches in the system yet.<br>
                        <a href="${pageContext.request.contextPath}/branch?action=add">Create the first branch</a></p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="branch-grid">
                        <c:forEach var="branch" items="${branches}">
                            <div class="branch-card">
                                <h3 class="branch-name">${branch.branchName}</h3>
                                <div class="branch-code">${branch.branchCode}</div>
                                <div class="branch-actions">
                                    <a href="${pageContext.request.contextPath}/branch?action=students&id=${branch.branchId}" class="btn-small btn-primary">👥 View Students</a>
                                    <a href="${pageContext.request.contextPath}/branch?action=delete&id=${branch.branchId}" class="btn-small btn-danger" onclick="return confirm('Are you sure you want to delete this branch?')">🗑️ Delete</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </main>

        <footer class="footer">
            <p>&copy; 2026 Student Management System. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
