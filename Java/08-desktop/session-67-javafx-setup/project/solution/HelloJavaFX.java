// HelloJavaFX.java — پاسخ پروژهٔ جلسه ۶۷
// نیازمند JavaFX SDK
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20;");

        Label title = new Label("Welcome to JavaFX");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        Button greetBtn = new Button("Greet");
        Label greeting = new Label("");

        greetBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                greeting.setText("Hello, " + name + "!");
            } else {
                greeting.setText("Please enter a name");
            }
        });

        root.getChildren().addAll(title, nameField, greetBtn, greeting);

        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("Hello JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
