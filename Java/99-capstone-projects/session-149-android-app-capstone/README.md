# جلسه ۱۴۹ — Android App Capstone: پروژهٔ نهایی اندروید

## اهداف یادگیری
- همهٔ مباحث Android را در یک پروژهٔ واقعی ترکیب کنید
- Jetpack Compose + ViewModel + Room + Retrofit + Navigation را با هم استفاده کنید
- لایه‌بندی صحیح و best practices را در نظر بگیرید

## پیش‌نیازها
- جلسات ۷۶–۹۵ (Android)

## مدت زمان پیشنهادی
۱۲۰ دقیقه

## درس

### پروژه: Task Manager App

یک اپلیکیشن مدیریت وظایف با Jetpack Compose بسازید:

**Features:**
- `Task` model با `id`, `title`, `description`, `isCompleted`, `priority`
- `TaskViewModel` با `StateFlow` برای UI state
- `TaskRepository` برای data access
- `TaskDao` با Room برای local database
- `TaskScreen` با Compose UI — LazyColumn, FAB, dialogs
- `AddTaskScreen` برای create/edit task
- `TaskDetailScreen` برای view task details

### Architecture

```
TaskScreen (Compose UI)
    ↓
TaskViewModel (StateFlow)
    ↓
TaskRepository (data access)
    ↓
TaskDao (Room database)
    ↓
Task (entity)
```

### Jetpack Compose Components

```kotlin
// TaskScreen
@Composable
fun TaskScreen(viewModel: TaskViewModel = viewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /* navigate to add */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->
        LazyColumn {
            items(tasks) { task ->
                TaskItem(task = task, onClick = { /* navigate to detail */ })
            }
        }
    }
}
```

## کد مهم

`src/Task.kt` — entity model.
`src/TaskDao.kt` — Room DAO.
`src/TaskViewModel.kt` — ViewModel با StateFlow.
`src/TaskRepository.kt` — repository pattern.
`src/TaskScreen.kt` — Compose UI.
`src/AddTaskScreen.kt` — add/edit screen.

## جمع‌بندی فاز ۱۰ (بخش Capstone)
- Desktop App Capstone ✅
- Android App Capstone ✅
- **بعدی: REST Backend Capstone**
