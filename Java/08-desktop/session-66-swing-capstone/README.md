# جلسه ۶۶ — Swing Capstone: سیستم مدیریت وظایف

## اهداف یادگیری
- همهٔ مباحث Swing را ترکیب کنید
- یک برنامهٔ کامل و کاربردی بسازید
- `JTable`, `JTree`, `JTabbedPane`, events, layouts را با هم به‌کار ببرید

## پیش‌نیازها
- جلسات ۶۱–۶۵

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### پروژه: Task Manager

یک سیستم مدیریت وظایف بسازید که:

1. **مدل**: `Task` — id, title, description, priority, status, dueDate
2. **UI**:
   - `JTabbedPane` برای تب‌های مختلف (All, Pending, Completed)
   - `JTable` برای نمایش وظایف
   - `JTree` برای دسته‌بندی بر اساس priority
   - `JTextField`/`JTextArea` برای افزودن وظیفه
   - `JComboBox` برای priority
   - `JButton` برای add/complete/delete
3. **Events**: `ActionListener`, `TableModelListener`, `TreeSelectionListener`
4. **Layouts**: `BorderLayout`, `FlowLayout`, `GridLayout`

### معماری

```java
class Task {
    int id;
    String title;
    String description;
    Priority priority;   // HIGH, MEDIUM, LOW
    Status status;       // PENDING, IN_PROGRESS, COMPLETED
    LocalDate dueDate;
}

class TaskManager {
    List<Task> tasks;
    // CRUD operations
}

class TaskManagerUI {
    JFrame frame;
    JTable table;
    JTree tree;
    // ...
}
```

## کد مهم

`src/TaskManagerDemo.java` — پیاده‌سازی کامل.

## جمع‌بندی فاز ۵ (بخش Swing)
- AWT Basics, Swing Components, Layouts, Events, Tables/Trees ✅
- **بعدی: JavaFX — modern Java GUI**
