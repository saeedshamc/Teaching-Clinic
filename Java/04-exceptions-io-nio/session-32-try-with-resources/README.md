# جلسه ۳۲ — Try-with-Resources

## اهداف یادگیری
- `AutoCloseable` و `Closeable` را بفهمید
- try-with-resources برای مدیریت خودکار resource را به‌کار ببرید
- چند resource در یک try را بشناسید
- suppressed exception را درک کنید

## پیش‌نیازها
- جلسهٔ ۳۱

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### مشکل: فراموش کردن close

```java
FileReader fr = new FileReader("file.txt");
BufferedReader br = new BufferedReader(fr);
// اگر exception بدهد، close نمی‌شود → resource leak!
br.close();   // فراموش می‌شود
```

### try-with-resources — خودکار close

```java
try (FileReader fr = new FileReader("file.txt");
     BufferedReader br = new BufferedReader(fr)) {
    String line = br.readLine();
    // ...
}   // br.close() و fr.close() خودکار صدا زده می‌شود — حتی با exception
```

- resource باید `AutoCloseable` یا `Closeable` را implements کرده باشد.
- `close()` خودکار در پایان try (حتی با exception یا return) صدا زده می‌شود.
- می‌توانید چند resource با `;` جدا کنید.

### مثال کامل

```java
try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    System.out.println("Error: " + e.getMessage());
}
```

### Suppressed exception

اگر هم try و هم close خطا بدهند، exception اصلی پرتاب می‌شود و exception close به‌عنوان "suppressed" به آن اضافه می‌شود:

```java
catch (IOException e) {
    for (Throwable suppressed : e.getSuppressed()) {
        System.out.println("Suppressed: " + suppressed);
    }
}
```

## کد مهم

`src/TryWithResourcesDemo.java` — try-with-resources با FileReader، چند resource، suppressed exception.

## خطاهای رایج
- resource که `AutoCloseable` نیست → compile error.
- `close()` را دستی هم صدا زدن → دوبار close (معمولاً بی‌خطر ولی اشتباه).
- resource را بیرون try تعریف کردن → try-with-resources کار نمی‌کند.
- exception در close را نادیده گرفتن → از `getSuppressed()` استفاده کنید.

## جمع‌بندی
- try-with-resources برای مدیریت خودکار resource.
- چند resource با `;`، close خودکار حتی با exception.
- Suppressed exception برای خطاهای close.

**جلسهٔ بعد:** File I/O.
