# پاسخ تمرین‌های Android Task Manager

## آسان

**۱–۳.** ساختار آماده است: `MainActivity → NavHost → TaskListScreen`.
داده در `TaskDatabase` (Room) ذخیره می‌شود و بعد از restart باقی می‌ماند.

## متوسط

**۴.** ترتیب در `TaskDao.getAllTasks()`: `ORDER BY priority DESC, createdAt DESC`.
**۵.** `onTaskClick` → `navController.navigate("edit/$id")` → `TaskEditScreen`
که با `taskId` داده را از DAO می‌خواند.
**۶.** `incompleteCount` در ViewModel از `dao.incompleteCount()` می‌آید و در
`TopAppBar` نمایش داده می‌شود.

## سخت

**۷. جستجو:**
```kotlin
// TaskDao
@Query("SELECT * FROM tasks WHERE title LIKE '%' || :q || '%' ORDER BY priority DESC")
fun search(q: String): Flow<List<Task>>

// ViewModel
private val query = MutableStateFlow("")
val tasks = query.flatMapLatest { repo.search(it) }
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
```

**۸. WorkManager:**
```kotlin
class ReminderWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {
        val count = dao.incompleteCount().first()
        // NotificationCompat Builder → show notification
        return Result.success()
    }
}
// زمان‌بندی:
PeriodicWorkRequestBuilder<ReminderWorker>(1, TimeUnit.DAYS).build()
WorkManager.getInstance(context).enqueueUniquePeriodicWork("daily", KEEP, request)
```

**۹. تم:**
```kotlin
val dark = isSystemInDarkTheme() // یا state var برای override
MaterialTheme(colorScheme = if (dark) darkColorScheme() else lightColorScheme())
```
