// ProfileConfig.java — پاسخ کامل پروژهٔ جلسه ۱۲۱
// نیازمند: spring-context jar برای کامپایل
import org.springframework.context.annotation.*;

@Configuration
public class ProfileConfig {

    @Bean
    @Profile("dev")
    public String devConfig() {
        return "Development configuration";
    }

    @Bean
    @Profile("prod")
    public String prodConfig() {
        return "Production configuration";
    }

    @Bean
    @Profile("!dev & !prod")
    public String defaultConfig() {
        return "Default configuration";
    }
}
