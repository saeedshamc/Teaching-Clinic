# جلسه ۷۲ — JavaFX Concurrency

## اهداف یادگیری
- `Task`, `Service`, `Worker` را برای background work بشناسید
- `Platform.runLater` را برای UI thread safety بفهمید
- `CompletableFuture` با JavaFX را به‌کار ببرید
- `ExecutorService` برای async tasks را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۷۱ (JavaFX Tables و Charts)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### JavaFX Threading Model

- **JavaFX Application Thread** — UI thread — همهٔ UI operations باید روی این thread باشد.
- **Background threads** — برای long-running tasks — نباید مستقیم UI را دست‌کاری کنند.

### Task — background work

```java
Task<Void> task = new Task<Void>() {
    @Override
    protected Void call() throws Exception {
        // background work
        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
            updateProgress(i, 10);
            updateMessage("Processing " + i);
        }
        return null;
    }
};

// UI bindings
progressBar.progressProperty().bind(task.progressProperty());
statusLabel.textProperty().bind(task.messageProperty());

// Run on background thread
new Thread(task).start();
```

### Service — reusable background task

```java
Service<Void> service = new Service<Void>() {
    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // work
                return null;
            }
        };
    }
};

service.setOnSucceeded(e -> {
    System.out.println("Done!");
});
service.start();
```

### Platform.runLater — UI thread safety

```java
// از background thread به UI thread
new Thread(() -> {
    // background work
    Platform.runLater(() -> {
        // UI update — safe
        label.setText("Done!");
    });
}).start();
```

### ExecutorService — برای complex async

```java
ExecutorService executor = Executors.newFixedThreadPool(3);

executor.submit(() -> {
    // background work
    Platform.runLater(() -> {
        // UI update
    });
});
```

## کد مهم

`src/JavaFXConcurrencyDemo.java` — Task، Service، Platform.runLater، ExecutorService.

## خطاهای رایج
- UI را روی background thread دست‌کاری کردن → `Not on FX application thread`.
- `Platform.runLater` را فراموش کردن → UI issues.
- `Task` را بدون `new Thread(task).start()` اجرا کردن → اجرا نمی‌شود.
- `Service` را بدون `setOnSucceeded` استفاده کردن → نتیجه را نمی‌گیرید.

## جمع‌بندی
- JavaFX Application Thread برای UI، background threads برای work.
- `Task` برای simple، `Service` برای reusable، `ExecutorService` برای complex.
- `Platform.runLater` برای UI updates از background.

**جلسهٔ بعد:** JavaFX Capstone.
