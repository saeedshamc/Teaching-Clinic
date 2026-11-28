# جلسه ۶۷ — JavaFX Setup و اولین برنامه

## اهداف یادگیری
- JavaFX را به‌عنوان modern Java GUI بشناسید
- `Application`, `Stage`, `Scene` را بفهمید
- JavaFX SDK را نصب و راه‌اندازی کنید
- اولین JavaFX app را اجرا کنید

## پیش‌نیازها
- جلسهٔ ۶۶ (Swing Capstone)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### JavaFX — modern Java GUI

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyApp extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        root.getChildren().add(new Label("Welcome to JavaFX"));
        root.getChildren().add(new Button("Click Me"));

        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("My JavaFX App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

### JavaFX SDK نصب

```bash
# Maven dependency (pom.xml)
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>21</version>
</dependency>

# یا Gradle (build.gradle)
implementation 'org.openjfx:javafx-controls:21'
```

### ساختار JavaFX App

| Component | کاربرد |
|---|---|
| `Application` | main class — `start(Stage)` و `main()` |
| `Stage` | پنجره |
| `Scene` | content container |
| `Node` | UI element (Button, Label, ...) |
| `Parent` | container (VBox, HBox, BorderPane) |

### Layout Containers

```java
VBox vbox = new VBox(10);           // عمودی
HBox hbox = new HBox(10);           // افقی
BorderPane border = new BorderPane(); // NORTH/SOUTH/EAST/WEST/CENTER
GridPane grid = new GridPane();     // grid
FlowPane flow = new FlowPane();     // flow
StackPane stack = new StackPane();  // روی هم
```

## کد مهم

`src/JavaFXSetupDemo.java` — اولین JavaFX app با VBox و Button.

## خطاهای رایج
- JavaFX SDK را نصب نکردن → `ClassNotFoundException`.
- `launch()` را فراموش کردن → app اجرا نمی‌شود.
- `start()` را override نکردن → compile error.
- `Stage` را مستقیم instantiate کردن → باید از `Application` بیاید.

## جمع‌بندی
- JavaFX = modern Java GUI — Scene Graph، CSS-like styling.
- `Application` → `Stage` → `Scene` → `Node`.
- Layout containers برای UI structure.

**جلسهٔ بعد:** JavaFX Controls.
