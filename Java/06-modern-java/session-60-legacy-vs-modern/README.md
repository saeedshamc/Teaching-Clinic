# جلسه ۶۰ — Legacy در برابر Modern: جمع‌بندی فاز

## اهداف یادرسی
- همهٔ مباحث legacy را مرور کنید
- تصمیم بگیرید چه زمانی از legacy و چه زمانی از modern استفاده کنید
- جدول مرجع legacy → modern را کامل کنید
- فاز ۴ را جمع‌بندی کنید

## پیش‌نیازها
- جلسات ۵۶–۵۹

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### جدول مرجع کامل Legacy → Modern

| Legacy | Modern | دلیل |
|---|---|---|
| `Vector` | `ArrayList` | non-synchronized — سریع‌تر |
| `Hashtable` | `HashMap` | non-synchronized — سریع‌تر |
| `Enumeration` | `Iterator` | modern iteration — remove, ListIterator |
| `Date` | `LocalDate/LocalDateTime` | immutable, thread-safe, intuitive |
| `Calendar` | `java.time` | immutable, thread-safe |
| `StringBuffer` | `StringBuilder` | non-synchronized — سریع‌تر |
| `Applet` | JSF/JavaFX/Web | Applet منسوخ — browser support حذف |
| `AWT` | Swing/JavaFX | pure Java, richer widgets |
| `RMI` | REST/gRPC | HTTP-based, language-agnostic |
| `Thread.stop()` | `interrupt()` | deprecated — unsafe |
| `finalize()` | try-with-resources/Cleaner | deprecated — unreliable |
| `new Integer()` | `Integer.valueOf()` | boxing — deprecated |

### چه زمانی از Legacy استفاده کنیم؟

- **Maintain کردن کد قدیمی** — وقتی نمی‌توانید migrate کنید.
- **Legacy API که هنوز پشتیبانی می‌شود** — مثل Vector در Java 17.
- **فهمیدن کد موجود** — برای خواندن و debug کردن.

### چه زمانی از Modern استفاده کنیم؟

- **همیشه برای کد جدید** — modern APIs بهتر، سریع‌تر، امن‌تر.
- **Performance-critical** — non-synchronized سریع‌تر.
- **Thread-safety** — java.time immutable، Collections.synchronizedList.
- **Web services** — REST/gRPC به‌جای RMI.
- **GUI** — JavaFX/Web به‌جای AWT/Applet.

### Migration Strategy

```java
// ۱. شناسایی deprecated APIs
// javac -Xlint:deprecation برای دیدن warningها

// ۲. جایگزین پیدا کنید
// Vector → ArrayList
// Hashtable → HashMap
// Date/Calendar → java.time

// ۳. تست کنید
// Unit tests برای اطمینان از behavior یکسان

// ۴. Refactor کنید
// کم‌کم migrate کنید — نه یکجا
```

## کد مهم

`src/LegacyVsModernDemo.java` — مقایسهٔ کامل legacy و modern.

## جمع‌بندی فاز ۴

- **Modern Java (۴۶–۵۵)**: Lambda, Method Ref, Streams, Optional, Records, Sealed, Pattern Matching, Text Blocks, Switch Expressions, Modules ✅
- **Legacy Java (۵۶–۶۰)**: Overview, Vector/Hashtable, Date/Calendar, AWT/RMI, Legacy vs Modern ✅

**فاز ۵: Desktop — AWT/Swing, JavaFX, SWT**
