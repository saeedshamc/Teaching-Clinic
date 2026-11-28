# جلسه ۳۵ — NIO Files و Channels (پیشرفته)

## اهداف یادگیری
- `Files.newBufferedReader`/`newBufferedWriter` را بشناسید
- `FileChannel` برای transfer و position را بفهمید
- `Files.copy` با `CopyOption` را به‌کار ببرید
- memory-mapped files را مقدماتی بشناسید

## پیش‌نیازها
- جلسات ۳۱–۳۴

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Files.newBufferedReader/Writer

```java
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

// خواندن با BufferedReader — کارآمدتر از readAllLines برای فایل بزرگ
try (BufferedReader br = Files.newBufferedReader(Paths.get("big.txt"), StandardCharsets.UTF_8)) {
    String line;
    while ((line = br.readLine()) != null) {
        // پردازش خط
    }
}

// نوشتن با BufferedWriter
try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("out.txt"))) {
    bw.write("Hello\n");
    bw.newLine();
}
```

### FileChannel — انتقال و position

```java
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

try (FileChannel channel = FileChannel.open(Paths.get("data.bin"),
        StandardOpenOption.READ, StandardOpenOption.WRITE)) {
    // خواندن در ByteBuffer
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    int bytesRead = channel.read(buffer);
    // نوشتن
    buffer.flip();
    channel.write(buffer);
    // position
    channel.position(0);   // به اول برگرد
    channel.size();        // حجم فایل
}
```

### Files.copy با options

```java
Files.copy(source, target);                                    // کپی ساده
Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);  // بازنویسی
Files.copy(source, target, StandardCopyOption.COPY_ATTRIBUTES);  // با metadata
Files.move(source, target, StandardCopyOption.ATOMIC_MOVE);      // atomic
```

### Memory-mapped file (مقدماتی)

```java
try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
    MappedByteBuffer buffer = channel.map(
        FileChannel.MapMode.READ_ONLY, 0, channel.size());
    // buffer را مثل آرایه بخوانید — بدون بارگذاری کل فایل در حافظه
}
```

## کد مهم

`src/NioFilesDemo.java` — newBufferedReader/Writer، FileChannel، Files.copy options.

## خطاهای رایج
- `readAllLines` روی فایل خیلی بزرگ → `OutOfMemoryError` — از `newBufferedReader` استفاده کنید.
- `ByteBuffer` را فراموش کردن `flip()` قبل از write → دادهٔ اشتباه.
- `FileChannel` را close نکردن → resource leak — با try-with-resources.
- `Files.copy` بدون `REPLACE_EXISTING` روی فایل موجود → `FileAlreadyExistsException`.

## جمع‌بندی
- `Files.newBufferedReader/Writer` برای فایل بزرگ.
- `FileChannel` برای انتقال، position، memory-mapped.
- `Files.copy/move` با options برای کنترل.

**جلسهٔ بعد:** Serialization.
