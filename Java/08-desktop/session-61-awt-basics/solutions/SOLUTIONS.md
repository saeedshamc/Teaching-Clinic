# پاسخ تمرین‌های جلسه ۶۱

## آسان

**۱ و ۲.**
```java
Frame f = new Frame("Test");
f.setSize(400, 300);
f.setLayout(new FlowLayout());
Button btn = new Button("Click");
btn.addActionListener(e -> System.out.println("Clicked"));
f.add(btn);
f.add(new Label("Welcome"));
f.add(new TextField(20));
f.setVisible(true);
```

## متوسط

**۳ و ۴.**
```java
f.setLayout(new BorderLayout());
f.add(btn, BorderLayout.NORTH);
f.add(panel, BorderLayout.CENTER);

btn.addActionListener(e -> {
    label.setText("Clicked!");
});
```

## سخت

**۵ و ۶.**
```java
f.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
    }
});

cb.addItemListener(e -> {
    System.out.println("State: " + cb.getState());
});
```
