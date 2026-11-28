// CalendarSystem.java — پاسخ پروژهٔ جلسه ۵۸
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class Event {
    String name;
    LocalDateTime dateTime;
    Event(String name, LocalDateTime dateTime) {
        this.name = name; this.dateTime = dateTime;
    }
}

public class CalendarSystem {

    private List<Event> events = new ArrayList<>();
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    void addEvent(String name, LocalDateTime dateTime) {
        events.add(new Event(name, dateTime));
    }

    Event getNextEvent() {
        LocalDateTime now = LocalDateTime.now();
        return events.stream()
            .filter(e -> e.dateTime.isAfter(now))
            .min((a, b) -> a.dateTime.compareTo(b.dateTime))
            .orElse(null);
    }

    long daysUntil(LocalDateTime dateTime) {
        return Duration.between(LocalDateTime.now(), dateTime).toDays();
    }

    void displayUpcoming() {
        System.out.println("=== Upcoming Events ===");
        LocalDateTime now = LocalDateTime.now();
        events.stream()
            .filter(e -> e.dateTime.isAfter(now))
            .sorted((a, b) -> a.dateTime.compareTo(b.dateTime))
            .forEach(e -> {
                long days = daysUntil(e.dateTime);
                System.out.println(e.name + " — " + e.dateTime.format(FMT) + " (in " + days + " days)");
            });
    }

    public static void main(String[] args) {
        CalendarSystem cal = new CalendarSystem();
        cal.addEvent("Team Meeting", LocalDateTime.now().plusDays(5).withHour(10));
        cal.addEvent("Project Deadline", LocalDateTime.now().plusDays(10).withHour(17));
        cal.addEvent("Birthday", LocalDateTime.now().plusDays(30).withHour(0).withMinute(0));

        cal.displayUpcoming();

        Event next = cal.getNextEvent();
        if (next != null) {
            System.out.println("\nNext event: " + next.name + " in " + cal.daysUntil(next.dateTime) + " days");
        }

        // محاسبهٔ فاصله بین دو تاریخ
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().plusMonths(3);
        Period period = Period.between(start, end);
        System.out.println("Period: " + period.getMonths() + " months");
    }
}
