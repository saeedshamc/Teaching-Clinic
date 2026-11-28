# جلسه ۱۱۲ — JSP: Java Server Pages

## اهداف یادگیری
- `JSP` را برای server-side rendering بشناسید
- `JSP tags` و `scriptlets` را بفهمید
- `EL` (Expression Language) و `JSTL` را به‌کار ببرید
- `RequestDispatcher` برای include/forward را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۱۱۱ (Servlets)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### JSP چیست؟

`JSP` — HTML + Java embedded. روی servlet container اجرا می‌شود و به servlet تبدیل می‌شود:

```jsp
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<html>
<body>
    <h1>Hello JSP!</h1>
    <p>Current time: <%= new java.util.Date() %></p>
</body>
</html>
```

### JSP Elements

| Element | Syntax | Usage |
|---|---|---|
| Directive | `<%@ page ... %>` | page settings |
| Declaration | `<%! ... %>` | variables/methods |
| Scriptlet | `<% ... %>` | Java code |
| Expression | `<%= ... %>` | output value |
| Comment | `<%-- ... --%>` | JSP comment |

### EL — Expression Language

```jsp
<!-- Access request attributes -->
<p>Name: ${user.name}</p>
<p>Email: ${user.email}</p>

<!-- Access request parameters -->
<p>Param: ${param.name}</p>

<!-- Access session -->
<p>Session user: ${sessionScope.user.name}</p>

<!-- Conditions -->
<c:if test="${user.age > 18}">
    <p>Adult</p>
</c:if>
```

### JSTL — JSTL Tags

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Loop -->
<c:forEach var="user" items="${users}">
    <p>${user.name} — ${user.email}</p>
</c:forEach>

<!-- If -->
<c:if test="${not empty users}">
    <p>Users found</p>
</c:if>

<!-- Choose -->
<c:choose>
    <c:when test="${user.age > 18}">Adult</c:when>
    <c:otherwise>Minor</c:otherwise>
</c:choose>

<!-- Format -->
<fmt:formatNumber value="${price}" type="currency" currencyCode="USD"/>
```

### RequestDispatcher — include/forward

```java
// In Servlet
RequestDispatcher dispatcher = req.getRequestDispatcher("/page.jsp");
dispatcher.forward(req, resp);   // forward — URL doesn't change
dispatcher.include(req, resp);   // include — URL changes
```

```jsp
<!-- In JSP -->
<jsp:include page="/header.jsp"/>
<jsp:forward page="/other.jsp"/>
```

## کد مهم

`src/index.jsp` — basic JSP page.
`src/users.jsp` — JSP با JSTL.
`src/UserServlet.java` — servlet که JSP را forward می‌کند.

## خطاهای رایج
- `scriptlet` را زیاد استفاده کردن → hard to maintain — JSTL بهتر است.
- `${}` را بدون EL support استفاده کردن → literal text.
- `forward` بعد از response commit → `IllegalStateException`.
- `page` directive را فراموش کردن → wrong content type.

## جمع‌بندی
- JSP برای server-side rendering — HTML + Java.
- EL برای `${}` expressions، JSTL برای logic.
- `RequestDispatcher` برای include/forward.
- Scriptlets را minimize کنید — JSTL preferable.

**جلسهٔ بعد:** Jakarta EE — enterprise Java.
