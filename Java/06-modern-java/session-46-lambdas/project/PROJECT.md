# پروژهٔ جلسه ۴۶ — سیستم پردازش متن

یک سیستم پردازش متن بسازید که:
- `TextProcessor` interface با `process(String)`
- چند lambda برای processهای مختلف: uppercase, reverse, word count, remove vowels
- `applyProcessors` که چند processor را پشت سر هم اجرا کند
- در `main` روی متن نمونه تست کنید

نمونه:
```
Input: "Hello World"
Uppercase: "HELLO WORLD"
Reversed: "dlroW olleH"
Word count: 2
No vowels: "Hll Wrld"
```

## صورت پروژه
پاسخ در `solution/TextProcessorDemo.java` — از lambda و functional interface استفاده کنید.
