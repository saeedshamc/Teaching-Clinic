// DockerizedApp.java — پاسخ کامل پروژهٔ جلسه ۱۴۴
// Java application برای Docker containerization
public class DockerizedApp {

    public static void main(String[] args) {
        System.out.println("=== Dockerized Java Application ===");
        System.out.println("Port: " + System.getenv("PORT"));
        System.out.println("Profile: " + System.getenv("SPRING_PROFILES_ACTIVE"));
        System.out.println("DB Host: " + System.getenv("DB_HOST"));

        // Simulate application startup
        System.out.println("Application started successfully");
        System.out.println("Ready to accept connections on port 8080");
    }
}
