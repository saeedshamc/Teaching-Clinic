# جلسه ۷۰ — JavaFX Events و CSS

## اهداف یادگیری
- Event handling در JavaFX را بشناسید (`setOnAction`, `addEventHandler`, `EventFilter`)
- Mouse events، keyboard events، drag & drop را بفهمید
- CSS-like styling در JavaFX را به‌کار ببرید
- pseudo-classes (`:hover`, `:pressed`, `:focused`) را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۶۹ (JavaFX Layout و FXML)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Event Handling

```java
// Action event — برای buttons
btn.setOnAction(e -> {
    System.out.println("Clicked");
});

// Mouse event
btn.setOnMouseClicked(e -> {
    System.out.println("Mouse clicked at " + e.getX() + "," + e.getY());
});

// Keyboard event
scene.setOnKeyPressed(e -> {
    System.out.println("Key: " + e.getCode());
});

// Event filter — قبل از event handler
btn.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
    System.out.println("Filter: before handler");
});
```

### CSS-like Styling

```java
// inline style
btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

// CSS class
btn.getStyleClass().add("primary-button");

// pseudo-classes — در CSS file
// .primary-button:hover { -fx-background-color: #45a049; }
// .primary-button:pressed { -fx-background-color: #3d8b40; }
```

### CSS File

```css
/* style.css */
.primary-button {
    -fx-background-color: #4CAF50;
    -fx-text-fill: white;
    -fx-font-size: 14px;
    -fx-padding: 10 20;
    -fx-background-radius: 5;
}

.primary-button:hover {
    -fx-background-color: #45a049;
}

.primary-button:pressed {
    -fx-background-color: #3d8b40;
}

.text-field {
    -fx-border-color: #ccc;
    -fx-border-radius: 5;
    -fx-padding: 5;
}
```

### Drag & Drop

```java
btn.setOnDragDetected(e -> {
    Dragboard db = btn.startDragAndDrop(TransferMode.MOVE);
    ClipboardContent content = new ClipboardContent();
    content.putString("data");
    db.setContent(content);
    e.consume();
});

panel.setOnDragOver(e -> {
    if (e.getGestureSource() != panel) {
        e.acceptTransferModes(TransferMode.MOVE);
    }
    e.consume();
});

panel.setOnDrop(e -> {
    if (e.getGestureSource() != panel) {
        String data = (String) e.getDragboard().getString();
        System.out.println("Dropped: " + data);
        e.setDropCompleted(true);
    }
    e.consume();
});
```

## کد مهم

`src/JavaFXEventsCssDemo.java` — events، CSS styling، pseudo-classes، drag & drop.

## خطاهای رایج
- `setOnAction` را برای non-button استفاده کردن → بعضی controls این را ندارند.
- CSS class را فراموش کردن → `getStyleClass().add("class")`.
- pseudo-class را در inline style استفاده کردن → باید در CSS file باشد.
- drag & drop بدون `consume()` → event propagate می‌شود.

## جمع‌بندی
- `setOnAction` برای actions، `setOnMouseClicked`/`setOnKeyPressed` برای mouse/keyboard.
- CSS-like styling با `setStyle` و `getStyleClass`.
- Pseudo-classes برای interactive states.

**جلسهٔ بعد:** JavaFX Tables و Charts.
