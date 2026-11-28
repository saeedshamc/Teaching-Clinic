// JavaFXSetupDemo.java — اولین JavaFX app با VBox و Button
// ⚠️ نیازمند JavaFX SDK — برای کامپایل: javac --module-path /path/to/javafx/lib --add-modules javafx.controls
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXSetupDemo extends Application {

    @Override
    public void start(Stage stage) {
        // ---------- VBox — عمودی ----------
        VBox root = new VBox(10);   // spacing = 10
        root.setStyle("-fx-padding: 20;");

        // ---------- Controls ----------
        Label title = new Label("Welcome to JavaFX!");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button btn = new Button("Click Me");
        btn.setOnAction(e -> {
            System.out.println("Button clicked!");
        });

        Label status = new Label("Ready");

        // ---------- Add to VBox ----------
        root.getChildren().addAll(title, btn, status);

        // ---------- Scene ----------
        Scene scene = new Scene(root, 300, 200);

        // ---------- Stage ----------
        stage.setTitle("JavaFX Setup Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
