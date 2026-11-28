// AppConfig.java — Spring configuration
// نیازمند: spring-context jar برای کامپایل
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.example")
public class AppConfig {

    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    public UserService userService(UserDao userDao) {
        return new UserService(userDao);
    }
}
