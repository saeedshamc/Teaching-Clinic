# پاسخ تمرین‌های جلسه ۵۴

## آسان

**۱ و ۲.**
```java
String json = """
    {
      "name": "Ali",
      "age": 20
    }
    """;

String result = switch (day) {
    case "SATURDAY", "SUNDAY" -> "Weekend";
    default -> "Weekday";
};
```

## متوسط

**۳.**
```java
String msg = """
    Hello %s, you are %d years old
    """.formatted(name, age);
```

**۴.**
```java
String result = switch (num) {
    case 3 -> {
        String msg = "Three";
        yield msg + " (odd)";
    }
    default -> "Other";
};
```

## سخت

**۵.**
```java
String html = """
    <html>
        <body>
            <h1>%s</h1>
        </body>
    </html>
    """.formatted(title);
```

**۶.**
```java
enum Color { RED, GREEN, BLUE }
String desc = switch (color) {
    case RED -> "Stop";
    case GREEN -> "Go";
    case BLUE -> "Caution";
};
```
