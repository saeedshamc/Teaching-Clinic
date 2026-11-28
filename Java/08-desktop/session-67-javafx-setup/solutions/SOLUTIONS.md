# پاسخ تمرین‌های جلسه ۶۷

## آسان

**۱ و ۲.**
```java
public class MyApp extends Application {
    @Override public void start(Stage stage) {
        VBox root = new VBox(10);
        root.getChildren().add(new Label("Hello"));
        root.getChildren().add(new Button("Click"));
        stage.setScene(new Scene(root, 300, 200));
        stage.show();
    }
    public static void main(String[] args) { launch(args); }
}
```

## متوسط

**۳ و ۴.**
```java
Scene scene = new Scene(root, 300, 200);
stage.setTitle("My App");
stage.setScene(scene);
stage.show();

btn.setOnAction(e -> System.out.println("Clicked"));
```

## سخت

**۵ و ۶.**
```java
HBox hbox = new HBox(10);
BorderPane border = new BorderPane();
border.setTop(new Label("Top"));
border.setCenter(new Label("Center"));

label.setStyle("-fx-font-size: 18px; -fx-text-fill: blue;");
```
