# راهنمای پاسخ — Task Manager توسعه‌یافته

## یادآوری/ددلاین
```kotlin
// Task.kt — ستون‌های جدید
val dueDate: Long? = null,        // epoch millis
val reminderEnabled: Boolean = false

// migration در TaskDatabase companion:
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE tasks ADD COLUMN dueDate INTEGER")
        db.execSQL("ALTER TABLE tasks ADD COLUMN reminderEnabled INTEGER NOT NULL DEFAULT 0")
    }
}
// .addMigrations(MIGRATION_1_2)
```

## دسته‌بندی
```kotlin
val category: String = "general"   // work/personal/shopping
```
فیلتر با Chips در بالای لیست → در ViewModel یک `selectedCategory` StateFlow.

## آمار
```kotlin
// در DAO
@Query("SELECT category, COUNT(*) FROM tasks GROUP BY category")
fun statsByCategory(): Flow<List<CategoryCount>>
// CategoryCount را به‌صورت data class با @Embedded یا دو ستون ساده برگردان.
// UI: Canvas با drawArc برای نمودار دایره‌ای.
```

## تکمیل گروهی
```kotlin
// selectedIds: MutableState<Set<Long>> در ViewModel
fun toggleSelection(id: Long) { selectedIds.update { if (id in it) it - id else it + id } }
fun completeSelected() { viewModelScope.launch { selectedIds.value.forEach { repo.setCompleted(it, true) } } }
```

## Share
```kotlin
val intent = Intent(Intent.ACTION_SEND).apply {
    type = "text/plain"
    putExtra(Intent.EXTRA_TEXT, "وظیفه: ${task.title}\n${task.description}")
}
context.startActivity(Intent.createChooser(intent, "Share"))
```
