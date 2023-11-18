<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
    <h2>Registration Page</h2>
    <form action="RegisterServlet" method="post">
        Email: <input type="email" name="email" required><br>
        Full Name: <input type="text" name="fullName" required><br>
        Password: <input type="password" name="password" required><br>
        <input type="submit" value="Register">
    </form>
</body>
</html>
