// RegistrationJavaFX.java — پاسخ پروژهٔ جلسه ۶۸
// نیازمند JavaFX SDK
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegistrationJavaFX extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20;");

        // ---------- Fields ----------
        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");

        CheckBox termsBox = new CheckBox("I agree to terms");

        ComboBox<String> countryCombo = new ComboBox<>();
        countryCombo.getItems().addAll("Iran", "USA", "UK", "Other");
        countryCombo.setValue("Iran");

        // ---------- Users list ----------
        ObservableList<String> users = FXCollections.observableArrayList();
        ListView<String> userList = new ListView<>(users);
        userList.setPrefHeight(100);

        // ---------- Submit ----------
        Button submitBtn = new Button("Submit");
        Label status = new Label("");

        submitBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String pass = passField.getText();
            boolean terms = termsBox.isSelected();
            String country = countryCombo.getValue();

            if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                status.setText("All fields required");
                return;
            }
            if (!terms) {
                status.setText("You must agree to terms");
                return;
            }

            users.add(name + " (" + email + ") - " + country);
            status.setText("Registered: " + name);
            nameField.clear(); emailField.clear(); passField.clear();
        });

        root.getChildren().addAll(
            new Label("Registration"),
            nameField, emailField, passField, termsBox, countryCombo,
            submitBtn, status, new Label("Registered users:"), userList
        );

        Scene scene = new Scene(root, 400, 500);
        stage.setTitle("Registration JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
