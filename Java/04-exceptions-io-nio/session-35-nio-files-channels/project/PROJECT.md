# پروژهٔ جلسه ۳۵ — کپی باینری

یک برنامه بنویسید که:
- یک فایل باینری را با `FileChannel` بخواند
- به فایل دیگر با `FileChannel` بنویسد
- حجم فایل مبدأ و مقصد را مقایسه کند
- با `ByteBuffer` در chunkهای 8KB کپی کند

نمونه:
```
Source: data.bin (1024 bytes)
Copied: 1024 bytes
Target: data_copy.bin (1024 bytes)
```

## صورت پروژه
پاسخ در `solution/BinaryCopier.java` — از `FileChannel` و `ByteBuffer` استفاده کنید.
