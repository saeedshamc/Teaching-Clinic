# پاسخ تمرین‌های جلسه ۱۴۹

## آسان

**۱ و ۲.**
```kotlin
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val priority: Int = 0
)

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Insert
    suspend fun insertTask(task: Task): Long

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}
```

## متوسط

**۳ و ۴.**
```kotlin
class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    val tasks: StateFlow<List<Task>> = repository.getAllTasks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            repository.insertTask(Task(title = title, description = description))
        }
    }
}

class TaskRepository(private val taskDao: TaskDao) {
    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()
    suspend fun insertTask(task: Task) = taskDao.insertTask(task)
}
```

## سخت

**۵ و ۶.**
```kotlin
@Composable
fun TaskScreen(viewModel: TaskViewModel = viewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /* navigate */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->
        LazyColumn {
            items(tasks) { task ->
                TaskItem(task, onClick = { /* navigate */ })
            }
        }
    }
}
```
