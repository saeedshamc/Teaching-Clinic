# پاسخ تمرین‌های جلسه ۷۱

## آسان

**۱ و ۲.**
```java
TableView<Person> table = new TableView<>();
TableColumn<Person, String> nameCol = new TableColumn<>("Name");
nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
table.getColumns().add(nameCol);
table.setItems(data);

PieChart chart = new PieChart(FXCollections.observableArrayList(
    new PieChart.Data("A", 25), new PieChart.Data("B", 35)
));
```

## متوسط

**۳ و ۴.**
```java
BarChart<String, Number> bar = new BarChart<>(xAxis, yAxis);
XYChart.Series<String, Number> series = new XYChart.Series<>();
series.getData().add(new XYChart.Data<>("Jan", 100));
bar.getData().add(series);

LineChart<String, Number> line = new LineChart<>(xAxis, yAxis);
line.getData().add(series);
```

## سخت

**۵ و ۶.**
```java
nameCol.setCellFactory(col -> new TableCell<>() {
    private final TextField field = new TextField();
    public void startEdit() { field.setText(getItem()); super.startEdit(); }
    public void commitEdit() { setItem(field.getText()); super.commitEdit(); }
});

// dynamic update
series.getData().add(new XYChart.Data<>("Apr", 250));
```
