// Application.java — Spring Boot application برای Docker demo
// نیازمند: spring-boot-starter-web jar برای کامپایل
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Application {

    public static void main(String[] args) {
        // SpringApplication.run(Application.class, args);
        System.out.println("=== Java Application Started ===");
        System.out.println("Port: 8080");
        System.out.println("Profile: " + System.getenv("SPRING_PROFILES_ACTIVE"));
    }
}
