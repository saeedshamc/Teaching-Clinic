// TaskDao.java — Room DAO برای tasks
// نیازمند Android SDK
package com.example.myapp;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM tasks ORDER BY dueDate ASC")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM tasks WHERE status = :status")
    LiveData<List<Task>> getTasksByStatus(String status);

    @Query("SELECT * FROM tasks WHERE priority = :priority")
    LiveData<List<Task>> getTasksByPriority(String priority);

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM tasks WHERE id = :id")
    void deleteById(int id);
}
