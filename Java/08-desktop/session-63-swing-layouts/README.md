# جلسه ۶۳ — Swing Layouts

## اهداف یادگیری
- `BorderLayout`, `FlowLayout`, `GridLayout`, `BoxLayout` را بشناسید
- `GridBagLayout` برای complex layouts را بفهمید
- `CardLayout` برای tab-like panels را بشناسید
- nested panels برای complex UIs را به‌کار ببرید

## پیش‌نیازها
- جلسهٔ ۶۲ (Swing Components)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### BorderLayout — North/South/East/West/Center

```java
frame.setLayout(new BorderLayout());

frame.add(new JButton("North"), BorderLayout.NORTH);
frame.add(new JButton("South"), BorderLayout.SOUTH);
frame.add(new JButton("East"), BorderLayout.EAST);
frame.add(new JButton("West"), BorderLayout.WEST);
frame.add(new JButton("Center"), BorderLayout.CENTER);
```

### FlowLayout — کامپوننت‌ها پشت سر هم

```java
panel.setLayout(new FlowLayout());
panel.add(new JButton("A"));
panel.add(new JButton("B"));
panel.add(new JButton("C"));
// A B C — چپ به راست یا راست به چپ
```

### GridLayout — grid ثابت

```java
panel.setLayout(new GridLayout(3, 2, 5, 5));   // 3 rows, 2 cols, hgap, vgap
panel.add(new JButton("1"));
panel.add(new JButton("2"));
panel.add(new JButton("3"));
// 1 2
// 3 (empty) (empty)
```

### BoxLayout — عمودی/افقی با flexible spacing

```java
JPanel panel = new JPanel();
panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
panel.add(new JLabel("Top"));
panel.add(Box.createVerticalStrut(10));   // فاصله
panel.add(new JButton("Middle"));
panel.add(Box.createVerticalGlue());       // flexible space
panel.add(new JButton("Bottom"));
```

### CardLayout — tab-like panels

```java
JPanel cards = new JPanel(new CardLayout());
cards.add(new JLabel("Card 1"), "card1");
cards.add(new JLabel("Card 2"), "card2");

CardLayout cl = (CardLayout) cards.getLayout();
cl.show(cards, "card2");   // نمایش card2
```

## کد مهم

`src/SwingLayoutsDemo.java` — BorderLayout، FlowLayout، GridLayout، BoxLayout، CardLayout.

## خطاهای رایج
- `BorderLayout` بدون CENTER → کامپوننت‌ها فشرده می‌شوند.
- `GridLayout` بدون rows/cols → exception.
- `BoxLayout` بدون Y_AXIS/X_AXIS → exception.
- nested panels را فراموش کردن → complex layouts سخت می‌شوند.

## جمع‌بندی
- BorderLayout برای ساختار اصلی، FlowLayout برای ساده، GridLayout برای grid.
- BoxLayout برای flexible spacing، CardLayout برای tabs.
- Nested panels برای complex UIs.

**جلسهٔ بعد:** Swing Events.
