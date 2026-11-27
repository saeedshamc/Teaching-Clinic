# پروژهٔ جلسه ۲۵ — سیستم مدیریت وظایف

یک سیستم مدیریت وظایف بسازید که:
- `List<Task>` برای نگهداری وظایف
- `Task` با `title`, `priority`, `done` (boolean)
- متدهای `addTask`, `completeTask`, `removeTask`, `displayAll`
- فیلتر بر اساس priority یا وضعیت done
- در `main` چند وظیفه اضافه و مدیریت کنید

نمونه:
```
Tasks:
[ ] Fix bug (HIGH)
[x] Write docs (LOW)
[ ] Review code (MEDIUM)
```

## صورت پروژه
پاسخ در `solution/TaskManager.java` — از `ArrayList` و `for-each` استفاده کنید.
