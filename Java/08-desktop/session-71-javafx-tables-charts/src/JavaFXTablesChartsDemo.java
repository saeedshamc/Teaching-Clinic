// JavaFXTablesChartsDemo.java — TableView، PieChart، BarChart، LineChart
// نیازمند JavaFX SDK
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXTablesChartsDemo extends Application {

    // ---------- Model ----------
    public static class Person {
        private final StringProperty name = new SimpleStringProperty();
        private final IntegerProperty age = new SimpleIntegerProperty();
        private final StringProperty city = new SimpleStringProperty();

        public Person(String name, int age, String city) {
            setName(name);
            setAge(age);
            setCity(city);
        }

        public String getName() { return name.get(); }
        public void setName(String v) { name.set(v); }
        public StringProperty nameProperty() { return name; }

        public int getAge() { return age.get(); }
        public void setAge(int v) { age.set(v); }
        public IntegerProperty ageProperty() { return age; }

        public String getCity() { return city.get(); }
        public void setCity(String v) { city.set(v); }
        public StringProperty cityProperty() { return city; }
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(20);
        root.setStyle("-fx-padding: 20;");

        // ---------- TableView ----------
        TableView<Person> table = new TableView<>();
        TableColumn<Person, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty());

        TableColumn<Person, String> cityCol = new TableColumn<>("City");
        cityCol.setCellValueFactory(cellData -> cellData.getValue().cityProperty());

        table.getColumns().addAll(nameCol, ageCol, cityCol);
        table.setPrefHeight(150);

        ObservableList<Person> data = FXCollections.observableArrayList(
            new Person("Ali", 20, "Tehran"),
            new Person("Sara", 25, "Isfahan"),
            new Person("Reza", 30, "Shiraz")
        );
        table.setItems(data);

        // ---------- PieChart ----------
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
            new PieChart.Data("A", 25),
            new PieChart.Data("B", 35),
            new PieChart.Data("C", 40)
        );
        PieChart pieChart = new PieChart(pieData);
        pieChart.setTitle("Distribution");
        pieChart.setPrefSize(200, 200);

        // ---------- BarChart ----------
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Month");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Sales");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Sales by Month");
        barChart.setPrefSize(300, 200);

        XYChart.Series<String, Number> barSeries = new XYChart.Series<>();
        barSeries.setName("2024");
        barSeries.getData().add(new XYChart.Data<>("Jan", 100));
        barSeries.getData().add(new XYChart.Data<>("Feb", 150));
        barSeries.getData().add(new XYChart.Data<>("Mar", 200));
        barChart.getData().add(barSeries);

        // ---------- LineChart ----------
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Trend");
        lineChart.setPrefSize(300, 200);

        XYChart.Series<String, Number> lineSeries = new XYChart.Series<>();
        lineSeries.setName("Trend");
        lineSeries.getData().add(new XYChart.Data<>("Jan", 100));
        lineSeries.getData().add(new XYChart.Data<>("Feb", 150));
        lineSeries.getData().add(new XYChart.Data<>("Mar", 200));
        lineChart.getData().add(lineSeries);

        root.getChildren().addAll(
            new javafx.scene.control.Label("JavaFX Tables & Charts"),
            table,
            new HBoxCharts(pieChart, barChart, lineChart)
        );

        Scene scene = new Scene(root, 800, 700);
        stage.setTitle("JavaFX Tables & Charts");
        stage.setScene(scene);
        stage.show();
    }

    // Helper class for charts layout
    static class HBoxCharts extends javafx.scene.layout.HBox {
        HBoxCharts(PieChart pie, BarChart bar, LineChart line) {
            super(10, pie, bar, line);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
