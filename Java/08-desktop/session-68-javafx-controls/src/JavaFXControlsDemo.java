// JavaFXControlsDemo.java — Button، TextField، CheckBox، ComboBox، ListView، events
// نیازمند JavaFX SDK
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXControlsDemo extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20;");

        // ---------- Label ----------
        Label title = new Label("JavaFX Controls Demo");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // ---------- TextField ----------
        TextField tf = new TextField();
        tf.setPromptText("Enter text...");

        // ---------- PasswordField ----------
        PasswordField pf = new PasswordField();
        pf.setPromptText("Password...");

        // ---------- CheckBox ----------
        CheckBox cb = new CheckBox("Enable feature");
        cb.selectedProperty().addListener((obs, old, newVal) -> {
            System.out.println("Checkbox: " + newVal);
        });

        // ---------- ComboBox ----------
        ComboBox<String> combo = new ComboBox<>();
        combo.getItems().addAll("Option A", "Option B", "Option C");
        combo.setValue("Option A");
        combo.getSelectionModel().selectedItemProperty().addListener((obs, old, newVal) -> {
            System.out.println("Combo selected: " + newVal);
        });

        // ---------- ListView ----------
        ObservableList<String> items = FXCollections.observableArrayList(
            "Item 1", "Item 2", "Item 3", "Item 4", "Item 5"
        );
        ListView<String> listView = new ListView<>(items);
        listView.setPrefHeight(120);
        listView.getSelectionModel().selectedItemProperty().addListener((obs, old, newVal) -> {
            System.out.println("ListView selected: " + newVal);
        });

        // ---------- Button ----------
        Button btn = new Button("Submit");
        btn.setOnAction(e -> {
            String text = tf.getText();
            String pass = pf.getText();
            boolean checked = cb.isSelected();
            String selected = combo.getValue();
            String listItem = listView.getSelectionModel().getSelectedItem();
            System.out.println("Text: " + text);
            System.out.println("Password length: " + pass.length());
            System.out.println("Checked: " + checked);
            System.out.println("Combo: " + selected);
            System.out.println("List: " + listItem);
        });

        root.getChildren().addAll(title, tf, pf, cb, combo, listView, btn);

        Scene scene = new Scene(root, 350, 450);
        stage.setTitle("JavaFX Controls Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
