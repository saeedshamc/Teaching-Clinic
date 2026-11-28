# جلسه ۷۵ — SWT Widgets پیشرفته

## اهداف یادگیری
- `Table`, `Tree`, `TabFolder`, `ToolBar`, `Menu` را بشناسید
- `GridData` برای advanced layout را بفهمید
- `SWT` widgets برای complex UIs را به‌کار ببرید
- فاز ۵ (Desktop) را جمع‌بندی کنید

## پیش‌نیازها
- جلسهٔ ۷۴ (SWT Basics)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Table — جدول داده

```java
Table table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
table.setHeaderVisible(true);
table.setLinesVisible(true);

TableColumn col1 = new TableColumn(table, SWT.NONE);
col1.setText("Name");
col1.setWidth(150);

TableItem item = new TableItem(table, SWT.NONE);
item.setText(0, "Ali");
item.setText(1, "123-4567");
```

### Tree — درخت سلسله‌مراتبی

```java
Tree tree = new Tree(shell, SWT.BORDER);
TreeItem root = new TreeItem(tree, SWT.NONE);
root.setText("Root");
TreeItem child = new TreeItem(root, SWT.NONE);
child.setText("Child 1");
TreeItem grandchild = new TreeItem(child, SWT.NONE);
grandchild.setText("Grandchild");
```

### TabFolder — تب‌ها

```java
TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
TabItem tab1 = new TabItem(tabFolder, SWT.NONE);
tab1.setText("Tab 1");
Composite tab1Content = new Composite(tabFolder, SWT.NONE);
tab1.setControl(tab1Content);
// add widgets to tab1Content
```

### Menu — منو

```java
Menu menuBar = new Menu(shell, SWT.BAR);
shell.setMenuBar(menuBar);

Menu fileMenu = new Menu(menuBar, SWT.DROP_DOWN);
fileMenu.setText("File");
menuBar.setMenu(0, fileMenu);

MenuItem newItem = new MenuItem(fileMenu, SWT.PUSH);
newItem.setText("New");
newItem.addListener(SWT.Selection, e -> System.out.println("New"));
```

### GridData — advanced layout

```java
GridData gd = new GridData();
gd.horizontalAlignment = SWT.FILL;
gd.verticalAlignment = SWT.FILL;
gd.grabExcessHorizontalSpace = true;
gd.grabExcessVerticalSpace = true;
widget.setLayoutData(gd);
```

## کد مهم

`src/SWTWidgetsDemo.java` — Table، Tree، TabFolder، Menu، GridData.

## جمع‌بندی فاز ۵ — Desktop

- **AWT/Swing (۶۱–۶۶)**: Frame, JPanel, JButton, JTable, JTree, events, layouts ✅
- **JavaFX (۶۷–۷۳)**: Application, Stage, Scene, controls, FXML, CSS, charts, concurrency ✅
- **SWT (۷۴–۷۵)**: Display, Shell, Composite, Table, Tree, TabFolder, Menu ✅

**فاز ۶: Android — ۲۰ جلسه**
