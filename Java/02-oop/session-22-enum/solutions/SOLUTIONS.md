# پاسخ تمرین‌های جلسه ۲۲

## آسان

**۱.**
```java
enum Day { SATURDAY, SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY }
for (Day d : Day.values()) System.out.println(d);
```

**۲.**
```java
enum Direction {
    NORTH, SOUTH, EAST, WEST;
    public Direction opposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
        };
    }
}
```

## متوسط

**۳.**
```java
enum HttpStatus {
    OK(200, "OK"), NOT_FOUND(404, "Not Found"), ERROR(500, "Internal Error");
    private final int code;
    private final String message;
    HttpStatus(int code, String message) { this.code = code; this.message = message; }
    public int getCode() { return code; }
    public String getMessage() { return message; }
}
```

**۴.**
```java
try {
    Day d = Day.valueOf("MONDAY");
} catch (IllegalArgumentException e) {
    System.out.println("Invalid day");
}
```

## سخت

**۵.**
```java
enum Operation {
    ADD { double apply(double a, double b) { return a + b; } },
    SUB { double apply(double a, double b) { return a - b; } },
    MUL { double apply(double a, double b) { return a * b; } };
    abstract double apply(double a, double b);
}
```

**۶.**
```java
String result = switch (day) {
    case SATURDAY, SUNDAY -> "Weekend";
    default -> "Weekday";
};
```
