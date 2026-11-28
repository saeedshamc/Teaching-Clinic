# پاسخ تمرین‌های جلسه ۶۹

## آسان

**۱ و ۲.**
```java
VBox vbox = new VBox(10);
HBox hbox = new HBox(10);
BorderPane border = new BorderPane();
border.setTop(new Label("Top"));
border.setCenter(new Label("Center"));

GridPane grid = new GridPane();
grid.setHgap(10); grid.setVgap(10);
grid.add(new Label("Name:"), 0, 0);
grid.add(new TextField(), 1, 0);
```

## متوسط

**۳ و ۴.**
```java
VBox.setVgrow(textArea, Priority.ALWAYS);
HBox.setHgrow(textField, Priority.ALWAYS);

// login.fxml
<BorderPane fx:controller="com.example.LoginController">
    <center><VBox>...</VBox></center>
</BorderPane>
```

## سخت

**۵ و ۶.**
```java
public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private void handleLogin() { ... }
}

FlowPane flow = new FlowPane(10, 10);
flow.getChildren().addAll(new Button("A"), new Button("B"));
```
