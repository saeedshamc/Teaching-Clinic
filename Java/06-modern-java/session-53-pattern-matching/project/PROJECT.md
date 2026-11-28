# پروژهٔ جلسه ۵۳ — سیستم پردازش رویداد با Pattern Matching

یک سیستم پردازش رویداد بسازید که:
- `sealed interface Event permits ClickEvent, KeyEvent, MouseEvent`
- هر رویداد با record تعریف شود
- `handleEvent` با instanceof pattern برای هر نوع
- `sealed interface Result permits Success, Failure` برای نتیجه
- در `main` چند رویداد بسازید و پردازش کنید

نمونه:
```
ClickEvent at (100,200) → Handled
KeyEvent 'a' → Handled
MouseEvent moved → Ignored
```

## صورت پروژه
پاسخ در `solution/EventHandler.java` — از sealed + record + instanceof pattern استفاده کنید.
