# جلسه ۶۸ — JavaFX Controls

## اهداف یادگیری
- کنترل‌های JavaFX را بشناسید (Button, Label, TextField, CheckBox, ComboBox, ListView)
- `setOnAction` و `setOnMouseClicked` را به‌کار ببرید
- `ObservableList` برای dynamic data را بفهمید
- `getSelectionModel` برای selection را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۶۷ (JavaFX Setup)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### کنترل‌های اصلی JavaFX

```java
Button btn = new Button("Click Me");
Label label = new Label("Text");
TextField tf = new TextField("Input");
PasswordField pf = new PasswordField();
CheckBox cb = new CheckBox("Option");
ComboBox<String> combo = new ComboBox<>();
combo.getItems().addAll("A", "B", "C");
ListView<String> listView = new ListView<>();
```

### Event Handling — setOnAction

```java
btn.setOnAction(e -> {
    System.out.println("Clicked: " + tf.getText());
});

cb.selectedProperty().addListener((obs, old, newVal) -> {
    System.out.println("Checked: " + newVal);
});
```

### ObservableList — برای dynamic data

```java
ObservableList<String> items = FXCollections.observableArrayList();
items.add("Item 1");
items.add("Item 2");

ListView<String> listView = new ListView<>(items);
// listView به‌صورت خودکار با items به‌روز می‌شود
```

### Selection Model

```java
listView.getSelectionModel().selectedItemProperty().addListener((obs, old, newVal) -> {
    System.out.println("Selected: " + newVal);
});

combo.getSelectionModel().selectedItemProperty().addListener((obs, old, newVal) -> {
    System.out.println("Combo selected: " + newVal);
});
```

## کد مهم

`src/JavaFXControlsDemo.java` — Button، TextField، CheckBox، ComboBox، ListView، events.

## خطاهای رایج
- `getItems().addAll` را فراموش کردن برای ComboBox → خالی است.
- `ObservableList` را برای static data استفاده کردن → overkill.
- `setOnAction` را برای CheckBox استفاده کردن → باید `selectedProperty().addListener`.
- selection listener را روی wrong property گذاشتن → `selectedItemProperty` درست است.

## جمع‌بندی
- JavaFX controls = modern, CSS-styleable, observable.
- `setOnAction` برای buttons، `addListener` برای properties.
- `ObservableList` برای dynamic data.

**جلسهٔ بعد:** JavaFX Layout و FXML.
