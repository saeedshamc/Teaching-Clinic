# جلسه ۷۴ — SWT Basics

## اهداف یادگیری
- SWT (Standard Widget Toolkit) را بشناسید
- `Display`, `Shell`, `Composite` را بفهمید
- `SWT` در برابر `Swing`/`JavaFX` را تشخیص دهید
- basic widgets (`Button`, `Label`, `Text`) را به‌کار ببرید

## پیش‌نیازها
- جلسهٔ ۷۳ (JavaFX Capstone)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### SWT — Eclipse's native GUI toolkit

```java
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

public class MyApp {
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("My SWT App");
        shell.setSize(400, 300);

        shell.setLayout(new GridLayout(1, false));

        Button btn = new Button(shell, SWT.PUSH);
        btn.setText("Click Me");
        btn.addListener(SWT.Selection, e -> {
            System.out.println("Clicked!");
        });

        Label label = new Label(shell, SWT.NONE);
        label.setText("Welcome");

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        display.dispose();
    }
}
```

### SWT vs Swing vs JavaFX

| Feature | SWT | Swing | JavaFX |
|---|---|---|---|
| Rendering | Native | Pure Java | Pure Java |
| Look & Feel | Native | Customizable | CSS-like |
| Performance | Fast (native) | Medium | Good |
| Platform | Native widgets | Cross-platform | Cross-platform |
| Use case | Eclipse IDE | Enterprise apps | Modern apps |

### Display — main event loop

```java
Display display = new Display();
// ... create widgets ...
shell.open();
while (!shell.isDisposed()) {
    if (!display.readAndDispatch()) {
        display.sleep();
    }
}
display.dispose();
```

### Shell — window

```java
Shell shell = new Shell(display);
shell.setText("Title");
shell.setSize(400, 300);
shell.open();
```

### Composite — container

```java
Composite composite = new Composite(shell, SWT.NONE);
composite.setLayout(new GridLayout(2, false));
Button btn = new Button(composite, SWT.PUSH);
Label label = new Label(composite, SWT.NONE);
```

## کد مهم

`src/SWTBasicsDemo.java` — Display، Shell، Button، Label، event loop.

## خطاهای رایج
- `display.dispose()` را فراموش کردن → resource leak.
- `shell.open()` را فراموش کردن → پنجره دیده نمی‌شود.
- `while (!shell.isDisposed())` را فراموش کردن → event loop اجرا نمی‌شود.
- SWT jar را نصب نکردن → `ClassNotFoundException`.

## جمع‌بندی
- SWT = native widgets — fast, Eclipse IDE uses it.
- `Display` → `Shell` → `Composite` → widgets.
- Event loop با `readAndDispatch()`.

**جلسهٔ بعد:** SWT Widgets.
