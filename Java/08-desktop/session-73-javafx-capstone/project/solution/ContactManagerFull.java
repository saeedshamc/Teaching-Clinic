// ContactManagerFull.java — پاسخ کامل پروژهٔ جلسه ۷۳
// نیازمند JavaFX SDK
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ContactManagerFull extends Application {

    public static class Contact {
        private final StringProperty name = new SimpleStringProperty();
        private final StringProperty phone = new SimpleStringProperty();
        private final StringProperty email = new SimpleStringProperty();
        private final StringProperty category = new SimpleStringProperty();

        Contact(String name, String phone, String email, String category) {
            setName(name); setPhone(phone); setEmail(email); setCategory(category);
        }
        public String getName() { return name.get(); }
        public void setName(String v) { name.set(v); }
        public StringProperty nameProperty() { return name; }
        public String getPhone() { return phone.get(); }
        public void setPhone(String v) { phone.set(v); }
        public StringProperty phoneProperty() { return phone; }
        public String getEmail() { return email.get(); }
        public void setEmail(String v) { email.set(v); }
        public StringProperty emailProperty() { return email; }
        public String getCategory() { return category.get(); }
        public void setCategory(String v) { category.set(v); }
        public StringProperty categoryProperty() { return category; }
    }

    private ObservableList<Contact> contacts;
    private TableView<Contact> table;
    private PieChart pieChart;
    private TextField searchField;
    private ComboBox<String> categoryCombo;

    @Override
    public void start(Stage stage) {
        contacts = FXCollections.observableArrayList();
        // sample data
        contacts.add(new Contact("Ali", "123", "ali@mail.com", "Friends"));
        contacts.add(new Contact("Sara", "456", "sara@mail.com", "Family"));
        contacts.add(new Contact("Reza", "789", "reza@mail.com", "Work"));

        BorderPane root = new BorderPane();
        root.setStyle("-fx-padding: 10;");

        // ---------- Top toolbar ----------
        HBox toolbar = new HBox(10);
        searchField = new TextField();
        searchField.setPromptText("Search...");
        categoryCombo = new ComboBox<>();
        categoryCombo.getItems().addAll("All", "Friends", "Family", "Work");
        categoryCombo.setValue("All");
        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");
        toolbar.getChildren().addAll(searchField, categoryCombo, addBtn, deleteBtn);
        root.setTop(toolbar);

        // ---------- Table ----------
        table = new TableView<>(contacts);
        TableColumn<Contact, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(c -> c.getValue().nameProperty());
        TableColumn<Contact, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(c -> c.getValue().phoneProperty());
        TableColumn<Contact, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(c -> c.getValue().emailProperty());
        TableColumn<Contact, String> catCol = new TableColumn<>("Category");
        catCol.setCellValueFactory(c -> c.getValue().categoryProperty());
        table.getColumns().addAll(nameCol, phoneCol, emailCol, catCol);
        table.setPrefHeight(250);

        // ---------- Right: PieChart ----------
        pieChart = new PieChart();
        pieChart.setTitle("By Category");
        pieChart.setPrefSize(200, 200);
        updatePieChart();

        HBox center = new HBox(10, table, pieChart);
        root.setCenter(center);

        // ---------- Events ----------
        addBtn.setOnAction(e -> showAddDialog());
        deleteBtn.setOnAction(e -> {
            Contact selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                contacts.remove(selected);
                updatePieChart();
            }
        });

        searchField.textProperty().addListener((obs, old, newVal) -> filterContacts());
        categoryCombo.getSelectionModel().selectedItemProperty().addListener((obs, old, newVal) -> filterContacts());

        Scene scene = new Scene(root, 900, 500);
        stage.setTitle("Contact Manager Full");
        stage.setScene(scene);
        stage.show();
    }

    void updatePieChart() {
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        Map<String, Integer> counts = new HashMap<>();
        for (Contact c : contacts) {
            counts.merge(c.getCategory(), 1, Integer::sum);
        }
        counts.forEach((cat, count) -> pieData.add(new PieChart.Data(cat, count)));
        pieChart.setData(pieData);
    }

    void filterContacts() {
        String search = searchField.getText().toLowerCase();
        String category = categoryCombo.getValue();
        ObservableList<Contact> filtered = FXCollections.observableArrayList();
        for (Contact c : contacts) {
            boolean matchSearch = c.getName().toLowerCase().contains(search) ||
                                 c.getEmail().toLowerCase().contains(search);
            boolean matchCategory = category.equals("All") || c.getCategory().equals(category);
            if (matchSearch && matchCategory) {
                filtered.add(c);
            }
        }
        table.setItems(filtered);
    }

    void showAddDialog() {
        Dialog<Contact> dialog = new Dialog<>();
        dialog.setTitle("Add Contact");
        dialog.setHeaderText("Enter contact details");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        ComboBox<String> catCombo = new ComboBox<>();
        catCombo.getItems().addAll("Friends", "Family", "Work");
        catCombo.setValue("Friends");

        VBox content = new VBox(10, nameField, phoneField, emailField, catCombo);
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                return new Contact(nameField.getText(), phoneField.getText(),
                                 emailField.getText(), catCombo.getValue());
            }
            return null;
        });

        dialog.showAndWait().ifPresent(contact -> {
            contacts.add(contact);
            updatePieChart();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
