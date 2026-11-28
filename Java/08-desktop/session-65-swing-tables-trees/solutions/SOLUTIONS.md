# پاسخ تمرین‌های جلسه ۶۵

## آسان

**۱ و ۲.**
```java
DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "Age"}, 0);
model.addRow(new Object[]{"Ali", 20});
JTable table = new JTable(model);
JScrollPane scroll = new JScrollPane(table);

DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
root.add(new DefaultMutableTreeNode("Child"));
JTree tree = new JTree(root);
```

## متوسط

**۳ و ۴.**
```java
model.addRow(new Object[]{"New", 30});
model.removeRow(0);

tree.addTreeSelectionListener(e -> {
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
    if (node != null) System.out.println(node.getUserObject());
});
```

## سخت

**۵ و ۶.**
```java
model.addTableModelListener(e -> {
    System.out.println("Changed: " + e.getFirstRow());
});

DefaultTreeModel treeModel = new DefaultTreeModel(root);
JTree tree = new JTree(treeModel);
treeModel.insertNodeInto(new DefaultMutableTreeNode("New"), parent, 0);
treeModel.removeNodeFromParent(node);
```
