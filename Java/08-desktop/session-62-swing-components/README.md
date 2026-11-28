# جلسه ۶۲ — Swing Components

## اهداف یادگیری
- `JFrame`, `JPanel`, `JButton`, `JLabel`, `JTextField` را بشناسید
- Swing در برابر AWT را تشخیص دهید
- `SwingUtilities.invokeLater` را برای EDT بفهمید
- `JOptionPane` برای dialogهای ساده را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۶۱ (AWT Basics)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Swing — pure Java GUI

```java
import javax.swing.*;

public class MyApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("My Swing App");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.add(new JLabel("Welcome"));
            panel.add(new JButton("Click Me"));

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
```

### JFrame vs Frame

| AWT `Frame` | Swing `JFrame` |
|---|---|
| native components | pure Java |
| platform-specific | consistent cross-platform |
| limited widgets | rich widgets (JTable, JTree, JList) |
| `addWindowListener` | `setDefaultCloseOperation` |

### کامپوننت‌های اصلی Swing

```java
JButton btn = new JButton("Click");
JLabel label = new JLabel("Text");
JTextField tf = new JTextField(20);
JTextArea ta = new JTextArea(10, 30);
JCheckBox cb = new JCheckBox("Option");
JRadioButton rb = new JRadioButton("Choice");
JComboBox<String> combo = new JComboBox<>(new String[]{"A", "B", "C"});
JList<String> list = new JList<>(new String[]{"Item1", "Item2"});
JTable table = new JTable(data, columns);
JTree tree = new JTree(rootNode);
```

### JOptionPane — dialogهای ساده

```java
// پیام
JOptionPane.showMessageDialog(frame, "Hello!", "Info", JOptionPane.INFORMATION_MESSAGE);

// تأیید
int result = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);

// ورودی
String input = JOptionPane.showInputDialog(frame, "Enter name:");

// خطا
JOptionPane.showMessageDialog(frame, "Error occurred", "Error", JOptionPane.ERROR_MESSAGE);
```

## کد مهم

`src/SwingComponentsDemo.java` — JFrame، JPanel، JButton، JLabel، JOptionPane.

## خطاهای رایج
- `SwingUtilities.invokeLater` را فراموش کردن → EDT issues.
- `setDefaultCloseOperation` را فراموش کردن → برنامه بسته نمی‌شود.
- `JFrame` را direct extend کردن → `JFrame` composition بهتر است.
- GUI را روی main thread دست‌کاری کردن → EDT issues.

## جمع‌بندی
- Swing = pure Java GUI — بهتر از AWT.
- `SwingUtilities.invokeLater` برای EDT.
- `JOptionPane` برای dialogهای ساده.

**جلسهٔ بعد:** Swing Layouts.
