# پاسخ تمرین‌های جلسه ۷۳

## آسان

**۱ و ۲.**
```java
public class Contact {
    private final StringProperty name = new SimpleStringProperty();
    public StringProperty nameProperty() { return name; }
}
TableView<Contact> table = new TableView<>();
TableColumn<Contact, String> col = new TableColumn<>("Name");
col.setCellValueFactory(c -> c.getValue().nameProperty());
table.setItems(contacts);
```

## متوسط

**۳ و ۴.**
```java
addBtn.setOnAction(e -> {
    contacts.add(new Contact(name, phone, email, cat));
});
PieChart chart = new PieChart(pieData);
```

## سخت

**۵ و ۶.**
```java
combo.getSelectionModel().selectedItemProperty().addListener((obs, old, newVal) -> {
    // filter contacts by category
});

Task<Void> task = new Task<Void>() {
    @Override protected Void call() { /* load/save */ return null; }
};
new Thread(task).start();
```
