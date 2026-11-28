# پاسخ تمرین‌های جلسه ۹۳

## آسان

**۱ و ۲.**
```java
NotificationChannel channel = new NotificationChannel("my_channel", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
getSystemService(NotificationManager.class).createNotificationChannel(channel);

NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "my_channel")
    .setSmallIcon(R.drawable.ic_notification)
    .setContentTitle("Title")
    .setContentText("Content");
```

## متوسط

**۳ و ۴.**
```java
PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

NotificationManagerCompat.from(this).notify(1, builder.build());
```

## سخت

**۵ و ۶.**
```java
.setStyle(new NotificationCompat.BigTextStyle().bigText("Long text..."))
.addAction(R.drawable.ic_action1, "Action 1", pendingIntent1)
.addAction(R.drawable.ic_action2, "Action 2", pendingIntent2)
```
