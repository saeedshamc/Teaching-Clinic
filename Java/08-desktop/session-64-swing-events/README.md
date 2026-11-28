# جلسه ۶۴ — Swing Events

## اهداف یادگیری
- `ActionListener`, `ItemListener`, `MouseListener`, `KeyListener` را بشناسید
- lambda برای event handling را به‌کار ببرید
- `ActionEvent`, `ItemEvent`, `MouseEvent`, `KeyEvent` را بفهمید
- adapter classes را برای ساده‌تر کردن listenerها بشناسید

## پیش‌نیازها
- جلسهٔ ۶۳ (Swing Layouts)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### ActionListener — برای buttons, menus

```java
JButton btn = new JButton("Click");
btn.addActionListener(e -> {
    System.out.println("Clicked: " + e.getActionCommand());
});
```

### ItemListener — برای checkboxes, radio buttons, combo boxes

```java
JCheckBox cb = new JCheckBox("Option");
cb.addItemListener(e -> {
    if (e.getStateChange() == ItemEvent.SELECTED) {
        System.out.println("Selected");
    } else {
        System.out.println("Deselected");
    }
});
```

### MouseListener — برای mouse events

```java
JPanel panel = new JPanel();
panel.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked at " + e.getX() + "," + e.getY());
    }
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered");
    }
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited");
    }
});
```

### KeyListener — برای keyboard events

```java
JTextField tf = new JTextField(20);
tf.addKeyListener(new KeyAdapter() {
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyChar());
    }
    public void keyReleased(KeyEvent e) {
        System.out.println("Key released");
    }
});
```

### Adapter classes — برای simple cases

```java
// بدون adapter — باید همه متدها را implement کنید
panel.addMouseListener(new MouseListener() {
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
});

// با adapter — فقط متدهای مورد نیاز
panel.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        // فقط این متد
    }
});
```

## کد مهم

`src/SwingEventsDemo.java` — ActionListener، ItemListener، MouseListener، KeyListener، adapters.

## خطاهای رایج
- `MouseListener` بدون adapter → باید همه ۷ متد را implement کنید.
- `KeyListener` بدون adapter → باید همه ۳ متد را implement کنید.
- lambda برای interface با چند متد → compile error — adapter لازم است.
- event handling روی wrong thread → EDT issues.

## جمع‌بندی
- ActionListener برای buttons، ItemListener برای checkboxes/radio/combos.
- MouseListener/KeyListener برای mouse/keyboard — adapter برای simple cases.
- Lambda برای functional interfaces، adapter برای multi-method.

**جلسهٔ بعد:** Swing Tables و Trees.
