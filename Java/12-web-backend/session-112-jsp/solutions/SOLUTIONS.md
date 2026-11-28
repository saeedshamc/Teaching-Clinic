# پاسخ تمرین‌های جلسه ۱۱۲

## آسان

**۱ و ۲.**
```jsp
<%@ page contentType="text/html; charset=UTF-8" %>
<p>Time: <%= new java.util.Date() %></p>
<p>URI: ${pageContext.request.requestURI}</p>
```

## متوسط

**۳ و ۴.**
```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="user" items="${users}">
    <p>${user.name} — ${user.email}</p>
</c:forEach>
<p>Param: ${param.name}</p>
```

## سخت

**۵ و ۶.**
```java
// Servlet
req.setAttribute("users", users);
RequestDispatcher dispatcher = req.getRequestDispatcher("/users.jsp");
dispatcher.forward(req, resp);
```
```jsp
<c:if test="${not empty users}">
    <p>Users found</p>
</c:if>
<c:choose>
    <c:when test="${user.age > 18}">Adult</c:when>
    <c:otherwise>Minor</c:otherwise>
</c:choose>
```
