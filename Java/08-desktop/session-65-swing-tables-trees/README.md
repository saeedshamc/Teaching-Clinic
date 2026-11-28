# جلسه ۶۵ — Swing Tables و Trees

## اهداف یادگیری
- `JTable` با `TableModel` را بشناسید
- `JTree` با `TreeModel` را بفهمید
- `DefaultTableModel` و `DefaultTreeModel` را به‌کار ببرید
- selection و cell editing را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۶۴ (Swing Events)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### JTable — جدول داده

```java
String[] columns = {"Name", "Age", "City"};
Object[][] data = {
    {"Ali", 20, "Tehran"},
    {"Sara", 25, "Isfahan"},
    {"Reza", 30, "Shiraz"}
};

JTable table = new JTable(data, columns);
JScrollPane scrollPane = new JScrollPane(table);
frame.add(scrollPane, BorderLayout.CENTER);
```

### DefaultTableModel — مدل قابل ویرایش

```java
DefaultTableModel model = new DefaultTableModel(columns, 0);
model.addRow(new Object[]{"Ali", 20, "Tehran"});
model.addRow(new Object[]{"Sara", 25, "Isfahan"});

JTable table = new JTable(model);

// اضافه کردن row
model.addRow(new Object[]{"New", 30, "Tabriz"});

// حذف row
model.removeRow(0);

// get value
String name = (String) model.getValueAt(0, 0);
```

### JTree — درخت سلسله‌مراتبی

```java
DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("Child 1");
DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("Child 2");
root.add(child1);
root.add(child2);
child1.add(new DefaultMutableTreeNode("Grandchild"));

JTree tree = new JTree(root);
JScrollPane scrollPane = new JScrollPane(tree);
```

### TreeSelectionListener — انتخاب درخت

```java
tree.addTreeSelectionListener(e -> {
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
    if (node != null) {
        System.out.println("Selected: " + node.getUserObject());
    }
});
```

## کد مهم

`src/SwingTablesTreesDemo.java` — JTable با DefaultTableModel، JTree با DefaultTreeModel.

## خطاهای رایج
- `JTable` بدون `JScrollPane` → header دیده نمی‌شود.
- `DefaultTableModel` با data اشتباه → exception.
- `getLastSelectedPathComponent` null → NPE — چک کنید.
- Tree node را direct cast کردن → `ClassCastException`.

## جمع‌بندی
- JTable برای tabular data — DefaultTableModel برای editable.
- JTree برای hierarchical data — DefaultMutableTreeNode.
- JScrollPane برای scrollable tables/trees.

**جلسهٔ بعد:** Swing Capstone.
