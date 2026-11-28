# جلسه ۹۱ — Android Services

## اهداف یادگیری
- `Service` را برای background work بدون UI بشناسید
- `startService`, `bindService`, `startForegroundService` را بفهمید
- `Service` lifecycle را یاد بگیرید
- `IntentService` و `JobIntentService` را به‌عنوان alternatives بشناسید

## پیش‌نیازها
- جلسهٔ ۹۰ (Jetpack Capstone)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Service — background component بدون UI

```xml
<!-- AndroidManifest.xml -->
<service android:name=".MyService" android:exported="false" />
```

### Started Service — برای one-time work

```java
public class MyService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // background work
        return START_STICKY;   // restart if killed
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;   // not bound
    }
}

// Start service
Intent intent = new Intent(this, MyService.class);
startService(intent);

// Stop service
stopService(intent);
```

### Bound Service — برای client-server

```java
public class MyService extends Service {
    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public String getData() {
        return "Data from service";
    }
}

// In Activity
private ServiceConnection connection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MyService.LocalBinder binder = (MyService.LocalBinder) service;
        myService = binder.getService();
    }
    @Override
    public void onServiceDisconnected(ComponentName name) {}
};

Intent intent = new Intent(this, MyService.class);
bindService(intent, connection, Context.BIND_AUTO_CREATE);
```

### Foreground Service — برای long-running work

```java
public class MyService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Create notification for foreground service
        Notification notification = createNotification();
        startForeground(1, notification);

        // background work
        return START_STICKY;
    }

    private Notification createNotification() {
        NotificationChannel channel = new NotificationChannel("service_channel", "Service", NotificationManager.IMPORTANCE_LOW);
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        return new Notification.Builder(this, "service_channel")
            .setContentTitle("My Service")
            .setContentText("Running in background")
            .setSmallIcon(R.drawable.ic_notification)
            .build();
    }
}

// Start foreground service
Intent intent = new Intent(this, MyService.class);
startForegroundService(intent);
```

## کد مهم

`src/MyService.java` — service با started/bound/foreground modes.
`src/ServiceActivity.java` — start/bind/stop service.

## خطاهای رایج
- `onBind` را بدون `IBinder` return کردن → compile error.
- `startForegroundService` را بدون notification استفاده کردن → crash.
- `bindService` را بدون `unbindService` استفاده کردن → resource leak.
- `Service` را روی main thread اجرا کردن → ANR — background thread لازم است.

## جمع‌بندی
- Service برای background work بدون UI — started, bound, foreground.
- `startService` برای one-time، `bindService` برای client-server، `startForegroundService` برای long-running.
- `START_STICKY` برای restart if killed.

**جلسهٔ بعد:** BroadcastReceiver و Permissions.
