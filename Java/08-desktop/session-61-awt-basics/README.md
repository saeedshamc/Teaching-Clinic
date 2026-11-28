# جلسه ۶۱ — AWT Basics

## اهداف یادگیری
- `Frame`, `Panel`, `Button`, `Label`, `TextField` را بشناسید
- `setSize`, `setVisible`, `setLayout` را به‌کار ببرید
- `main` thread در برابر EDT (Event Dispatch Thread) را بفهمید
- basic event handling با `ActionListener` را یاد بگیرید

## پیش‌نیازها
- فازهای ۱–۴

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### اولین برنامهٔ AWT

```java
import java.awt.*;
import java.awt.event.*;

public class MyApp extends Frame {

    public MyApp() {
        super("My AWT App");
        setSize(400, 300);
        setLayout(new FlowLayout());

        Button btn = new Button("Click Me");
        btn.addActionListener(e -> {
            System.out.println("Button clicked!");
        });
        add(btn);

        Label label = new Label("Welcome");
        add(label);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MyApp();
    }
}
```

### کامپوننت‌های اصلی AWT

| کامپوننت | کاربرد |
|---|---|
| `Frame` | پنجرهٔ اصلی |
| `Panel` | کانتینر برای کامپوننت‌ها |
| `Button` | دکمه |
| `Label` | متن ثابت |
| `TextField` | ورودی یک‌خطی |
| `TextArea` | ورودی چندخطی |
| `Checkbox`, `RadioButton` | انتخاب |
| `Choice`, `List` | لیست |
| `Canvas` | رسم آزاد |

### Layout Managers

```java
setLayout(new FlowLayout());        // کامپوننت‌ها پشت سر هم
setLayout(new BorderLayout());      // NORTH/SOUTH/EAST/WEST/CENTER
setLayout(new GridLayout(2, 3));    // grid 2x3
setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // عمودی/افقی
```

### EDT — Event Dispatch Thread

- همهٔ GUI operations باید روی EDT اجرا شوند.
- `main` thread نباید مستقیم GUI را دست‌کاری کند.
- `SwingUtilities.invokeLater()` برای Swing (بعداً).

## کد مهم

`src/AWTBasicsDemo.java` — Frame، کامپوننت‌ها، layout، ActionListener.

## خطاهای رایج
- `setVisible(true)` را فراموش کردن → پنجره دیده نمی‌شود.
- `setSize` را فراموش کردن → پنجره tiny است.
- `windowClosing` را handle نکردن → برنامه بسته نمی‌شود.
- GUI را روی main thread دست‌کاری کردن → EDT issues.

## جمع‌بندی
- AWT قدیمی‌ترین GUI toolkit — برای فهم legacy.
- Frame + Layout + Components + Event Listeners.
- برای پروژهٔ جدید از Swing/JavaFX استفاده کنید.

**جلسهٔ بعد:** Swing Components.
