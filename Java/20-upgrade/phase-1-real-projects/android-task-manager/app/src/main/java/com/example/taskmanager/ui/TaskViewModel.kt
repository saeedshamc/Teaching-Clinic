// TaskViewModel.kt — ViewModel با StateFlow
package com.example.taskmanager.ui

import androidx.lifecycle.*
import com.example.taskmanager.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TaskViewModel(app: Application) : AndroidViewModel(app) {

    private val db = TaskDatabase.getInstance(app)
    private val repo = TaskRepository(db.taskDao())

    val tasks: StateFlow<List<Task>> = repo.allTasks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val incompleteCount: StateFlow<Int> = repo.incompleteCount
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun addTask(title: String, description: String, priority: Int) {
        viewModelScope.launch {
            repo.insert(Task(title = title, description = description, priority = priority))
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch { repo.update(task) }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch { repo.delete(task) }
    }

    fun toggleComplete(task: Task) {
        viewModelScope.launch { repo.setCompleted(task.id, !task.isCompleted) }
    }
}
