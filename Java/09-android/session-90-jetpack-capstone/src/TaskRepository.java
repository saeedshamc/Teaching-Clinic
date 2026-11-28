// TaskRepository.java — data layer برای tasks
// نیازمند Android SDK
package com.example.myapp;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executors;

public class TaskRepository {

    private TaskDao dao;
    private static TaskRepository instance;

    private TaskRepository(Context context) {
        dao = TaskDatabase.getInstance(context).taskDao();
    }

    public static synchronized TaskRepository getInstance(Context context) {
        if (instance == null) instance = new TaskRepository(context);
        return instance;
    }

    public LiveData<List<Task>> getAllTasks() { return dao.getAllTasks(); }
    public LiveData<List<Task>> getTasksByStatus(String status) { return dao.getTasksByStatus(status); }
    public LiveData<List<Task>> getTasksByPriority(String priority) { return dao.getTasksByPriority(priority); }

    public void insert(Task task) {
        Executors.newSingleThreadExecutor().execute(() -> dao.insert(task));
    }

    public void update(Task task) {
        Executors.newSingleThreadExecutor().execute(() -> dao.update(task));
    }

    public void delete(Task task) {
        Executors.newSingleThreadExecutor().execute(() -> dao.delete(task));
    }

    public void deleteById(int id) {
        Executors.newSingleThreadExecutor().execute(() -> dao.deleteById(id));
    }
}
