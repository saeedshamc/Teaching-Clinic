# پاسخ تمرین‌های جلسه ۷۲

## آسان

**۱ و ۲.**
```java
Task<Void> task = new Task<Void>() {
    @Override protected Void call() throws Exception {
        for (int i = 0; i <= 10; i++) {
            Thread.sleep(300);
            updateProgress(i, 10);
            updateMessage("Processing " + i);
        }
        return null;
    }
};
new Thread(task).start();

// UI update از background:
Platform.runLater(() -> label.setText("Done!"));
```

## متوسط

**۳ و ۴.**
```java
Service<Void> service = new Service<Void>() {
    @Override protected Task<Void> createTask() {
        return new Task<Void>() { /* ... */ };
    }
};
service.start();

ExecutorService executor = Executors.newFixedThreadPool(3);
executor.submit(() -> { /* background work */ });
```

## سخت

**۵ و ۶.**
```java
CompletableFuture.supplyAsync(() -> fetchData())
    .thenAccept(result -> Platform.runLater(() -> updateUI(result)));

task.setOnFailed(e -> {
    status.setText("Failed: " + task.getException().getMessage());
});
```
