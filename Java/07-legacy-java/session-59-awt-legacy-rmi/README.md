# جلسه ۵۹ — AWT قدیمی و RMI

## اهداف یادگیری
- AWT basics را برای فهم کد قدیمی بشناسید
- AWT منسوخ شده را بفهمید
- RMI را برای distributed objects بشناسید
- جایگزین‌های مدرن (JavaFX، REST/gRPC) را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۵۸

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### AWT — Abstract Window Toolkit (قدیمی‌ترین GUI)

```java
import java.awt.*;
import java.awt.event.*;

public class AWTApp extends Frame {
    public AWTApp() {
        super("AWT App");
        setSize(400, 300);
        setLayout(new FlowLayout());

        Button btn = new Button("Click Me");
        btn.addActionListener(e -> System.out.println("Clicked!"));
        add(btn);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new AWTApp();
    }
}
```

### چرا AWT منسوخ شده؟

| مشکل | توضیح |
|---|---|
| Native components | هر platform ظاهر متفاوت |
| Single-threaded | EDT فقط یک thread |
| Limited widgets | Button, Label, TextField — ساده |
| No layout managers خوب | FlowLayout, BorderLayout — محدود |

### جایگزین‌ها

- **Swing** — pure Java، بهتر از AWT ولی هنوز قدیمی
- **JavaFX** — مدرن، Scene Builder، CSS-like styling
- **Web (JSF, Spring MVC)** — برای enterprise apps

### RMI — Remote Method Invocation

```java
// Server — remote object
public class HelloImpl extends UnicastRemoteObject implements Hello {
    public HelloImpl() throws RemoteException { super(); }
    public String sayHello() { return "Hello from server!"; }
}

// Client — call remote method
Hello stub = (Hello) Naming.lookup("rmi://localhost/HelloService");
String result = stub.sayHello();
```

### چرا RMI منسوخ شده؟

- Complex setup (registry, serialization)
- Firewall-unfriendly
- REST/gRPC بهتر برای web services
- Microservices architecture

## کد مهم

`src/AWTLegacyDemo.java` — AWT basics (فقط برای فهم).
`src/RMILegacyDemo.java` — RMI concepts (فقط برای فهم).

## خطاهای رایج
- AWT را برای پروژهٔ جدید استفاده کردن → JavaFX یا Web بهتر است.
- RMI را برای پروژهٔ جدید استفاده کردن → REST/gRPC بهتر است.
- Applet را برای پروژهٔ جدید استفاده کردن → کاملاً منسوخ.
- native components را برای cross-platform assume کردن → مشکل.

## جمع‌بندی
- AWT قدیمی‌ترین GUI — برای فهم کد legacy.
- RMI برای distributed objects — منسوخ، REST/gRPC جایگزین.
- برای پروژهٔ جدید از modern tech استفاده کنید.

**جلسهٔ بعد:** Legacy vs Modern — جمع‌بندی فاز.
