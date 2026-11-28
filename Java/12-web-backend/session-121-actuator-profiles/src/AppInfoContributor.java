// AppInfoContributor.java — app info برای /actuator/info
// نیازمند: spring-boot-actuator jar برای کامپایل
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class AppInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("app", Map.of(
            "name", "My Spring Boot App",
            "version", "1.0.0",
            "environment", System.getProperty("spring.profiles.active", "default"),
            "java.version", System.getProperty("java.version")
        ));
        builder.withDetail("team", Map.of(
            "name", "Development Team",
            "contact", "dev@example.com"
        ));
    }
}
