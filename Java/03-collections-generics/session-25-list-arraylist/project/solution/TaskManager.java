// TaskManager.java — پاسخ پروژهٔ جلسه ۲۵
import java.util.ArrayList;
import java.util.List;

class Task {
    String title;
    String priority;
    boolean done;

    Task(String title, String priority) {
        this.title = title;
        this.priority = priority;
        this.done = false;
    }
}

public class TaskManager {

    static List<Task> tasks = new ArrayList<>();

    static void addTask(String title, String priority) {
        tasks.add(new Task(title, priority));
    }

    static void completeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).done = true;
        }
    }

    static void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    static void displayAll() {
        System.out.println("\n=== Tasks ===");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.printf("[%s] %s (%s)%n", t.done ? "x" : " ", t.title, t.priority);
        }
    }

    static void displayByPriority(String priority) {
        System.out.println("\n=== Priority: " + priority + " ===");
        for (Task t : tasks) {
            if (t.priority.equalsIgnoreCase(priority)) {
                System.out.printf("[%s] %s%n", t.done ? "x" : " ", t.title);
            }
        }
    }

    public static void main(String[] args) {
        addTask("Fix bug", "HIGH");
        addTask("Write docs", "LOW");
        addTask("Review code", "MEDIUM");
        addTask("Deploy", "HIGH");

        displayAll();

        completeTask(0);   // Fix bug → done
        completeTask(2);   // Review code → done

        System.out.println("\n=== After completing ===");
        displayAll();

        displayByPriority("HIGH");
    }
}
