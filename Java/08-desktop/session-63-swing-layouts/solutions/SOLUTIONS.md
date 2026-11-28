# پاسخ تمرین‌های جلسه ۶۳

## آسان

**۱ و ۲.**
```java
frame.setLayout(new BorderLayout());
frame.add(new JButton("North"), BorderLayout.NORTH);
frame.add(new JButton("Center"), BorderLayout.CENTER);

panel.setLayout(new FlowLayout());
panel.add(new JButton("A"));
panel.add(new JButton("B"));
```

## متوسط

**۳ و ۴.**
```java
panel.setLayout(new GridLayout(3, 3, 5, 5));
for (int i = 1; i <= 9; i++) panel.add(new JButton(String.valueOf(i)));

panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
panel.add(Box.createVerticalGlue());
```

## سخت

**۵ و ۶.**
```java
JPanel cards = new JPanel(new CardLayout());
cards.add(new JLabel("Card 1"), "card1");
cards.add(new JLabel("Card 2"), "card2");
CardLayout cl = (CardLayout) cards.getLayout();
cl.show(cards, "card2");
```
