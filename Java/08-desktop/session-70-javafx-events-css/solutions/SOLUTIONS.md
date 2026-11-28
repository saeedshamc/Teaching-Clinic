# پاسخ تمرین‌های جلسه ۷۰

## آسان

**۱ و ۲.**
```java
btn.setOnAction(e -> System.out.println("Clicked"));
btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
```

## متوسط

**۳ و ۴.**
```java
btn.getStyleClass().add("primary-button");
// در style.css:
// .primary-button:hover { -fx-background-color: #45a049; }
// .primary-button:pressed { -fx-background-color: #3d8b40; }
```

## سخت

**۵ و ۶.**
```java
btn.setOnDragDetected(e -> {
    Dragboard db = btn.startDragAndDrop(TransferMode.ANY);
    ClipboardContent content = new ClipboardContent();
    content.putString("data");
    db.setContent(content);
});

target.setOnDrop(e -> {
    String data = e.getDragboard().getString();
    e.setDropCompleted(true);
});

// Event filter
btn.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
    System.out.println("Filter before handler");
});
```
