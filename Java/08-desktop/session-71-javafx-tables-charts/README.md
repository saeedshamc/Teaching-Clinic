# جلسه ۷۱ — JavaFX Tables و Charts

## اهداف یادگیری
- `TableView` با `TableColumn` را بشناسید
- `ObservableList` برای dynamic table data را به‌کار ببرید
- `PieChart`, `BarChart`, `LineChart` را بفهمید
- `XYChart.Series` برای chart data را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۷۰ (JavaFX Events و CSS)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### TableView — جدول داده

```java
public class Person {
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();

    public String getName() { return name.get(); }
    public void setName(String v) { name.set(v); }
    public Integer getAge() { return age.get(); }
    public void setAge(int v) { age.set(v); }
}

TableView<Person> table = new TableView<>();
TableColumn<Person, String> nameCol = new TableColumn<>("Name");
nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");
ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty());

table.getColumns().addAll(nameCol, ageCol);

ObservableList<Person> data = FXCollections.observableArrayList();
data.add(new Person("Ali", 20));
table.setItems(data);
```

### PieChart — نمودار دایره‌ای

```java
ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
    new PieChart.Data("A", 25),
    new PieChart.Data("B", 35),
    new PieChart.Data("C", 40)
);
PieChart chart = new PieChart(pieData);
chart.setTitle("Distribution");
```

### BarChart — نمودار میله‌ای

```java
CategoryAxis xAxis = new CategoryAxis();
xAxis.setLabel("Month");
NumberAxis yAxis = new NumberAxis();
yAxis.setLabel("Sales");

BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
barChart.setTitle("Sales by Month");

XYChart.Series<String, Number> series = new XYChart.Series<>();
series.setName("2024");
series.getData().add(new XYChart.Data<>("Jan", 100));
series.getData().add(new XYChart.Data<>("Feb", 150));
series.getData().add(new XYChart.Data<>("Mar", 200));

barChart.getData().add(series);
```

### LineChart — نمودار خطی

```java
LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
lineChart.setTitle("Trend");

XYChart.Series<String, Number> series = new XYChart.Series<>();
series.setName("Trend");
series.getData().add(new XYChart.Data<>("Jan", 100));
series.getData().add(new XYChart.Data<>("Feb", 150));
series.getData().add(new XYChart.Data<>("Mar", 200));

lineChart.getData().add(series);
```

## کد مهم

`src/JavaFXTablesChartsDemo.java` — TableView، PieChart، BarChart، LineChart.

## خطاهای رایج
- `TableColumn` بدون `setCellValueFactory` → خالی است.
- `Property` را به‌جای getter استفاده کردن → `cellData.getValue().nameProperty()`.
- `XYChart.Series` را فراموش کردن → chart خالی است.
- `ObservableList` را برای static data استفاده کردن → overkill.

## جمع‌بندی
- TableView برای tabular data — TableColumn + Property.
- Charts برای visualization — PieChart, BarChart, LineChart.
- ObservableList برای dynamic data.

**جلسهٔ بعد:** JavaFX Concurrency.
