# جلسه ۹۰ — Jetpack Capstone: سیستم مدیریت وظایف

## اهداف یادگیری
- همهٔ مباحث Jetpack را ترکیب کنید
- MVVM architecture با Room, Retrofit, WorkManager, Navigation را به‌کار ببرید
- `ViewModel`, `LiveData`, `Repository`, `Dao` را با هم استفاده کنید

## پیش‌نیازها
- جلسات ۸۲–۸۹

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### پروژه: Task Manager Jetpack

یک سیستم مدیریت وظایف بسازید که:

1. **Model**: `Task` — id, title, description, priority, status, dueDate
2. **Room**: `TaskDao`, `TaskDatabase` برای local storage
3. **Repository**: `TaskRepository` برای data layer
4. **ViewModel**: `TaskViewModel` با `LiveData`
5. **UI**: `TaskActivity` با RecyclerView, Navigation, WorkManager

### معماری

```
UI (Activity/Fragment)
    ↓ observes
ViewModel (TaskViewModel)
    ↓ uses
Repository (TaskRepository)
    ↓ calls
Room (TaskDao) + Retrofit (ApiService)
    ↓
Local DB + Remote API
```

## کد مهم

`src/Task.java` — entity.
`src/TaskDao.java` — DAO.
`src/TaskDatabase.java` — database.
`src/TaskRepository.java` — repository.
`src/TaskViewModel.java` — ViewModel.
`src/TaskActivity.java` — Activity.

## جمع‌بندی فاز ۶ (بخش Jetpack)
- Setup, ViewModel/LiveData, Room, Retrofit, WorkManager, Navigation, Coroutines/Flow, Hilt/DataStore ✅
- **بعدی: Android Advanced — Services, Broadcast, Permissions, Notifications**
