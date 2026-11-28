# پروژهٔ جلسه ۱۱۲ — سیستم مدیریت کاربران با JSP

یک سیستم مدیریت کاربران با JSP بسازید که:
- `UserServlet` با `doGet`/`doPost` برای CRUD
- `users.jsp` با JSTL برای list rendering
- `user-form.jsp` برای add/edit user
- `header.jsp` و `footer.jsp` برای common layout
- `RequestDispatcher` برای include/forward
- `c:forEach`, `c:if`, `c:choose` برای JSTL

نمونه:
```
GET /users → users.jsp با table
POST /users → add user → redirect to /users
GET /users/new → user-form.jsp
```

## صورت پروژه
پاسخ در `solution/` — UserServlet.java + users.jsp + user-form.jsp + layout.jsp.
