<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Form - JSP</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        form { max-width: 400px; }
        input { width: 100%; padding: 8px; margin: 5px 0; }
        button { background: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>
    <h1>${empty user ? 'Add' : 'Edit'} User</h1>
    <form action="/users" method="post">
        <label>Name:</label>
        <input type="text" name="name" value="${user.name}" required><br>
        <label>Email:</label>
        <input type="email" name="email" value="${user.email}" required><br>
        <label>Age:</label>
        <input type="number" name="age" value="${user.age}" required><br>
        <button type="submit">Save</button>
        <a href="/users">Cancel</a>
    </form>
</body>
</html>
