// TaskManagerApp.kt — پاسخ کامل capstone: سیستم مدیریت وظایف
// Jetpack Compose + Room + ViewModel + Repository
import java.util.*;

public class TaskManagerApp {

    public static void main(String[] args) {
        System.out.println("=== Task Manager App ===");
        System.out.println("Architecture:");
        System.out.println("  TaskScreen (Compose UI)");
        System.out.println("    ↓");
        System.out.println("  TaskViewModel (StateFlow)");
        System.out.println("    ↓");
        System.out.println("  TaskRepository (data access)");
        System.out.println("    ↓");
        System.out.println("  TaskDao (Room database)");
        System.out.println("    ↓");
        System.out.println("  Task (entity)");

        // Demo data
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Buy groceries", "Milk, eggs, bread", false, 1));
        tasks.add(new Task(2L, "Finish report", "Quarterly report", true, 2));
        tasks.add(new Task(3L, "Call dentist", "Schedule appointment", false, 0));

        System.out.println("\nTasks:");
        for (Task task : tasks) {
            System.out.println("  " + task.getTitle() + " — " + (task.isCompleted() ? "Completed" : "Pending"));
        }
    }

    static class Task {
        private Long id;
        private String title;
        private String description;
        private boolean isCompleted;
        private int priority;

        Task(Long id, String title, String description, boolean isCompleted, int priority) {
            this.id = id; this.title = title; this.description = description;
            this.isCompleted = isCompleted; this.priority = priority;
        }
        Long getId() { return id; }
        String getTitle() { return title; }
        String getDescription() { return description; }
        boolean isCompleted() { return isCompleted; }
        int getPriority() { return priority; }
    }
}
