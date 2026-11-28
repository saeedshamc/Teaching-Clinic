// ContactManagerFX.java — مینی‌پروژهٔ جمع‌بندی فاز ۵ (بخش JavaFX)
// نیازمند JavaFX SDK
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ContactManagerFX extends Application {

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

    @Override
    public void start(Stage stage) {
        // ---------- Data ----------
        ObservableList<Contact> contacts = FXCollections.observableArrayList(
            new Contact("Ali", "123-4567", "ali@mail.com", "Friends"),
            new Contact("Sara", "987-6543", "sara@mail.com", "Family"),
            new Contact("Reza", "555-1234", "reza@mail.com", "Work"),
            new Contact("Mona", "111-2222", "mona@mail.com", "Friends")
        );

        // ---------- Table ----------
        TableView<Contact> table = new TableView<>(contacts);
        TableColumn<Contact, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(c -> c.getValue().nameProperty());
        TableColumn<Contact, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(c -> c.getValue().phoneProperty());
        TableColumn<Contact, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(c -> c.getValue().emailProperty());
        TableColumn<Contact, String> catCol = new TableColumn<>("Category");
        catCol.setCellValueFactory(c -> c.getValue().categoryProperty());
        table.getColumns().addAll(nameCol, phoneCol, emailCol, catCol);
        table.setPrefHeight(200);

        // ---------- Form ----------
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        ComboBox<String> categoryCombo = new ComboBox<>();
        categoryCombo.getItems().addAll("Friends", "Family", "Work");
        categoryCombo.setValue("Friends");

        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");

        addBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String cat = categoryCombo.getValue();
            if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                contacts.add(new Contact(name, phone, email, cat));
                nameField.clear(); phoneField.clear(); emailField.clear();
            }
        });

        deleteBtn.setOnAction(e -> {
            Contact selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                contacts.remove(selected);
            }
        });

        // ---------- PieChart ----------
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
            new PieChart.Data("Friends", 2),
            new PieChart.Data("Family", 1),
            new PieChart.Data("Work", 1)
        );
        PieChart pieChart = new PieChart(pieData);
        pieChart.setTitle("By Category");
        pieChart.setPrefSize(200, 200);

        // ---------- Layout ----------
        VBox formBox = new VBox(5, nameField, phoneField, emailField, categoryCombo, addBtn, deleteBtn);
        HBox mainBox = new HBox(10, formBox, table, pieChart);
        mainBox.setStyle("-fx-padding: 10;");

        BorderPane root = new BorderPane();
        root.setTop(new Label("Contact Manager"));
        root.setCenter(mainBox);

        Scene scene = new Scene(root, 800, 400);
        stage.setTitle("Contact Manager JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
