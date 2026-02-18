<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - Page Not Found</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header class="header">
            <h1>404 - Page Not Found</h1>
            <p>The page you are looking for doesn't exist</p>
        </header>

        <main class="content" style="text-align: center; padding: 60px 20px;">
            <h2>Oops! Page Not Found</h2>
            <p>We couldn't find the page you're looking for.</p>
            <a href="${pageContext.request.contextPath}/" class="btn btn-primary" style="margin-top: 20px;">Go to Home</a>
        </main>

        <footer class="footer">
            <p>&copy; 2026 Student Management System. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
