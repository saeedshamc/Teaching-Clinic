# جلسه ۱۱۱ — Servlets: وب با جاوا

## اهداف یادگیری
- `Servlet` را برای server-side Java web apps بشناسید
- `HttpServlet`, `doGet`, `doPost` را بفهمید
- `HttpServletRequest`, `HttpServletResponse` را به‌کار ببرید
- `web.xml` و annotations برای servlet mapping را یاد بگیرید

## پیش‌نیازها
- فازهای ۱–۷ دورهٔ جاوا

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Servlet چیست؟

`Servlet` — Java class که HTTP requests را handle می‌کند. روی servlet container (Tomcat, Jetty) اجرا می‌شود:

```java
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println("<h1>Hello from Servlet!</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        resp.getWriter().println("Hello, " + name);
    }
}
```

### Servlet Lifecycle

```
۱. init() — یکبار موقع load
۲. service() → doGet()/doPost() — برای هر request
۳. destroy() — موقع shutdown
```

### HttpServletRequest — خواندن request

```java
String name = req.getParameter("name");           // form/query param
String path = req.getRequestURI();                 // /hello
String method = req.getMethod();                   // GET/POST
String header = req.getHeader("User-Agent");
int contentLength = req.getContentLength();
InputStream body = req.getInputStream();            // raw body
```

### HttpServletResponse — نوشتن response

```java
resp.setStatus(200);                                // HTTP status
resp.setContentType("application/json");
resp.setHeader("X-Custom", "value");
PrintWriter out = resp.getWriter();                 // text response
out.println("Hello");

// یا binary
OutputStream os = resp.getOutputStream();
os.write(bytes);

// redirect
resp.sendRedirect("/other-page");
```

### web.xml — بدون annotations

```xml
<servlet>
    <servlet-name>HelloServlet</servlet-name>
    <servlet-class>com.example.HelloServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>HelloServlet</servlet-name>
    <url-pattern>/hello</url-pattern>
</servlet-mapping>
```

## کد مهم

`src/HelloServlet.java` — basic servlet.
`src/UserServlet.java` — servlet با CRUD.
`src/web.xml` — servlet configuration.

## خطاهای رایج
- `getWriter()` و `getOutputStream()` را همزمان استفاده کردن → `IllegalStateException`.
- `sendRedirect` بعد از `getWriter` → headers already sent.
- `init()` را بدون `super.init()` override کردن → servlet config null.
- URL mapping را بدون leading slash تعریف کردن → `PatternSyntaxException`.

## جمع‌بندی
- Servlet برای server-side Java web — HttpServlet + doGet/doPost.
- Request: `getParameter`, `getHeader`, `getInputStream`.
- Response: `setStatus`, `setContentType`, `getWriter`.
- `@WebServlet` یا `web.xml` برای mapping.

**جلسهٔ بعد:** JSP — Java Server Pages.
