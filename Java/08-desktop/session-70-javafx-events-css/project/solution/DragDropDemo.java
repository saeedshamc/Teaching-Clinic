// DragDropDemo.java — پاسخ پروژهٔ جلسه ۷۰
// نیازمند JavaFX SDK
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DragDropDemo extends Application {

    @Override
    public void start(Stage stage) {
        ObservableList<String> available = FXCollections.observableArrayList("Item 1", "Item 2", "Item 3", "Item 4");
        ObservableList<String> selected = FXCollections.observableArrayList();

        ListView<String> availableList = new ListView<>(available);
        availableList.setPrefSize(150, 200);
        ListView<String> selectedList = new ListView<>(selected);
        selectedList.setPrefSize(150, 200);

        // ---------- Drag from available ----------
        availableList.setOnDragDetected(e -> {
            if (!availableList.getSelectionModel().isEmpty()) {
                Dragboard db = availableList.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(availableList.getSelectionModel().getSelectedItem());
                db.setContent(content);
                e.consume();
            }
        });

        // ---------- Drop on selected ----------
        selectedList.setOnDragOver(e -> {
            if (e.getGestureSource() != selectedList) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });

        selectedList.setOnDrop(e -> {
            if (e.getGestureSource() != selectedList) {
                Dragboard db = e.getDragboard();
                if (db.hasString()) {
                    String item = db.getString();
                    selected.add(item);
                    available.remove(item);
                    e.setDropCompleted(true);
                }
            }
            e.consume();
        });

        // ---------- Drop on available (back) ----------
        availableList.setOnDragOver(e -> {
            if (e.getGestureSource() != availableList) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });

        availableList.setOnDrop(e -> {
            if (e.getGestureSource() != availableList) {
                Dragboard db = e.getDragboard();
                if (db.hasString()) {
                    String item = db.getString();
                    available.add(item);
                    selected.remove(item);
                    e.setDropCompleted(true);
                }
            }
            e.consume();
        });

        VBox leftBox = new VBox(5, new Label("Available"), availableList);
        VBox rightBox = new VBox(5, new Label("Selected"), selectedList);
        HBox root = new HBox(20, leftBox, rightBox);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Drag & Drop Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
