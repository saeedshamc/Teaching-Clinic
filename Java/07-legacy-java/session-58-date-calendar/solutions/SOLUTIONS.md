# پاسخ تمرین‌های جلسه ۵۸

## آسان

**۱ و ۲.**
```java
Date now = new Date();
System.out.println(now.getTime());

Calendar cal = Calendar.getInstance();
cal.set(2024, Calendar.JANUARY, 15);
System.out.println(cal.getTime());
```

## متوسط

**۳ و ۴.**
```java
cal.add(Calendar.DAY_OF_MONTH, 5);   // +5 days
cal.add(Calendar.MONTH, -1);         // -1 month

LocalDate today = LocalDate.now();
LocalDate birthday = LocalDate.of(2000, 1, 15);
```

## سخت

**۵ و ۶.**
```java
LocalDateTime start = LocalDateTime.of(2024, 1, 1, 10, 0);
LocalDateTime end = LocalDateTime.of(2024, 1, 15, 14, 30);
Duration duration = Duration.between(start, end);
System.out.println(duration.toDays() + " days");

LocalDate d1 = LocalDate.of(2000, 1, 15);
LocalDate d2 = LocalDate.now();
Period period = Period.between(d1, d2);
System.out.println(period.getYears() + " years");
```
