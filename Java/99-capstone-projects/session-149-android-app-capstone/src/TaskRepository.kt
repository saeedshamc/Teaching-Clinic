// TaskRepository.kt — Repository pattern برای Android capstone
// نیازمند: androidx.room برای کامپایل
// package com.example.taskmanager
//
// import kotlinx.coroutines.flow.Flow
//
// class TaskRepository(private val taskDao: TaskDao) {
//
//     fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()
//
//     fun getIncompleteTasks(): Flow<List<Task>> = taskDao.getIncompleteTasks()
//
//     suspend fun getTaskById(id: Long): Task? = taskDao.getTaskById(id)
//
//     suspend fun insertTask(task: Task): Long = taskDao.insertTask(task)
//
//     suspend fun updateTask(task: Task) = taskDao.updateTask(task)
//
//     suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
//
//     suspend fun deleteTaskById(id: Long) = taskDao.deleteTaskById(id)
//
//     suspend fun updateTaskStatus(id: Long, completed: Boolean) =
//         taskDao.updateTaskStatus(id, completed)
// }

// Java version — uncomment when Room is available
public class TaskRepository {
    // Repository implementation will be added when Room is available
}
