# پاسخ تمرین‌های جلسه ۶۶

## آسان

**۱ و ۲.**
```java
class Task {
    int id; String title; String priority; String status;
}
DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Title", "Priority"}, 0);
model.addRow(new Object[]{1, "Task1", "HIGH"});
```

## متوسط

**۳ و ۴.**
```java
void addTask(Task t) { tasks.add(t); refreshTable(); }
void completeTask(int id) { tasks.get(id).status = "COMPLETED"; refreshTable(); }
void deleteTask(int id) { tasks.remove(id); refreshTable(); }

// Tree filter
tree.addTreeSelectionListener(e -> {
    String filter = node.getUserObject().toString();
    // filter tasks by priority
});
```

## سخت

**۵ و ۶.**
```java
JTabbedPane tabs = new JTabbedPane();
tabs.addTab("All", allPanel);
tabs.addTab("Pending", pendingPanel);
tabs.addTab("Completed", completedPanel);

tasks.sort((a, b) -> a.dueDate.compareTo(b.dueDate));
```
