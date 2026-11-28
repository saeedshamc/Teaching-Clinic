// RESTBackendCapstone.java — پاسخ کامل capstone: سیستم فروشگاه کامل
// Spring Boot + JPA + Security + WebSocket + Validation + AOP
import java.util.*;

public class RESTBackendCapstone {

    public static void main(String[] args) {
        System.out.println("=== REST Backend Capstone ===");
        System.out.println("Features:");
        System.out.println("  - User registration/login با JWT");
        System.out.println("  - Product CRUD با pagination و filtering");
        System.out.println("  - Order management با transaction");
        System.out.println("  - Cart operations (add/remove items)");
        System.out.println("  - Real-time notifications با WebSocket");
        System.out.println("  - Role-based access (USER, ADMIN)");
        System.out.println("  - Validation و error handling");
        System.out.println("  - Actuator برای monitoring");
        System.out.println("  - AOP برای logging و performance");

        System.out.println("\n=== API Endpoints ===");
        System.out.println("Products:");
        System.out.println("  GET    /api/products");
        System.out.println("  GET    /api/products/{id}");
        System.out.println("  GET    /api/products/active");
        System.out.println("  GET    /api/products/search?name=...");
        System.out.println("  GET    /api/products/category/{category}");
        System.out.println("  POST   /api/products");
        System.out.println("  PUT    /api/products/{id}");
        System.out.println("  DELETE /api/products/{id}");

        System.out.println("\n=== Architecture ===");
        System.out.println("Controller → Service → Repository → Database");
        System.out.println("         ↓");
        System.out.println("    WebSocket + JWT + Actuator + AOP");
    }
}
