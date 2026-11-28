// ECommerceApp.java — پاسخ کامل capstone
// نیازمند: spring-boot-starter-web + spring-boot-starter-data-jpa jars
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceApp {
    public static void main(String[] args) {
        SpringApplication.run(ECommerceApp.class, args);
        System.out.println("=== E-Commerce Backend Started ===");
    }
}
