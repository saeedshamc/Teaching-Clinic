// DatabaseConfig.java — profile-specific bean configuration
// نیازمند: spring-context jar برای کامپایل
import org.springframework.context.annotation.*;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        System.out.println("Creating DEV data source (H2 in-memory)");
        // In production, use HikariCP with H2
        return null;   // placeholder — use real DataSource in production
    }

    @Bean
    @Profile("prod")
    public DataSource prodDataSource() {
        System.out.println("Creating PROD data source (MySQL)");
        // In production, use HikariCP with MySQL
        return null;   // placeholder — use real DataSource in production
    }

    @Bean
    @Profile("!dev & !prod")   // default profile
    public DataSource defaultDataSource() {
        System.out.println("Creating DEFAULT data source");
        return null;
    }
}
