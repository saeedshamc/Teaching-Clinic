<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP Demo</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        h1 { color: #2c3e50; }
        .info { background: #ecf0f1; padding: 15px; border-radius: 5px; }
    </style>
</head>
<body>
    <h1>Hello JSP!</h1>

    <div class="info">
        <p>Current time: <%= new Date() %></p>
        <p>Server: <%= System.getProperty("os.name") %></p>
        <p>Java version: <%= System.getProperty("java.version") %></p>
    </div>

    <!-- EL expressions -->
    <h2>Expression Language</h2>
    <p>Request URI: ${pageContext.request.requestURI}</p>
    <p>Method: ${pageContext.request.method}</p>
    <p>Remote address: ${pageContext.request.remoteAddr}</p>

    <!-- JSTL -->
    <h2>JSTL Example</h2>
    <c:if test="${not empty param.name}">
        <p>Hello, ${param.name}!</p>
    </c:if>
    <c:if test="${empty param.name}">
        <p>No name provided. Try: ?name=Ali</p>
    </c:if>
</body>
</html>
