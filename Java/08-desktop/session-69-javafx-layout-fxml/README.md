# جلسه ۶۹ — JavaFX Layout و FXML

## اهداف یادگیری
- Layout containers JavaFX را بشناسید (VBox, HBox, BorderPane, GridPane, FlowPane)
- `Insets`, `Hgrow`, `Vgrow` را برای responsive layout بفهمید
- FXML برای declarative UI را بشناسید
- `FXMLLoader` و controller pattern را به‌کار ببرید

## پیش‌نیازها
- جلسهٔ ۶۸ (JavaFX Controls)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Layout Containers

```java
// VBox — عمودی
VBox vbox = new VBox(10);   // spacing
vbox.setPadding(new Insets(10));
vbox.getChildren().addAll(new Label("A"), new Button("B"));

// HBox — افقی
HBox hbox = new HBox(10);
hbox.getChildren().addAll(new Label("A"), new Button("B"));

// BorderPane — NORTH/SOUTH/EAST/WEST/CENTER
BorderPane border = new BorderPane();
border.setTop(new Label("Top"));
border.setCenter(new Label("Center"));
border.setBottom(new Label("Bottom"));

// GridPane — grid با rows/columns
GridPane grid = new GridPane();
grid.setHgap(10);
grid.setVgap(10);
grid.add(new Label("Name:"), 0, 0);
grid.add(new TextField(), 1, 0);
grid.add(new Label("Email:"), 0, 1);
grid.add(new TextField(), 1, 1);

// FlowPane — flow layout
FlowPane flow = new FlowPane(10, 10);
flow.getChildren().addAll(new Button("A"), new Button("B"), new Button("C"));
```

### Responsive Layout — Hgrow/Vgrow

```java
VBox vbox = new VBox();
TextArea ta = new TextArea();
VBox.setVgrow(ta, Priority.ALWAYS);   // TextArea فضای باقی‌مانده را می‌گیرد

HBox hbox = new HBox();
TextField tf = new TextField();
HBox.setHgrow(tf, Priority.ALWAYS);   // TextField فضای باقی‌مانده را می‌گیرد
```

### FXML — declarative UI

```xml
<!-- login.fxml -->
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<BorderPane xmlns="http://javafx.com/javafx"
            xmlns:fx="http://javafx.com/fxml"
            fx:controller="com.example.LoginController">
    <top>
        <Label text="Login" style="-fx-font-size: 18px;"/>
    </top>
    <center>
        <VBox spacing="10">
            <TextField fx:id="usernameField" promptText="Username"/>
            <PasswordField fx:id="passwordField" promptText="Password"/>
            <Button text="Login" onAction="#handleLogin"/>
        </VBox>
    </center>
</BorderPane>
```

### Controller — code behind FXML

```java
public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String user = usernameField.getText();
        String pass = passwordField.getText();
        System.out.println("Login: " + user);
    }
}
```

## کد مهم

`src/JavaFXLayoutDemo.java` — layout containers، responsive، FXML example.
`src/LoginController.java` — controller برای FXML.

## خطاهای رایج
- `GridPane` بدون `setHgap`/`setVgap` → کامپوننت‌ها به‌هم می‌چسبند.
- `Hgrow`/`Vgrow` را فراموش کردن → کامپوننت‌ها resize نمی‌شوند.
- FXML controller را پیدا نکردن → `NullPointerException`.
- `@FXML` annotation را فراموش کردن → fields inject نمی‌شوند.

## جمع‌بندی
- VBox/HBox برای simple، BorderPane برای structure، GridPane برای forms.
- FXML برای declarative UI، controller برای logic.
- Hgrow/Vgrow برای responsive.

**جلسهٔ بعد:** JavaFX Events و CSS.
