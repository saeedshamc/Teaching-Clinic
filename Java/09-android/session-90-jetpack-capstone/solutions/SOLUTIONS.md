# پاسخ تمرین‌های جلسه ۹۰

## آسان

**۱ و ۲.**
```java
@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
}

@Dao
public interface TaskDao {
    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> getAllTasks();
}
```

## متوسط

**۳ و ۴.**
```java
public class TaskRepository {
    private TaskDao dao;
    public LiveData<List<Task>> getAllTasks() { return dao.getAllTasks(); }
    public void insert(Task task) {
        Executors.newSingleThreadExecutor().execute(() -> dao.insert(task));
    }
}

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository repository;
    private LiveData<List<Task>> tasks;
}
```

## سخت

**۵ و ۶.**
```java
viewModel.getTasks().observe(this, tasks -> {
    adapter.updateTasks(tasks);
});

WorkRequest work = new OneTimeWorkRequest.Builder(SyncWorker.class).build();
WorkManager.getInstance(context).enqueue(work);
```
