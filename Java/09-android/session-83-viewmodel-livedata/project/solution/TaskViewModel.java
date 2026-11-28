// TaskViewModel.java — پاسخ پروژهٔ جلسه ۸۳
// نیازمند Android SDK
package com.example.myapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class TaskViewModel extends ViewModel {

    public static class Task {
        String title, description, priority, status;
        Task(String title, String description, String priority) {
            this.title = title; this.description = description;
            this.priority = priority; this.status = "PENDING";
        }
    }

    private MutableLiveData<List<Task>> tasks = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>(false);

    public LiveData<List<Task>> getTasks() { return tasks; }
    public LiveData<String> getError() { return error; }
    public LiveData<Boolean> getLoading() { return loading; }

    public void loadTasks() {
        loading.setValue(true);
        List<Task> list = new ArrayList<>();
        list.add(new Task("Write report", "Q4 report", "HIGH"));
        list.add(new Task("Fix bug", "Login issue", "MEDIUM"));
        list.add(new Task("Update docs", "API docs", "LOW"));
        tasks.setValue(list);
        loading.setValue(false);
    }

    public void addTask(String title, String description, String priority) {
        List<Task> current = tasks.getValue();
        if (current != null) {
            current.add(new Task(title, description, priority));
            tasks.setValue(current);
        }
    }

    public void completeTask(int position) {
        List<Task> current = tasks.getValue();
        if (current != null && position >= 0 && position < current.size()) {
            current.get(position).status = "COMPLETED";
            tasks.setValue(current);
        }
    }

    public void deleteTask(int position) {
        List<Task> current = tasks.getValue();
        if (current != null && position >= 0 && position < current.size()) {
            current.remove(position);
            tasks.setValue(current);
        }
    }
}
