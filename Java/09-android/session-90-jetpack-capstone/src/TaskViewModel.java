// TaskViewModel.java — ViewModel برای tasks
// نیازمند Android SDK
package com.example.myapp;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository repository;
    private LiveData<List<Task>> tasks;
    private MutableLiveData<String> error = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>(false);

    public TaskViewModel(Application application) {
        super(application);
        repository = TaskRepository.getInstance(application);
        tasks = repository.getAllTasks();
    }

    public LiveData<List<Task>> getTasks() { return tasks; }
    public LiveData<String> getError() { return error; }
    public LiveData<Boolean> getLoading() { return loading; }

    public void addTask(Task task) {
        loading.setValue(true);
        repository.insert(task);
        loading.setValue(false);
    }

    public void updateTask(Task task) {
        repository.update(task);
    }

    public void deleteTask(Task task) {
        repository.delete(task);
    }

    public void deleteTaskById(int id) {
        repository.deleteById(id);
    }

    public void showError(String message) {
        error.setValue(message);
    }
}
