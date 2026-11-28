# README — پروژهٔ چت real-time

چت کلاینت-سرور با Java Socket خالص — بدون هیچ dependency.

## اجرا

```bash
# ترمینال ۱ — سرور
javac -d out src/ChatServer.java src/ChatClient.java
java -cp out ChatServer

# ترمینال ۲، ۳، ... — کلاینت‌ها
java -cp out ChatClient localhost 5000
java -cp out ChatClient localhost 5000
```

هر کلاینت نام می‌گیرد و پیام‌ها برای همهٔ کلاینت‌های دیگر broadcast می‌شود.

## دستورها
- `/quit` — خروج
- `/list` — تعداد کاربران متصل

## توسعه
`project/PROJECT.md` — نسخهٔ Swing + تاریخچهٔ پیام + اتاق‌های متعدد.
