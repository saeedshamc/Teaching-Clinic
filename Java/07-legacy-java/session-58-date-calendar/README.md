# جلسه ۵۸ — Date و Calendar (Legacy Date/Time)

## اهداف یادگیری
- `java.util.Date` و `Calendar` را بشناسید
- deprecated بودن آن‌ها را درک کنید
- `java.time` را به‌عنوان جایگزین مدرن یاد بگیرید
- migrate از Date/Calendar به java.time را انجام دهید

## پیش‌نیازها
- جلسهٔ ۵۷

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### Date — legacy date/time

```java
import java.util.Date;

Date now = new Date();
System.out.println(now);   // Thu Jan 15 10:30:00 UTC 2024

// deprecated methods
now.getYear();        // deprecated — سال از ۱۹۰۰
now.getMonth();       // deprecated — ۰-based
now.getDate();        // deprecated — روز ماه
now.getHours();       // deprecated
now.getMinutes();     // deprecated

// modern methods
now.getTime();        // milliseconds since epoch
now.after(otherDate); // مقایسه
now.before(otherDate);
```

### Calendar — legacy calendar

```java
import java.util.Calendar;

Calendar cal = Calendar.getInstance();
cal.set(2024, Calendar.JANUARY, 15);
System.out.println(cal.getTime());   // Date object

// get fields
System.out.println(cal.get(Calendar.YEAR));        // 2024
System.out.println(cal.get(Calendar.MONTH));       // 0 (January)
System.out.println(cal.get(Calendar.DAY_OF_MONTH)); // 15

// add/subtract
cal.add(Calendar.DAY_OF_MONTH, 5);
cal.add(Calendar.MONTH, -1);

// set time
cal.set(Calendar.HOUR_OF_DAY, 10);
cal.set(Calendar.MINUTE, 30);
```

### java.time — modern replacement

```java
import java.time.*;

// LocalDate — فقط تاریخ
LocalDate today = LocalDate.now();
LocalDate birthday = LocalDate.of(2000, 1, 15);

// LocalTime — فقط زمان
LocalTime now = LocalTime.now();

// LocalDateTime — تاریخ + زمان
LocalDateTime dt = LocalDateTime.now();

// ZonedDateTime — با timezone
ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Tehran"));

// Duration/Period — فاصله زمانی
Duration duration = Duration.between(start, end);
Period period = Period.between(date1, date2);
```

## کد مهم

`src/DateCalendarDemo.java` — Date، Calendar، java.time مقایسه.

## خطاهای رایج
- `Date.getYear()` → deprecated و ۰-based — از `Calendar` یا `java.time` استفاده کنید.
- `Calendar.MONTH` → ۰-based (January=0) — اشتباه رایج.
- `Date` mutable → thread-unsafe — `java.time` immutable است.
- timezone issues با `Date` → `ZonedDateTime` بهتر است.

## جمع‌بندی
- Date/Calendar deprecated — برای کد جدید از java.time استفاده کنید.
- java.time immutable، thread-safe، intuitive.
- `LocalDate`, `LocalTime`, `LocalDateTime`, `ZonedDateTime`, `Duration`, `Period`.

**جلسهٔ بعد:** AWT Legacy و RMI.
