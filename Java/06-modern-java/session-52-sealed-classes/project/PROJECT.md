# پروژهٔ جلسه ۵۲ — سیستم پیام‌رسانی با Sealed

یک سیستم پیام‌رسانی بسازید که:
- `sealed interface Message permits TextMessage, ImageMessage, FileMessage`
- هر نوع message با record تعریف شود
- `processMessage` با switch expression برای هر نوع
- `sealed interface Priority permits High, Medium, Low` برای اولویت
- در `main` چند پیام بسازید و پردازش کنید

نمونه:
```
TextMessage: "Hello" → Sent
ImageMessage: "photo.jpg" → Sent
FileMessage: "data.zip" → Failed (too large)
```

## صورت پروژه
پاسخ در `solution/MessageSystem.java` — از sealed + record + switch expression استفاده کنید.
