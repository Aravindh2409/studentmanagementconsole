<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>500 - Server Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header class="header">
            <h1>500 - Server Error</h1>
            <p>Something went wrong on our end</p>
        </header>

        <main class="content" style="text-align: center; padding: 60px 20px;">
            <h2>Server Error</h2>
            <p>An unexpected error occurred. Please try again later.</p>
            <% if (exception != null) { %>
                <details style="margin-top: 30px; text-align: left; background: #fee2e2; padding: 15px; border-radius: 5px;">
                    <summary>Error Details</summary>
                    <pre style="margin-top: 10px; color: #991b1b;"><%= exception.toString() %></pre>
                </details>
            <% } %>
            <a href="${pageContext.request.contextPath}/" class="btn btn-primary" style="margin-top: 20px;">Go to Home</a>
        </main>

        <footer class="footer">
            <p>&copy; 2026 Student Management System. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
