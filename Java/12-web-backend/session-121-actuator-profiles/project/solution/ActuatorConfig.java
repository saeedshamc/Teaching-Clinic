// ActuatorConfig.java — پاسخ کامل پروژهٔ جلسه ۱۲۱
// نیازمند: spring-boot-actuator jar برای کامپایل
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class ActuatorConfig {

    @Component
    public static class DatabaseHealthIndicator implements HealthIndicator {
        @Override
        public Health health() {
            return Health.up()
                .withDetail("database", "UP")
                .withDetail("connectionPool", "10/10")
                .build();
        }
    }

    @Component
    public static class AppInfoContributor implements InfoContributor {
        @Override
        public void contribute(Info.Builder builder) {
            builder.withDetail("app", Map.of(
                "name", "MyApp",
                "version", "1.0.0",
                "build", "production"
            ));
        }
    }
}
