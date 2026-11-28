# پاسخ تمرین‌های جلسه ۶۲

## آسان

**۱ و ۲.**
```java
SwingUtilities.invokeLater(() -> {
    JFrame frame = new JFrame("Test");
    frame.setSize(400, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    panel.add(new JButton("Click"));
    frame.add(panel);
    frame.setVisible(true);
});
```

## متوسط

**۳ و ۴.**
```java
panel.add(new JLabel("Welcome"));
panel.add(new JTextField(15));
panel.add(new JCheckBox("Option"));
panel.add(new JComboBox<>(new String[]{"A", "B"}));

JOptionPane.showMessageDialog(frame, "Hello!", "Info", JOptionPane.INFORMATION_MESSAGE);
```

## سخت

**۵ و ۶.**
```java
int result = JOptionPane.showConfirmDialog(frame, "Sure?", "Confirm", JOptionPane.YES_NO_OPTION);
String input = JOptionPane.showInputDialog(frame, "Enter name:");
```
