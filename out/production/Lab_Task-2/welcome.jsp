<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
</head>
<body>
    <h2>Welcome Page</h2>
    <%
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
            String email = (String) session.getAttribute("email");
    %>
            <p>Welcome, <%= email %>!</p>
    <%
        } else {
    %>
            <p>Please login first.</p>
    <%
        }
    %>
</body>
</html>
