# پاسخ تمرین‌های جلسه ۱۱۱

## آسان

**۱ و ۲.**
```java
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Hello!</h1>");
    }
}
```

## متوسط

**۳ و ۴.**
```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String name = req.getParameter("name");
    resp.setContentType("application/json");
    resp.getWriter().printf("{\"message\":\"Hello, %s\"}", name);
}
```

## سخت

**۵ و ۶.**
```java
// CRUD in servlet
List<User> users = new ArrayList<>();

// redirect
resp.sendRedirect("/other-page");
```
