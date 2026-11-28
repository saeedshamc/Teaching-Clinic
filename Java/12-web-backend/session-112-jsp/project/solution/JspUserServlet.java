// JspUserServlet.java — پاسخ کامل پروژهٔ جلسه ۱۱۲
// نیازمند: jakarta.servlet-api jar برای کامپایل
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/users/*")
public class JspUserServlet extends HttpServlet {

    private static final List<Map<String, Object>> users = new ArrayList<>();
    private static int nextId = 1;

    static {
        users.add(Map.of("id", 1, "name", "Ali", "email", "ali@mail.com", "age", 25));
        users.add(Map.of("id", 2, "name", "Sara", "email", "sara@mail.com", "age", 30));
        nextId = 3;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();
        String action = path.substring(path.lastIndexOf('/') + 1);

        switch (action) {
            case "users" -> {
                req.setAttribute("users", users);
                req.getRequestDispatcher("/users.jsp").forward(req, resp);
            }
            case "new" -> req.getRequestDispatcher("/user-form.jsp").forward(req, resp);
            default -> {
                try {
                    int id = Integer.parseInt(action);
                    users.stream().filter(u -> (int) u.get("id") == id).findFirst()
                        .ifPresent(u -> {
                            try {
                                req.setAttribute("user", u);
                                req.getRequestDispatcher("/user-form.jsp").forward(req, resp);
                            } catch (Exception e) {}
                        });
                } catch (NumberFormatException e) {
                    resp.sendError(404);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));

        Map<String, Object> user = new HashMap<>();
        user.put("id", nextId++);
        user.put("name", name);
        user.put("email", email);
        user.put("age", age);
        users.add(user);

        resp.sendRedirect("/users");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getRequestURI();
        String idStr = path.substring(path.lastIndexOf('/') + 1);
        int id = Integer.parseInt(idStr);
        users.removeIf(u -> (int) u.get("id") == id);
        resp.sendRedirect("/users");
    }
}
