# جلسه ۸۶ — WorkManager: Background Tasks

## اهداف یادگیری
- `WorkManager` را برای deferrable background work بشناسید
- `Worker`, `CoroutineWorker`, `ListenableWorker` را بفهمید
- `WorkRequest`, `PeriodicWorkRequest` را به‌کار ببرید
- constraints (`NetworkType`, `BatteryNotLow`) را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۸۵ (Retrofit)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### WorkManager — deferrable work

```gradle
dependencies {
    implementation 'androidx.work:work-runtime:2.8.1'
}
```

### Worker — background task

```java
public class MyWorker extends Worker {
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        // background work
        return Result.success();
    }
}
```

### WorkRequest — one-time work

```java
WorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
    .build();

WorkManager.getInstance(context).enqueue(workRequest);
```

### PeriodicWorkRequest — recurring work

```java
PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(
    MyWorker.class,
    15, TimeUnit.MINUTES   // minimum 15 minutes
).build();

WorkManager.getInstance(context).enqueue(periodicWork);
```

### Constraints — conditions for work

```java
Constraints constraints = new Constraints.Builder()
    .setRequiredNetworkType(NetworkType.CONNECTED)
    .setRequiresBatteryNotLow(true)
    .setRequiresCharging(false)
    .build();

WorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
    .setConstraints(constraints)
    .build();
```

### Worker with input data

```java
Data inputData = new Data.Builder()
    .putString("key", "value")
    .putInt("number", 42)
    .build();

WorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
    .setInputData(inputData)
    .build();

// In Worker
String key = getInputData().getString("key");
```

## کد مهم

`src/MyWorker.java` — background worker.
`src/WorkManagerActivity.java` — enqueue work.

## خطاهاعج
- `PeriodicWorkRequest` با interval کمتر از ۱۵ دقیقه → `IllegalArgumentException`.
- `doWork` را روی main thread اجرا کردن → `NetworkOnMainThreadException`.
- `Result.success()` را فراموش کردن → work fails.
- `WorkManager` را بدون `getInstance` استفاده کردن → crash.

## جمع‌بندی
- WorkManager برای deferrable background work — survives app restarts.
- `Worker` برای background tasks، `WorkRequest` برای scheduling.
- Constraints برای conditions (network, battery, charging).

**جلسهٔ بعد:** Navigation Component.
