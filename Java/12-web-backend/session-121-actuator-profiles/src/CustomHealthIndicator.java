// CustomHealthIndicator.java — custom health check
// نیازمند: spring-boot-actuator jar برای کامپایل
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        boolean dbUp = checkDatabase();
        boolean cacheUp = checkCache();
        boolean apiUp = checkExternalApi();

        Health.Builder builder = Health.up();
        builder.withDetail("database", dbUp ? "UP" : "DOWN");
        builder.withDetail("cache", cacheUp ? "UP" : "DOWN");
        builder.withDetail("externalApi", apiUp ? "UP" : "DOWN");

        if (!dbUp || !cacheUp || !apiUp) {
            builder.down();
        }
        return builder.build();
    }

    private boolean checkDatabase() {
        // Simulate DB check
        return true;
    }

    private boolean checkCache() {
        // Simulate cache check
        return true;
    }

    private boolean checkExternalApi() {
        // Simulate external API check
        return true;
    }
}
