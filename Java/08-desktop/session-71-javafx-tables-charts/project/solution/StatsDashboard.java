// StatsDashboard.java — پاسخ پروژهٔ جلسه ۷۱
// نیازمند JavaFX SDK
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StatsDashboard extends Application {

    public static class Product {
        private final StringProperty name = new SimpleStringProperty();
        private final DoubleProperty sales = new SimpleDoubleProperty();
        private final DoubleProperty profit = new SimpleDoubleProperty();

        Product(String name, double sales, double profit) {
            setName(name); setSales(sales); setProfit(profit);
        }
        public String getName() { return name.get(); }
        public void setName(String v) { name.set(v); }
        public StringProperty nameProperty() { return name; }
        public double getSales() { return sales.get(); }
        public void setSales(double v) { sales.set(v); }
        public DoubleProperty salesProperty() { return sales; }
        public double getProfit() { return profit.get(); }
        public void setProfit(double v) { profit.set(v); }
        public DoubleProperty profitProperty() { return profit; }
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(15);
        root.setStyle("-fx-padding: 20;");

        // ---------- Table ----------
        TableView<Product> table = new TableView<>();
        TableColumn<Product, String> nameCol = new TableColumn<>("Product");
        nameCol.setCellValueFactory(c -> c.getValue().nameProperty());
        TableColumn<Product, Number> salesCol = new TableColumn<>("Sales");
        salesCol.setCellValueFactory(c -> c.getValue().salesProperty());
        TableColumn<Product, Number> profitCol = new TableColumn<>("Profit");
        profitCol.setCellValueFactory(c -> c.getValue().profitProperty());
        table.getColumns().addAll(nameCol, salesCol, profitCol);
        table.setPrefHeight(150);

        ObservableList<Product> products = FXCollections.observableArrayList(
            new Product("Laptop", 5000, 2000),
            new Product("Phone", 8000, 3000),
            new Product("Tablet", 3000, 1500)
        );
        table.setItems(products);

        // ---------- Charts ----------
        // PieChart — market share
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
            new PieChart.Data("Laptop", 25),
            new PieChart.Data("Phone", 40),
            new PieChart.Data("Tablet", 15),
            new PieChart.Data("Other", 20)
        );
        PieChart pieChart = new PieChart(pieData);
        pieChart.setTitle("Market Share");
        pieChart.setPrefSize(250, 200);

        // BarChart — sales by region
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Region");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Sales");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Sales by Region");
        barChart.setPrefSize(300, 200);

        XYChart.Series<String, Number> north = new XYChart.Series<>();
        north.setName("North");
        north.getData().add(new XYChart.Data<>("Q1", 100));
        north.getData().add(new XYChart.Data<>("Q2", 150));
        XYChart.Series<String, Number> south = new XYChart.Series<>();
        south.setName("South");
        south.getData().add(new XYChart.Data<>("Q1", 120));
        south.getData().add(new XYChart.Data<>("Q2", 180));
        barChart.getData().addAll(north, south);

        // LineChart — trend
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Monthly Trend");
        lineChart.setPrefSize(300, 200);
        XYChart.Series<String, Number> trend = new XYChart.Series<>();
        trend.setName("Trend");
        trend.getData().add(new XYChart.Data<>("Jan", 100));
        trend.getData().add(new XYChart.Data<>("Feb", 150));
        trend.getData().add(new XYChart.Data<>("Mar", 200));
        trend.getData().add(new XYChart.Data<>("Apr", 250));
        lineChart.getData().add(trend);

        HBox charts = new HBox(15, pieChart, barChart, lineChart);
        root.getChildren().addAll(
            new javafx.scene.control.Label("Statistics Dashboard"),
            table, charts
        );

        Scene scene = new Scene(root, 900, 650);
        stage.setTitle("Stats Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
