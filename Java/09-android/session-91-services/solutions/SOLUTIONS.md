# پاسخ تمرین‌های جلسه ۹۱

## آسان

**۱ و ۲.**
```java
public class MyService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // background work
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) { return null; }
}

Intent intent = new Intent(this, MyService.class);
startService(intent);
stopService(intent);
```

## متوسط

**۳ و ۴.**
```java
bindService(intent, connection, Context.BIND_AUTO_CREATE);

public class LocalBinder extends Binder {
    MyService getService() { return MyService.this; }
}
```

## سخت

**۵ و ۶.**
```java
startForeground(1, createNotification());
return START_STICKY;
```
