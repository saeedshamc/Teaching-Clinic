# پروژهٔ جلسه ۲۲ — سیستم وضعیت تسک

یک سیستم مدیریت تسک بسازید که:
- enum `TaskStatus` با `TODO`, `IN_PROGRESS`, `REVIEW`, `DONE` و label فارسی
- enum `Priority` با `LOW`, `MEDIUM`, `HIGH`, `URGENT` و code
- کلاس `Task` با `title`, `status`, `priority` و متد `advance()` که status را جلو می‌برد
- در `main` تسک بسازید و status را advance کنید

نمونه:
```
Task: Fix bug — TODO → IN_PROGRESS → REVIEW → DONE
```

## صورت پروژه
پاسخ در `solution/TaskSystem.java` — از enum با فیلد و switch استفاده کنید.
