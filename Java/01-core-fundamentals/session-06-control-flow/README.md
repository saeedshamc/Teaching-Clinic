# جلسه ۰۶ — کنترل جریان (if / switch)

## اهداف یادگیری
- `if / else if / else` را به‌کار ببرید
- `switch` کلاسیک و expression (Java 14+) را بشناسید
- `break` / `continue` / `return` را در حلقه‌ها (جلسهٔ بعد) و شرط‌ها بفهمید
- الگوی guard clause و early return را یاد بگیرید

## پیش‌نیازها
- جلسات ۰۴ و ۰۵

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### if / else if / else

```java
int score = 85;
if (score >= 90) {
    System.out.println("A");
} else if (score >= 80) {
    System.out.println("B");
} else if (score >= 70) {
    System.out.println("C");
} else {
    System.out.println("F");
}
```
- شرط‌ها **به ترتیب** چک می‌شوند؛ اولین true اجرا و بقیه رد می‌شوند.
- برای boolean از `== true` استفاده نکنید — خود شرط boolean است.

### switch کلاسیک (قبل از Java 14)

```java
switch (day) {
    case 1: System.out.println("Saturday"); break;
    case 2: System.out.println("Sunday"); break;
    default: System.out.println("Other");
}
```
- بدون `break` **fall-through** می‌کند (همهٔ caseهای پشت‌سرهم اجرا می‌شوند).
- `default` اختیاری است.

### switch expression (Java 14+, LTS 17)

```java
String name = switch (day) {
    case 1 -> "Saturday";
    case 2 -> "Sunday";
    case 6, 7 -> "Weekend";      // چند مقدار
    default -> "Other";
};
```
- `->` arrow syntax — نیازی به break نیست و fall-through ندارد.
- می‌تواند مقدار برگرداند (مثل expression) یا بلاک داشته باشد.
- Java 21+: pattern matching با `case String s`, `case null` و sealed types.

### break / continue / return در شرط‌ها
- `break` در switch بدون arrow → خروج از switch.
- `continue` فقط در حلقه معنا دارد (جلسهٔ ۰۷).
- `return` از متد خارج می‌شود — برای early return.

### الگوی guard clause
```java
static void process(int age) {
    if (age < 0) return;        // early return برای حالت نامعتبر
    if (age > 150) return;
    // منطق اصلی فقط برای ورودی معتبر
    System.out.println("Age: " + age);
}
```

## کد مهم

`src/ControlFlowDemo.java` — if/else if، switch کلاسیک با fall-through، switch expression، early return.

## خطاهای رایج
- `switch` روی String/int/enum/char کار می‌کند ولی روی double/boolean نه (قبل از Java 21 records/patterns).
- فراموش کردن `break` در switch کلاسیک → fall-through ناخواسته.
- `=` به‌جای `==` در if → compile error یا منطق اشتباه.
- شرط‌های unreachable (`if(true)` قبل از `else` همه‌کاره) → compiler warning.

## جمع‌بندی
- `if/else if/else` برای منطق چندشاخه‌ای.
- `switch` expression برای مقداردهی تمیز (Java 14+).
- `break` در switch کلاسیک، `return` برای خروج زودهنگام.

**جلسهٔ بعد:** حلقه‌ها (for/while/do-while).
