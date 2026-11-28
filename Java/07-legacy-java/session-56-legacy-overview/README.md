# جلسه ۵۶ — Legacy Java: مرور کلی

## اهداف یادگیری
- Legacy Java چیست و چرا هنوز مهم است را بفهمید
- deprecated APIها را بشناسید
- جایگزین‌های مدرن برای legacy را یاد بگیرید
- کد قدیمی را بخوانید و بفهمید

## پیش‌نیازها
- همهٔ جلسات قبلی

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### Legacy Java — کدهای قدیمی

خیلی از پروژه‌های واقعی هنوز از APIهای قدیمی استفاده می‌کنند:
- `Vector`, `Hashtable` (به‌جای `ArrayList`, `HashMap`)
- `Date`, `Calendar` (به‌جای `java.time`)
- `Enumeration` (به‌جای `Iterator`)
- `Applet` (به‌جای JSF/JavaFX/Web)
- `AWT` (به‌جای Swing/JavaFX)
- `RMI` (به‌جای REST/gRPC)

### چرا هنوز مهم است؟

- **کدهای قدیمی** — maintain کردن سیستم‌های موجود.
- **legacy codebases** — بسیاری از شرکت‌ها هنوز Java 8 یا قدیمی‌تر دارند.
- **فهمیدن کد** — برای خواندن و debug کردن کد قدیمی.
- **migration** — دانستن چه چیزی deprecated شده و جایگزینش چیست.

### Deprecated در برابر Removed

| Deprecated | Removed |
|---|---|
| هنوز کار می‌کند ولی هشدار می‌دهد | دیگر وجود ندارد |
| `Date`, `Calendar` | `Applet` (Java 9+) |
| `Thread.stop()` | `SecurityManager` (deprecated) |
| `new Integer()` | `finalize()` (deprecated) |

### Legacy → Modern mapping

| Legacy | Modern |
|---|---|
| `Vector`, `Hashtable` | `ArrayList`, `HashMap` |
| `Enumeration` | `Iterator` |
| `Date`, `Calendar` | `java.time` (LocalDate, LocalDateTime) |
| `StringBuffer` | `StringBuilder` |
| `Applet` | JSF, JavaFX, Web |
| `AWT` | Swing, JavaFX |
| `RMI` | REST, gRPC, RESTful Web Services |

## کد مهم

`src/LegacyOverviewDemo.java` — مقایسهٔ legacy و modern.

## خطاهای رایج
- `Vector`/`Hashtable` را برای کد جدید استفاده کردن → کندتر از ArrayList/HashMap.
- `Date`/`Calendar` را برای کد جدید استفاده کردن → `java.time` بهتر است.
- `Enumeration` را برای کد جدید استفاده کردن → `Iterator` بهتر است.
- Applet را برای پروژهٔ جدید استفاده کردن → منسوخ شده.

## جمع‌بندی
- Legacy Java هنوز در دنیای واقعی وجود دارد.
- Deprecated ≠ Removed — deprecated هنوز کار می‌کند.
- برای کد جدید از modern APIs استفاده کنید.

**جلسهٔ بعد:** Vector و Hashtable.
