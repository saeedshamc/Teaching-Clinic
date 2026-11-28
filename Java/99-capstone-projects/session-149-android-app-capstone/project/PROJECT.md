# پروژهٔ جلسه ۱۴۹ — اپلیکیشن مدیریت وظایف با Jetpack Compose

یک اپلیکیشن مدیریت وظایف با Jetpack Compose بسازید:
- [ ] `Task` entity با `id`, `title`, `description`, `isCompleted`, `priority`
- [ ] `TaskDao` با Room برای local database
- [ ] `TaskRepository` با repository pattern
- [ ] `TaskViewModel` با `StateFlow` برای UI state
- [ ] `TaskScreen` با `LazyColumn`, `FAB`, `Checkbox`
- [ ] `AddTaskScreen` برای create/edit task
- [ ] `TaskDetailScreen` برای view task details

نمونه:
```
TaskScreen:
  - LazyColumn with task items
  - FAB for add task
  - Checkbox for complete/incomplete
  - Priority indicator

AddTaskScreen:
  - Title field
  - Description field
  - Priority selector
  - Save/Cancel buttons
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
