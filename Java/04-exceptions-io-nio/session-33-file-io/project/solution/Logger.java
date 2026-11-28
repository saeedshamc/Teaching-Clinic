// Logger.java — پاسخ پروژهٔ جلسه ۳۳
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

enum LogLevel { INFO, WARN, ERROR }

public class Logger {

    static final String LOG_FILE = "app.log";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static void log(LogLevel level, String message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String entry = "[" + timestamp + "] " + level + ": " + message + "\n";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            bw.write(entry);
        } catch (IOException e) {
            System.out.println("Log error: " + e.getMessage());
        }
    }

    static List<String> readLastLines(int count) {
        List<String> all = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                all.add(line);
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
        // آخرین count خط
        int start = Math.max(0, all.size() - count);
        return all.subList(start, all.size());
    }

    public static void main(String[] args) {
        log(LogLevel.INFO, "Application started");
        log(LogLevel.WARN, "Low memory detected");
        log(LogLevel.ERROR, "Connection failed");
        log(LogLevel.INFO, "Retrying connection...");
        log(LogLevel.INFO, "Connection restored");

        System.out.println("=== Last 10 log lines ===");
        for (String line : readLastLines(10)) {
            System.out.println(line);
        }
    }
}
