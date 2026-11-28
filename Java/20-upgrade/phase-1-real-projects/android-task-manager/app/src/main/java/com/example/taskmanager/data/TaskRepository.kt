// TaskRepository.kt — Repository pattern
package com.example.taskmanager.data

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {

    val allTasks: Flow<List<Task>> = dao.getAllTasks()
    val incompleteCount: Flow<Int> = dao.incompleteCount()

    suspend fun insert(task: Task) = dao.insert(task)
    suspend fun update(task: Task) = dao.update(task)
    suspend fun delete(task: Task) = dao.delete(task)
    suspend fun setCompleted(id: Long, completed: Boolean) = dao.setCompleted(id, completed)
    suspend fun getById(id: Long) = dao.getById(id)
}
