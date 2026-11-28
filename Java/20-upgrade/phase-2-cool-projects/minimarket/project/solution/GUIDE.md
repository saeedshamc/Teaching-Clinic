# راهنمای پاسخ — Mini Market گرافیکی

## POS با ComboBox + سبد
```java
// کالاها در JComboBox؛ انتخاب → qty spinner → دکمهٔ «افزودن به فاکتور»
DefaultTableModel cartModel = new DefaultTableModel(
    new String[]{"کالا","تعداد","قیمت"}, 0);
JTable cartTable = new JTable(cartModel);
// دکمهٔ «ثبت فاکتور» → service.createInvoice(cartItems)
```

## جستجو با DocumentListener
```java
searchField.getDocument().addDocumentListener(new DocumentListener() {
    public void insertUpdate(DocumentEvent e) { filter(); }
    public void removeUpdate(DocumentEvent e) { filter(); }
    public void changedUpdate(DocumentEvent e) { filter(); }
    void filter() {
        String q = searchField.getText().toLowerCase();
        // model.setRowCount(0); for products matching q → addRow
    }
});
```

## Print فاکتور
```java
// کلاس PrintInvoice implements Printable
public int print(Graphics g, PageFormat pf, int page) {
    // drawString سطرهای فاکتور — return NO_SUCH_PAGE بعد از صفحهٔ اول
}
```

## نمودار
```java
// JPanel سفارشی — برای هر روز یک مستطیل به ارتفاع فروش
@Override protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    // normalize heights، fillRect
}
```
