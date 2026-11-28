# پاسخ تمرین‌های جلسه ۷۴

## آسان

**۱ و ۲.**
```java
Display display = new Display();
Shell shell = new Shell(display);
shell.setText("App");
shell.setSize(400, 300);
shell.setLayout(new GridLayout(1, false));
shell.add(new Button(shell, SWT.PUSH));
shell.add(new Label(shell, SWT.NONE));
shell.open();
while (!shell.isDisposed()) {
    if (!display.readAndDispatch()) display.sleep();
}
display.dispose();
```

## متوسط

**۳ و ۴.**
```java
shell.setLayout(new GridLayout(2, false));
Button btn = new Button(shell, SWT.PUSH);
btn.addListener(SWT.Selection, e -> System.out.println("Clicked"));
```

## سخت

**۵ و ۶.**
```java
Composite composite = new Composite(shell, SWT.NONE);
composite.setLayout(new GridLayout(1, false));
// add widgets to composite

Table table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
table.setHeaderVisible(true);
```
