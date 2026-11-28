// JavaFXConcurrencyDemo.java — Task، Service، Platform.runLater، ExecutorService
// نیازمند JavaFX SDK
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaFXConcurrencyDemo extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20;");

        Label status = new Label("Ready");
        ProgressBar progress = new ProgressBar();
        progress.setPrefWidth(300);

        Button taskBtn = new Button("Run Task");
        Button serviceBtn = new Button("Run Service");
        Button executorBtn = new Button("Run Executor");

        // ---------- Task ----------
        taskBtn.setOnAction(e -> {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    for (int i = 0; i <= 10; i++) {
                        Thread.sleep(300);
                        updateProgress(i, 10);
                        updateMessage("Task: " + i + "/10");
                    }
                    return null;
                }
            };

            progress.progressProperty().unbind();
            progress.progressProperty().bind(task.progressProperty());
            status.textProperty().unbind();
            status.textProperty().bind(task.messageProperty());

            task.setOnSucceeded(ev -> status.setText("Task completed!"));
            new Thread(task).start();
        });

        // ---------- Service ----------
        serviceBtn.setOnAction(e -> {
            Task<Void> serviceTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    for (int i = 0; i <= 5; i++) {
                        Thread.sleep(500);
                        updateProgress(i, 5);
                        updateMessage("Service: " + i + "/5");
                    }
                    return null;
                }
            };

            progress.progressProperty().unbind();
            progress.progressProperty().bind(serviceTask.progressProperty());
            status.textProperty().unbind();
            status.textProperty().bind(serviceTask.messageProperty());

            serviceTask.setOnSucceeded(ev -> status.setText("Service completed!"));
            new Thread(serviceTask).start();
        });

        // ---------- ExecutorService ----------
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executorBtn.setOnAction(e -> {
            executor.submit(() -> {
                for (int i = 0; i <= 10; i++) {
                    try { Thread.sleep(200); } catch (InterruptedException ex) {}
                    final int idx = i;
                    Platform.runLater(() -> {
                        progress.setProgress(idx / 10.0);
                        status.setText("Executor: " + idx + "/10");
                    });
                }
                Platform.runLater(() -> status.setText("Executor completed!"));
            });
        });

        root.getChildren().addAll(
            new Label("JavaFX Concurrency Demo"),
            status, progress,
            taskBtn, serviceBtn, executorBtn
        );

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("JavaFX Concurrency");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
