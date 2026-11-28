# پاسخ تمرین‌های جلسه ۷۵

## آسان

**۱ و ۲.**
```java
Table table = new Table(shell, SWT.BORDER);
table.setHeaderVisible(true);
TableColumn col = new TableColumn(table, SWT.NONE);
col.setText("Name");
TableItem item = new TableItem(table, SWT.NONE);
item.setText(0, "Ali");

Tree tree = new Tree(shell, SWT.BORDER);
TreeItem root = new TreeItem(tree, SWT.NONE);
root.setText("Root");
TreeItem child = new TreeItem(root, SWT.NONE);
child.setText("Child");
```

## متوسط

**۳ و ۴.**
```java
TabFolder tabs = new TabFolder(shell, SWT.NONE);
TabItem tab1 = new TabItem(tabs, SWT.NONE);
tab1.setText("Tab 1");
Composite content = new Composite(tabs, SWT.NONE);
tab1.setControl(content);

Menu menuBar = new Menu(shell, SWT.BAR);
Menu fileMenu = new Menu(menuBar, SWT.DROP_DOWN);
fileMenu.setText("File");
menuBar.setMenu(0, fileMenu);
MenuItem newItem = new MenuItem(fileMenu, SWT.PUSH);
newItem.setText("New");
```

## سخت

**۵ و ۶.**
```java
GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
widget.setLayoutData(gd);

widget.addListener(SWT.Selection, e -> {
    System.out.println("Selected");
});
```
