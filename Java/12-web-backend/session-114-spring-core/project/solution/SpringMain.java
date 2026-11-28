// SpringMain.java — پاسخ کامل پروژهٔ جلسه ۱۱۴
// نیازمند: spring-context jar برای کامپایل
import org.springframework.context.annotation.*;

public class SpringMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

        SpringUserService service = context.getBean(SpringUserService.class);

        System.out.println("=== Spring Core Project ===");
        System.out.println("Total users: " + service.getUserCount());
        System.out.println("All users: " + service.getAllUsers());
        System.out.println("User 1: " + service.getUserById(1).orElse(null));

        User newUser = service.createUser("Maryam", "maryam@mail.com", 32);
        System.out.println("Created: " + newUser);
        System.out.println("New count: " + service.getUserCount());

        context.close();
    }
}
