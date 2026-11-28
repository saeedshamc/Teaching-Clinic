# پروژهٔ جلسه ۸۳ — سیستم مدیریت وظایف با ViewModel

یک سیستم مدیریت وظایف بسازید که:
- `TaskViewModel` با `LiveData` برای tasks
- `Task` model با title, description, priority, status
- `TaskAdapter` برای RecyclerView
- `TaskActivity` با observe برای UI
- `addTask`, `completeTask`, `deleteTask` در ViewModel
- `Loading` و `Error` LiveData برای status

نمونه:
```
[Tasks: 3]
[Task 1: Write report — HIGH — PENDING]
[Task 2: Fix bug — MEDIUM — IN_PROGRESS]
```

## صورت پروژه
پاسخ در `solution/` — TaskViewModel.java + TaskActivity.java + TaskAdapter.java.
