# جلسه ۲۲ — Enum

## اهداف یادگیری
- `enum` را برای مجموعهٔ ثابت مقادیر بسازید
- enum با فیلدها، سازنده و متدها را پیاده کنید
- `valueOf` و `values` را به‌کار ببرید
- switch روی enum را بفهمید

## پیش‌نیازها
- جلسات ۱۶–۲۱

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### enum ساده

```java
enum Color {
    RED, GREEN, BLUE, YELLOW
}

Color c = Color.RED;
System.out.println(c);           // RED
System.out.println(c.ordinal()); // 0 — index در تعریف
System.out.println(Color.values().length); // 4
```

### enum با فیلد و متد

```java
enum Status {
    PENDING("در انتظار", 0),
    IN_PROGRESS("در حال انجام", 1),
    DONE("تمام شده", 2);

    private final String label;
    private final int code;

    Status(String label, int code) {
        this.label = label;
        this.code = code;
    }

    public String getLabel() { return label; }
    public int getCode() { return code; }
}
```

### valueOf و values

```java
Status s = Status.valueOf("DONE");   // رشته → enum (case-sensitive)
Status[] all = Status.values();      // همهٔ مقادیر
for (Status st : all) System.out.println(st.getLabel());
```

### switch روی enum

```java
switch (status) {
    case PENDING -> System.out.println("Waiting...");
    case IN_PROGRESS -> System.out.println("Working...");
    case DONE -> System.out.println("Finished!");
}
```

## کد مهم

`src/EnumDemo.java` — enum ساده، enum با فیلد/متد، valueOf/values، switch روی enum.

## خطاهای رایج
- `Color.valueOf("red")` → `IllegalArgumentException` — case-sensitive است.
- enum را `new` کردن → compile error — فقط ثابت‌های تعریف‌شده.
- `ordinal()` را برای persistence استفاده نکنید — ترتیب ممکن است عوض شود.
- switch بدون `default` روی enum → compiler هشدار می‌دهد.

## جمع‌بندی
- Enum = مجموعهٔ ثابت امن از مقادیر.
- می‌تواند فیلد، سازنده، متد داشته باشد.
- `values()` برای همه، `valueOf()` برای تبدیل رشته.

**جلسهٔ بعد:** inner classes.
