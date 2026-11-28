// Main.java — Spring application entry point
// نیازمند: spring-context jar برای کامپایل
import org.springframework.context.annotation.*;

public class Main {

    public static void main(String[] args) {
        // Create Spring context
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

        // Get beans
        UserService userService = context.getBean(UserService.class);
        UserDao userDao = context.getBean(UserDao.class);

        System.out.println("=== Spring Core Demo ===");
        System.out.println("Users: " + userService.getAllUsers());
        System.out.println("User 1: " + userService.getUserById(1).orElse(null));

        // Create user
        User newUser = userService.createUser("Reza", "reza@mail.com", 28);
        System.out.println("Created: " + newUser);

        // Close context
        context.close();
    }
}
