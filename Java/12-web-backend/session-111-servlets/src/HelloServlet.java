// HelloServlet.java — basic servlet
// نیازمند: jakarta.servlet-api یا javax.servlet-api jar برای کامپایل
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("""
            <!DOCTYPE html>
            <html>
            <head><title>Hello Servlet</title></head>
            <body>
                <h1>Hello from Servlet!</h1>
                <p>Method: GET</p>
                <p>URI: %s</p>
                <p>User-Agent: %s</p>
            </body>
            </html>
            """.formatted(req.getRequestURI(), req.getHeader("User-Agent")));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String name = req.getParameter("name");
        PrintWriter out = resp.getWriter();
        out.printf("{\"message\": \"Hello, %s!\", \"method\": \"POST\"}", name != null ? name : "Guest");
    }
}
