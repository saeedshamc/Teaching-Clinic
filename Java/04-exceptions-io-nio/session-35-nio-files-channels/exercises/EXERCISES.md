# تمرین‌های جلسه ۳۵ — NIO Files و Channels

## آسان
1. `Files.newBufferedReader` با `StandardCharsets.UTF_8` تست کنید.
2. `Files.copy` با `REPLACE_EXISTING` تست کنید.

## متوسط
3. `FileChannel` برای نوشتن و خواندن `ByteBuffer` را پیاده کنید.
4. `channel.position()` و `channel.size()` را تست کنید.

## سخت
5. `Files.move` با `ATOMIC_MOVE` را تست کنید.
6. `MappedByteBuffer` برای خواندن فایل بزرگ را مقدماتی امتحان کنید.
