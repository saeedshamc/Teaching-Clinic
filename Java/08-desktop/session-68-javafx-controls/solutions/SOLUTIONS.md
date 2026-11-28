# پاسخ تمرین‌های جلسه ۶۸

## آسان

**۱ و ۲.**
```java
TextField tf = new TextField();
PasswordField pf = new PasswordField();
CheckBox cb = new CheckBox("Option");
ComboBox<String> combo = new ComboBox<>();
combo.getItems().addAll("A", "B", "C");
```

## متوسط

**۳ و ۴.**
```java
ObservableList<String> items = FXCollections.observableArrayList("A", "B", "C");
ListView<String> list = new ListView<>(items);

btn.setOnAction(e -> {
    System.out.println("Clicked");
});
```

## سخت

**۵ و ۶.**
```java
cb.selectedProperty().addListener((obs, old, newVal) -> {
    System.out.println("Checked: " + newVal);
});

list.getSelectionModel().selectedItemProperty().addListener((obs, old, newVal) -> {
    System.out.println("Selected: " + newVal);
});
```
