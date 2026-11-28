# پاسخ تمرین‌های جلسه ۳۵

## آسان

**۱.**
```java
try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
    String line; while ((line = br.readLine()) != null) System.out.println(line);
}
```

**۲.**
```java
Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
```

## متوسط

**۳.**
```java
try (FileChannel ch = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE)) {
    ByteBuffer buf = ByteBuffer.allocate(100);
    buf.put("Hello".getBytes());
    buf.flip();
    ch.write(buf);
    ch.position(0);
    ch.read(buf);
    buf.flip();
}
```

**۴.**
```java
System.out.println(ch.position());   // موقعیت فعلی
System.out.println(ch.size());       // حجم فایل
ch.position(0);                      // به اول برگرد
```

## سخت

**۵.**
```java
Files.move(source, target, StandardCopyOption.ATOMIC_MOVE);
```

**۶.**
```java
try (FileChannel ch = FileChannel.open(path, StandardOpenOption.READ)) {
    MappedByteBuffer buf = ch.map(FileChannel.MapMode.READ_ONLY, 0, ch.size());
    // buf را مثل آرایه بخوانید
}
```
