// JspServlet.java — servlet که JSP را forward می‌کند
// نیازمند: jakarta.servlet-api jar برای کامپایل
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/users")
public class JspServlet extends HttpServlet {

    private static final List<Map<String, Object>> users = new ArrayList<>();

    static {
        users.add(Map.of("id", 1, "name", "Ali", "email", "ali@mail.com", "age", 25));
        users.add(Map.of("id", 2, "name", "Sara", "email", "sara@mail.com", "age", 30));
        users.add(Map.of("id", 3, "name", "Reza", "email", "reza@mail.com", "age", 28));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set attribute برای JSP
        req.setAttribute("users", users);

        // Forward به JSP
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));

        Map<String, Object> user = new HashMap<>();
        user.put("id", users.size() + 1);
        user.put("name", name);
        user.put("email", email);
        user.put("age", age);
        users.add(user);

        // Redirect back to list
        resp.sendRedirect("/users");
    }
}
