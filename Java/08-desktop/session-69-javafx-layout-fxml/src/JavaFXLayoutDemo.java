// JavaFXLayoutDemo.java — layout containers، responsive
// نیازمند JavaFX SDK
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class JavaFXLayoutDemo extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // ---------- Top ----------
        HBox topBar = new HBox(10);
        topBar.getChildren().addAll(
            new Label("Dashboard"),
            new Button("Settings"),
            new Button("Logout")
        );
        root.setTop(topBar);

        // ---------- Left sidebar ----------
        VBox sidebar = new VBox(5);
        sidebar.setPadding(new Insets(10));
        sidebar.setStyle("-fx-background-color: #f0f0f0;");
        sidebar.getChildren().addAll(
            new Button("Dashboard"),
            new Button("Reports"),
            new Button("Users"),
            new Button("Settings")
        );
        VBox.setVgrow(sidebar, Priority.ALWAYS);
        root.setLeft(sidebar);

        // ---------- Center — GridPane form ----------
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        form.add(new Label("Name:"), 0, 0);
        form.add(new TextField(), 1, 0);
        form.add(new Label("Email:"), 0, 1);
        form.add(new TextField(), 1, 1);
        form.add(new Label("Password:"), 0, 2);
        form.add(new PasswordField(), 1, 2);
        form.add(new Button("Submit"), 1, 3);

        GridPane.setHgrow(form, Priority.ALWAYS);
        root.setCenter(form);

        // ---------- Bottom ----------
        HBox bottomBar = new HBox(10);
        bottomBar.setPadding(new Insets(10));
        bottomBar.getChildren().addAll(
            new Label("Status: Ready"),
            new Button("Help")
        );
        root.setBottom(bottomBar);

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("JavaFX Layout Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
