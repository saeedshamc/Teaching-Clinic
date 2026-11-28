<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Users - JSP</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background: #4CAF50; color: white; }
        tr:nth-child(even) { background: #f2f2f2; }
        .btn { background: #2196F3; color: white; padding: 8px 16px; text-decoration: none; border-radius: 4px; }
        .btn:hover { background: #0b7dda; }
    </style>
</head>
<body>
    <h1>User Management</h1>
    <a href="/users/new" class="btn">Add User</a>

    <c:if test="${not empty users}">
        <table>
            <tr>
                <th>ID</th><th>Name</th><th>Email</th><th>Age</th><th>Actions</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.age}</td>
                    <td>
                        <a href="/users/${user.id}">Edit</a> |
                        <a href="/users/${user.id}" onclick="return confirm('Delete?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty users}">
        <p>No users found. <a href="/users/new">Add one</a></p>
    </c:if>
</body>
</html>
