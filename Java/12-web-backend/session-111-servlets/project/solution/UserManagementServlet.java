// UserManagementServlet.java — پاسخ کامل پروژهٔ جلسه ۱۱۱
// نیازمند: jakarta.servlet-api jar برای کامپایل
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/users/*")
public class UserManagementServlet extends HttpServlet {

    private static final List<Map<String, Object>> users = new ArrayList<>();
    private static int nextId = 1;

    static {
        users.add(Map.of("id", 1, "name", "Ali", "email", "ali@mail.com"));
        users.add(Map.of("id", 2, "name", "Sara", "email", "sara@mail.com"));
        nextId = 3;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String path = req.getRequestURI();
        String idStr = path.substring(path.lastIndexOf('/') + 1);

        if (idStr.equals("users")) {
            resp.getWriter().print(toJson(users));
        } else {
            int id = Integer.parseInt(idStr);
            users.stream().filter(u -> (int) u.get("id") == id).findFirst()
                .ifPresentOrElse(
                    u -> { try { resp.getWriter().print(toJson(u)); } catch (IOException e) {} },
                    () -> { resp.setStatus(404); try { resp.getWriter().print("{\"error\":\"Not found\"}"); } catch (IOException e) {} }
                );
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        Map<String, Object> user = new HashMap<>();
        user.put("id", nextId++);
        user.put("name", name);
        user.put("email", email);
        users.add(user);

        resp.setStatus(201);
        resp.getWriter().print(toJson(user));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getRequestURI();
        String idStr = path.substring(path.lastIndexOf('/') + 1);
        int id = Integer.parseInt(idStr);
        boolean removed = users.removeIf(u -> (int) u.get("id") == id);
        resp.setStatus(removed ? 204 : 404);
    }

    private String toJson(Object obj) {
        if (obj instanceof List) {
            StringBuilder sb = new StringBuilder("[");
            List<?> list = (List<?>) obj;
            for (int i = 0; i < list.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append(toJson(list.get(i)));
            }
            return sb.append("]").toString();
        }
        if (obj instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) obj;
            StringBuilder sb = new StringBuilder("{");
            boolean first = true;
            for (Map.Entry<?, ?> e : map.entrySet()) {
                if (!first) sb.append(",");
                first = false;
                sb.append("\"").append(e.getKey()).append("\":");
                Object v = e.getValue();
                if (v instanceof String) sb.append("\"").append(v).append("\"");
                else sb.append(v);
            }
            return sb.append("}").toString();
        }
        return String.valueOf(obj);
    }
}
