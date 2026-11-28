# پروژهٔ جلسه ۱۱۱ — سیستم مدیریت کاربران با Servlet

یک سیستم مدیریت کاربران با Servlet بسازید که:
- `UserServlet` با `@WebServlet("/users")`
- `doGet` برای list/find users
- `doPost` برای create user
- `doPut`/`doDelete` برای update/delete
- `getParameter` برای form data
- `setContentType("application/json")` برای JSON response
- `web.xml` برای servlet mapping

نمونه:
```
GET /users → [{"id":1,"name":"Ali"},...]
POST /users → {"id":3,"name":"Reza"}
GET /users/1 → {"id":1,"name":"Ali"}
```

## صورت پروژه
پاسخ در `solution/` — UserServlet.java + web.xml.
