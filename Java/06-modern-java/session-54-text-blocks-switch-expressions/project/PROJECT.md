# پروژهٔ جلسه ۵۴ — سیستم قالب‌سازی با Text Blocks و Switch

یک سیستم قالب‌سازی بسازید که:
- `record Template(String name, String content)` با text block
- `render` متد که placeholders را با `formatted()` پر کند
- `switch expression` برای انتخاب template بر اساس type
- چند template (welcome, error, success) تعریف کنید
- در `main` خروجی را چاپ کنید

نمونه:
```
Template: welcome
Output:
<html>
    <body>
        <h1>Welcome, Ali!</h1>
        <p>Your account is ready.</p>
    </body>
</html>
```

## صورت پروژه
پاسخ در `solution/TemplateEngine.java` — از text blocks و switch expressions استفاده کنید.
