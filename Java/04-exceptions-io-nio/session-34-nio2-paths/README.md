# جلسه ۳۴ — NIO.2: Paths و Files

## اهداف یادگیری
- `Path` و `Paths` را برای کار با مسیرها بشناسید
- `Files` utility class را برای خواندن/نوشتن فایل‌ها به‌کار ببرید
- path operations (resolve, normalize, relativize) را یاد بگیرید
- directory traversal را بفهمید

## پیش‌نیازها
- جلسات ۳۱–۳۳

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Path و Paths

```java
import java.nio.file.Path;
import java.nio.file.Paths;

Path path = Paths.get("src", "main", "file.txt");   // چند بخش
System.out.println(path);                            // src/main/file.txt
System.out.println(path.getFileName());              // file.txt
System.out.println(path.getParent());                // src/main
System.out.println(path.getNameCount());             // 3
System.out.println(path.getName(0));                 // src
```

### path operations

```java
Path base = Paths.get("/home/user");
Path file = Paths.get("docs", "readme.txt");

Path resolved = base.resolve(file);           // /home/user/docs/readme.txt
Path normalized = Paths.get("/a/b/../c").normalize();   // /a/c
Path relativized = Paths.get("/a/b").relativize(Paths.get("/a/c"));  // ../c

System.out.println(path.startsWith("src"));   // true/false
System.out.println(path.endsWith(".txt"));    // true/false
System.out.println(path.isAbsolute());        // true/false
```

### Files utility — خواندن/نوشتن

```java
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

Path path = Paths.get("data.txt");

// نوشتن
Files.write(path, "Hello World".getBytes());
Files.write(path, "Line1\nLine2\n".getBytes(), StandardOpenOption.APPEND);

// خواندن همه
byte[] bytes = Files.readAllBytes(path);
String content = new String(bytes);

// خواندن خط به خط
List<String> lines = Files.readAllLines(path);
for (String line : lines) System.out.println(line);

// خواندن با charset
List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
```

### Files.exists و metadata

```java
System.out.println(Files.exists(path));
System.out.println(Files.isDirectory(path));
System.out.println(Files.isRegularFile(path));
System.out.println(Files.size(path));   // حجم
Files.delete(path);                      // حذف
Files.createDirectory(path);             // ساخت پوشه
Files.createDirectories(path);           // ساخت چند سطح
```

## کد مهم

`src/NioPathsDemo.java` — Path operations، Files.read/write، metadata.

## خطاهای رایج
- `Paths.get` با `null` → `NullPointerException`.
- `Files.readAllLines` روی فایل بزرگ → `OutOfMemoryError` — برای فایل بزرگ از `BufferedReader`.
- `normalize()` قبل از `resolve()` → مسیر اشتباه.
- `Files.delete` روی فایل non-empty directory → `DirectoryNotEmptyException`.

## جمع‌بندی
- `Path` برای مسیر، `Files` برای عملیات فایل.
- `resolve`/`normalize`/`relativize` برای path manipulation.
- `Files.readAllLines` برای خواندن ساده، `Files.write` برای نوشتن.

**جلسهٔ بعد:** NIO Files و Channels پیشرفته.
