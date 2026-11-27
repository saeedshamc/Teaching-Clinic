# پاسخ تمرین‌های جلسه ۰۶

## آسان

**۱.**
```java
int n = -5;
if (n > 0) System.out.println("positive");
else if (n < 0) System.out.println("negative");
else System.out.println("zero");
```

**۲.**
```java
String day = "Saturday";
String fa = switch (day) {
    case "Saturday" -> "شنبه";
    case "Sunday" -> "یکشنبه";
    case "Monday" -> "دوشنبه";
    default -> "نامشخص";
};
System.out.println(fa);
```

## متوسط

**۳.**
```java
double calc(double a, double b, String op) {
    return switch (op) {
        case "+" -> a + b;
        case "-" -> a - b;
        case "*" -> a * b;
        case "/" -> b == 0 ? Double.NaN : a / b;
        default -> 0;
    };
}
```

**۴.**
```java
static void greet(String name) {
    if (name == null || name.isBlank()) return;   // guard clause
    System.out.println("Hello " + name);
}
```

## سخت

**۵.**
```java
// کلاسیک با fall-through — هر سه case چاپ می‌شود!
switch (x) {
    case 1: System.out.println("one");
    case 2: System.out.println("two");
    case 3: System.out.println("three"); break;
}
// اگر x=1 → one, two, three همه چاپ می‌شوند چون break نیست.
// با arrow: case 1 -> ... فقط همان یکی اجرا می‌شود — fall-through ندارد.
```

**۶.** enum در فاز ۲ جلسهٔ ۲۲ عمیق می‌شود؛ فعلاً:
```java
enum Color { RED, GREEN, BLUE }
Color c = Color.RED;
String label = switch (c) {
    case RED -> "قرمز";
    case GREEN -> "سبز";
    case BLUE -> "آبی";
};
```
