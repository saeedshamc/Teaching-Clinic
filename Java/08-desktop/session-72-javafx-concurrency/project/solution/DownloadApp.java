// DownloadApp.java — پاسخ پروژهٔ جلسه ۷۲
// نیازمند JavaFX SDK
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DownloadApp extends Application {

    private Task<Void> currentTask;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20;");

        Label title = new Label("File Downloader");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefWidth(300);

        Label statusLabel = new Label("Ready");
        Label fileLabel = new Label("file.zip");

        Button downloadBtn = new Button("Download");
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setDisable(true);

        downloadBtn.setOnAction(e -> {
            currentTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    for (int i = 0; i <= 100; i += 10) {
                        if (isCancelled()) break;
                        Thread.sleep(300);
                        updateProgress(i / 100.0, 1.0);
                        updateMessage("Downloading... " + i + "%");
                    }
                    return null;
                }
            };

            progressBar.progressProperty().unbind();
            progressBar.progressProperty().bind(currentTask.progressProperty());
            statusLabel.textProperty().unbind();
            statusLabel.textProperty().bind(currentTask.messageProperty());

            currentTask.setOnSucceeded(ev -> {
                statusLabel.setText("Download complete!");
                cancelBtn.setDisable(true);
                downloadBtn.setDisable(false);
            });

            currentTask.setOnFailed(ev -> {
                statusLabel.setText("Download failed: " + currentTask.getException().getMessage());
                cancelBtn.setDisable(true);
                downloadBtn.setDisable(false);
            });

            new Thread(currentTask).start();
            downloadBtn.setDisable(true);
            cancelBtn.setDisable(false);
        });

        cancelBtn.setOnAction(e -> {
            if (currentTask != null) {
                currentTask.cancel();
                statusLabel.setText("Cancelled");
                cancelBtn.setDisable(true);
                downloadBtn.setDisable(false);
            }
        });

        root.getChildren().addAll(title, fileLabel, progressBar, statusLabel, downloadBtn, cancelBtn);

        Scene scene = new Scene(root, 400, 250);
        stage.setTitle("File Downloader");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
