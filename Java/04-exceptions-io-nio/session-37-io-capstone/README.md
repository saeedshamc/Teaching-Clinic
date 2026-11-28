# جلسه ۳۷ — مینی‌پروژهٔ جمع‌بندی: سیستم مدیریت فایل‌های متنی

## اهداف یادگیری
- همهٔ مباحث فاز ۳ (Exception، IO، NIO، Serialization) را ترکیب کنید
- یک ابزار واقعی برای مدیریت فایل‌های متنی بسازید

## پیش‌نیازها
- جلسات ۳۱–۳۶

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### پروژه: TextFileManager

یک ابزار کنسولی برای مدیریت فایل‌های متنی بسازید که:

1. **مدل**: `TextFile` — path، name، size، lastModified
2. **عملیات**:
   - لیست فایل‌های `.txt` در یک پوشه
   - خواندن محتوای فایل (با BufferedReader)
   - نوشتن/append به فایل (با BufferedWriter)
   - جستجوی متن در فایل‌ها
   - آمار: تعداد خط، کلمه، کاراکتر
   - serialization برای ذخیرهٔ تنظیمات (مثل last directory)
3. **خطاها**: مدیریت `IOException` با try-with-resources و custom exception

### معماری

```java
class TextFile {
    Path path;
    String name;
    long size;
    // ...
}

class TextFileManager {
    Path directory;
    List<TextFile> files;
    // ...
}
```

## کد مهم

`src/TextFileManager.java` — پیاده‌سازی کامل.

## خطاهای رایج
- `Files.walk` را close نکردن → resource leak — با try-with-resources.
- `readAllLines` روی فایل بزرگ → `OutOfMemoryError`.
- فراموش کردن `StandardCharsets.UTF_8` → encoding issues.

## جمع‌بندی فاز ۳ (بخش IO)
- Exception: try-catch-finally، throw/throws، custom ✅
- IO: File I/O، try-with-resources، NIO.2، Serialization ✅
- **بعدی: Concurrency — Thread، synchronized، Executor**
