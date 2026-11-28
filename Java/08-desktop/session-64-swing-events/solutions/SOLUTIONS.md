# پاسخ تمرین‌های جلسه ۶۴

## آسان

**۱ و ۲.**
```java
btn.addActionListener(e -> {
    System.out.println("Clicked: " + e.getActionCommand());
});

cb.addItemListener(e -> {
    if (e.getStateChange() == ItemEvent.SELECTED) {
        System.out.println("Selected");
    }
});
```

## متوسط

**۳ و ۴.**
```java
panel.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked at " + e.getX() + "," + e.getY());
    }
    public void mouseEntered(MouseEvent e) { System.out.println("Entered"); }
    public void mouseExited(MouseEvent e) { System.out.println("Exited"); }
});

tf.addKeyListener(new KeyAdapter() {
    public void keyPressed(KeyEvent e) {
        System.out.println("Key: " + e.getKeyChar());
    }
});
```

## سخت

**۵ و ۶.**
```java
panel.addMouseMotionListener(new MouseMotionAdapter() {
    public void mouseDragged(MouseEvent e) {
        System.out.println("Dragging at " + e.getX() + "," + e.getY());
    }
});

// چند listener روی یک component:
btn.addActionListener(e -> System.out.println("Listener 1"));
btn.addActionListener(e -> System.out.println("Listener 2"));
```
