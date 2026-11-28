// JavaFXEventsCssDemo.java — events، CSS styling، pseudo-classes
// نیازمند JavaFX SDK
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXEventsCssDemo extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20;");

        // ---------- Button with CSS ----------
        Button btn = new Button("Click Me");
        btn.getStyleClass().add("primary-button");
        btn.setOnAction(e -> {
            System.out.println("Button clicked!");
        });

        // ---------- Button with inline style ----------
        Button styledBtn = new Button("Styled");
        styledBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px;");
        styledBtn.setOnMouseClicked(e -> {
            System.out.println("Mouse clicked at " + e.getX() + "," + e.getY());
        });

        // ---------- TextField with keyboard event ----------
        TextField tf = new TextField();
        tf.setPromptText("Type and press Enter...");
        tf.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                System.out.println("Enter pressed: " + tf.getText());
            }
        });

        // ---------- CheckBox with listener ----------
        CheckBox cb = new CheckBox("Enable notifications");
        cb.selectedProperty().addListener((obs, old, newVal) -> {
            System.out.println("Notifications: " + (newVal ? "ON" : "OFF"));
        });

        // ---------- ComboBox ----------
        ComboBox<String> combo = new ComboBox<>();
        combo.getItems().addAll("Option A", "Option B", "Option C");
        combo.setValue("Option A");
        combo.getSelectionModel().selectedItemProperty().addListener((obs, old, newVal) -> {
            System.out.println("Selected: " + newVal);
        });

        // ---------- Drag & Drop demo ----------
        Label dropTarget = new Label("Drop here");
        dropTarget.setStyle("-fx-border-color: #ccc; -fx-padding: 20; -fx-background-color: #f5f5f5;");
        dropTarget.setOnDragOver(e -> {
            if (e.getGestureSource() != dropTarget) {
                e.acceptTransferModes(TransferMode.ANY);
            }
            e.consume();
        });
        dropTarget.setOnDrop(e -> {
            if (e.getGestureSource() != dropTarget) {
                Dragboard db = e.getDragboard();
                if (db.hasString()) {
                    dropTarget.setText("Dropped: " + db.getString());
                    e.setDropCompleted(true);
                }
            }
            e.consume();
        });

        // ---------- Draggable button ----------
        Button dragBtn = new Button("Drag me");
        dragBtn.setOnDragDetected(e -> {
            Dragboard db = dragBtn.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString("Dragged from button");
            db.setContent(content);
            e.consume();
        });

        root.getChildren().addAll(
            new Label("JavaFX Events & CSS Demo"),
            btn, styledBtn, tf, cb, combo, dragBtn, dropTarget
        );

        Scene scene = new Scene(root, 400, 450);

        // ---------- CSS file ----------
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setTitle("JavaFX Events & CSS");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
