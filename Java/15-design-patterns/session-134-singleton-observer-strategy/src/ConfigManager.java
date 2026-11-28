// ConfigManager.java — Singleton برای configuration
// قابل کامپایل با javac — بدون dependency
import java.util.*;

public class ConfigManager {

    private static class Holder {
        static final ConfigManager INSTANCE = new ConfigManager();
    }

    private final Map<String, String> config = new HashMap<>();

    private ConfigManager() {
        // Load default config
        config.put("app.name", "MyApp");
        config.put("app.version", "1.0.0");
        config.put("db.url", "jdbc:mysql://localhost:3306/mydb");
    }

    public static ConfigManager getInstance() {
        return Holder.INSTANCE;
    }

    public void set(String key, String value) {
        config.put(key, value);
    }

    public String get(String key) {
        return config.get(key);
    }

    public String getOrDefault(String key, String defaultValue) {
        return config.getOrDefault(key, defaultValue);
    }

    public void printAll() {
        config.forEach((k, v) -> System.out.println(k + " = " + v));
    }
}
