# پاسخ تمرین‌های جلسه ۸۶

## آسان

**۱ و ۲.**
```java
public class MyWorker extends Worker {
    @Override
    public Result doWork() {
        // background work
        return Result.success();
    }
}

WorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();
WorkManager.getInstance(context).enqueue(workRequest);
```

## متوسط

**۳ و ۴.**
```java
PeriodicWorkRequest periodic = new PeriodicWorkRequest.Builder(
    MyWorker.class, 15, TimeUnit.MINUTES
).build();

Constraints constraints = new Constraints.Builder()
    .setRequiredNetworkType(NetworkType.CONNECTED)
    .build();
```

## سخت

**۵ و ۶.**
```java
Data inputData = new Data.Builder()
    .putString("key", "value")
    .build();

WorkRequest work = new OneTimeWorkRequest.Builder(MyWorker.class)
    .setInputData(inputData)
    .build();

WorkManager.getInstance(context).cancelAllWork();
```
