// TaskViewModel.kt — ViewModel با StateFlow برای Android capstone
// نیازمند: androidx.lifecycle برای کامپایل
// package com.example.taskmanager
//
// import androidx.lifecycle.*
// import kotlinx.coroutines.flow.*
// import kotlinx.coroutines.launch
//
// class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
//
//     private val _uiState = MutableStateFlow(TaskUiState())
//     val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()
//
//     val tasks: StateFlow<List<Task>> = repository.getAllTasks()
//         .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
//
//     fun addTask(title: String, description: String, priority: Int) {
//         viewModelScope.launch {
//             val task = Task(title = title, description = description, priority = priority)
//             repository.insertTask(task)
//         }
//     }
//
//     fun updateTask(task: Task) {
//         viewModelScope.launch {
//             repository.updateTask(task)
//         }
//     }
//
//     fun deleteTask(task: Task) {
//         viewModelScope.launch {
//             repository.deleteTask(task)
//         }
//     }
//
//     fun toggleTaskStatus(task: Task) {
//         viewModelScope.launch {
//             repository.updateTaskStatus(task.id, !task.isCompleted)
//         }
//     }
// }
//
// data class TaskUiState(
//     val isLoading: Boolean = false,
//     val error: String? = null,
//     val searchQuery: String = ""
// )

// Java version — uncomment when ViewModel is available
public class TaskViewModel {
    // ViewModel implementation will be added when Android is available
}
