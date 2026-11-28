# پاسخ تمرین‌های جلسه ۵۹

## آسان

**۱ و ۲.**
```java
// AWT
Frame f = new Frame("Test");
f.setSize(400, 300);
Button btn = new Button("Click");
btn.addActionListener(e -> System.out.println("Clicked"));
f.add(btn);
f.setVisible(true);

// RMI — فقط concepts:
// Server: UnicastRemoteObject + Remote interface
// Client: Naming.lookup + stub.method()
```

## متوسط

**۳ و ۴.**
```java
btn.addActionListener(e -> System.out.println("Clicked"));

f.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
    }
});
```

## سخت

**۵ و ۶.**
```java
// AWT vs Swing:
// AWT: native components — platform-specific
// Swing: pure Java — consistent cross-platform
// Swing بهتر برای most cases

// RMI vs REST:
// RMI: complex, firewall-unfriendly, Java-only
// REST: simple, HTTP-based, language-agnostic
// REST بهتر برای web services
```
