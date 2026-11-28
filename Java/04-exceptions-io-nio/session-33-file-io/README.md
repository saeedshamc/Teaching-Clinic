# جلسه ۳۳ — File I/O (java.io)

## اهداف یادگیری
- `File` class را برای metadata و operations بشناسید
- `FileReader`/`FileWriter` و `BufferedReader`/`BufferedWriter` را به‌کار ببرید
- `PrintWriter` برای خروجی ساده فایل را بفهمید
- directory operations را یاد بگیرید

## پیش‌نیازها
- جلسات ۳۱ و ۳۲

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### File class — metadata و operations

```java
import java.io.File;

File f = new File("data.txt");
System.out.println(f.exists());        // true/false
System.out.println(f.isFile());        // فایل است؟
System.out.println(f.isDirectory());   // پوشه است؟
System.out.println(f.length());        // حجم به بایت
System.out.println(f.getName());       // data.txt
System.out.println(f.getAbsolutePath());
f.createNewFile();                     // ساخت فایل خالی
f.delete();                            // حذف
f.mkdir();                             // ساخت پوشه
f.listFiles();                         // لیست فایل‌های داخل
```

### FileReader/FileWriter — کاراکتر

```java
import java.io.FileReader;
import java.io.FileWriter;

// خواندن کاراکتر به کاراکتر
FileReader fr = new FileReader("file.txt");
int c;
while ((c = fr.read()) != -1) {
    System.out.print((char) c);
}
fr.close();

// نوشتن
FileWriter fw = new FileWriter("out.txt");
fw.write("Hello World");
fw.close();
```

### BufferedReader/BufferedWriter — خط به خط

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

// خواندن خط به خط — سریع‌تر
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
}

// نوشتن خط به خط
try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"))) {
    bw.write("Line 1\n");
    bw.newLine();   // خط جدید platform-independent
    bw.write("Line 2\n");
}
```

### PrintWriter — ساده‌ترین

```java
import java.io.PrintWriter;

try (PrintWriter pw = new PrintWriter("out.txt")) {
    pw.println("Hello");
    pw.printf("%s is %d years old%n", "Ali", 20);
    pw.print("No newline");
}
```

## کد مهم

`src/FileIODemo.java` — File metadata، FileReader/Writer، BufferedReader/Writer، PrintWriter.

## خطاهای رایج
- `FileNotFoundException` → فایل وجود ندارد؛ قبلش `exists()` چک کنید.
- `IOException` روی close → با try-with-resources حل می‌شود.
- `readLine()` روی فایل binary → کاراکترهای عجیب — برای binary از NIO/streams.
- `FileWriter` بدون `append` → فایل قبلی overwrite می‌شود.

## جمع‌بندی
- `File` برای metadata، `BufferedReader/Writer` برای خط به خط.
- try-with-resources برای close خودکار.
- `PrintWriter` برای خروجی ساده.

**جلسهٔ بعد:** NIO.2 Paths و Files.
