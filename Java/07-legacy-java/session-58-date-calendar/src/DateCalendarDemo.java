// DateCalendarDemo.java — Date، Calendar، java.time مقایسه
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateCalendarDemo {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        // ---------- Date ----------
        System.out.println("=== Date (Legacy) ===");
        Date now = new Date();
        System.out.println("Date: " + now);
        System.out.println("getTime: " + now.getTime());

        // deprecated methods — فقط برای فهمیدن
        System.out.println("getYear (deprecated): " + (now.getYear() + 1900));
        System.out.println("getMonth (deprecated): " + (now.getMonth() + 1));   // 0-based
        System.out.println("getDate (deprecated): " + now.getDate());

        // ---------- Calendar ----------
        System.out.println("\n=== Calendar (Legacy) ===");
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 15);
        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 30);
        System.out.println("Calendar: " + cal.getTime());
        System.out.println("Year: " + cal.get(Calendar.YEAR));
        System.out.println("Month: " + cal.get(Calendar.MONTH));   // 0-based
        System.out.println("Day: " + cal.get(Calendar.DAY_OF_MONTH));

        // add/subtract
        cal.add(Calendar.DAY_OF_MONTH, 5);
        System.out.println("After +5 days: " + cal.getTime());
        cal.add(Calendar.MONTH, -1);
        System.out.println("After -1 month: " + cal.getTime());

        // ---------- java.time ----------
        System.out.println("\n=== java.time (Modern) ===");

        // LocalDate
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(2000, 1, 15);
        System.out.println("Today: " + today);
        System.out.println("Birthday: " + birthday);

        // LocalTime
        LocalTime time = LocalTime.now();
        System.out.println("Time: " + time);

        // LocalDateTime
        LocalDateTime dt = LocalDateTime.now();
        System.out.println("DateTime: " + dt);

        // ZonedDateTime
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Tehran"));
        System.out.println("ZonedDateTime: " + zdt);

        // Duration
        LocalDateTime start = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 15, 14, 30);
        Duration duration = Duration.between(start, end);
        System.out.println("Duration: " + duration.toDays() + " days, " + duration.toHours() % 24 + " hours");

        // Period
        Period period = Period.between(birthday, today);
        System.out.println("Period: " + period.getYears() + " years, " + period.getMonths() + " months");

        // DateTimeFormatter
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Formatted: " + dt.format(fmt));

        // ---------- مقایسه ----------
        System.out.println("\n=== Comparison ===");
        System.out.println("Date mutable: yes (deprecated)");
        System.out.println("Calendar mutable: yes (deprecated)");
        System.out.println("java.time immutable: yes (thread-safe)");
        System.out.println("java.time intuitive: yes (no 0-based months)");
    }
}
