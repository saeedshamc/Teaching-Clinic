// TaskDao.kt — Room DAO برای Android capstone
// نیازمند: androidx.room برای کامپایل
// package com.example.taskmanager
//
// import androidx.room.*
// import kotlinx.coroutines.flow.Flow
//
// @Dao
// interface TaskDao {
//     @Query("SELECT * FROM tasks ORDER BY priority DESC, createdAt DESC")
//     fun getAllTasks(): Flow<List<Task>>
//
//     @Query("SELECT * FROM tasks WHERE isCompleted = 0")
//     fun getIncompleteTasks(): Flow<List<Task>>
//
//     @Query("SELECT * FROM tasks WHERE id = :id")
//     suspend fun getTaskById(id: Long): Task?
//
//     @Insert(onConflict = OnConflictStrategy.REPLACE)
//     suspend fun insertTask(task: Task): Long
//
//     @Update
//     suspend fun updateTask(task: Task)
//
//     @Delete
//     suspend fun deleteTask(task: Task)
//
//     @Query("DELETE FROM tasks WHERE id = :id")
//     suspend fun deleteTaskById(id: Long)
//
//     @Query("UPDATE tasks SET isCompleted = :completed WHERE id = :id")
//     suspend fun updateTaskStatus(id: Long, completed: Boolean)
// }

// Java version — uncomment when Room is available
// For now, use in-memory storage
public interface TaskDao {
    // Methods will be implemented when Room is available
}
