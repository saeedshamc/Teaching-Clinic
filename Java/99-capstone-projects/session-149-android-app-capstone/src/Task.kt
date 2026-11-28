// Task.kt — Task entity برای Android capstone
// نیازمند: androidx.room برای کامپایل
// package com.example.taskmanager
//
// import androidx.room.*
//
// @Entity(tableName = "tasks")
// data class Task(
//     @PrimaryKey(autoGenerate = true) val id: Long = 0,
//     val title: String,
//     val description: String,
//     val isCompleted: Boolean = false,
//     val priority: Int = 0,   // 0=low, 1=medium, 2=high
//     val createdAt: Long = System.currentTimeMillis()
// )

// Kotlin data class — uncomment when Room is available
// For Java version, use:
public class Task {
    private Long id;
    private String title;
    private String description;
    private boolean isCompleted;
    private int priority;
    private long createdAt;

    public Task() {}
    public Task(Long id, String title, String description, boolean isCompleted, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.priority = priority;
        this.createdAt = System.currentTimeMillis();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
    public long getCreatedAt() { return createdAt; }
}
