# جلسه ۹۳ — Notifications

## اهداف یادگیری
- `NotificationManager` و `NotificationCompat` را بشناسید
- `NotificationChannel` را برای Android 8+ بفهمید
- `PendingIntent` برای notification actions را به‌کار ببرید
- `NotificationCompat.Builder` را برای rich notifications یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۹۲ (BroadcastReceiver و Permissions)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### NotificationChannel — Android 8+

```java
NotificationChannel channel = new NotificationChannel(
    "my_channel",
    "My Channel",
    NotificationManager.IMPORTANCE_DEFAULT
);
channel.setDescription("Channel description");
NotificationManager manager = getSystemService(NotificationManager.class);
manager.createNotificationChannel(channel);
```

### NotificationCompat.Builder — برای notifications

```java
NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "my_channel")
    .setSmallIcon(R.drawable.ic_notification)
    .setContentTitle("Title")
    .setContentText("Content text")
    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    .setAutoCancel(true);

NotificationManagerCompat.from(this).notify(1, builder.build());
```

### PendingIntent — برای actions

```java
Intent intent = new Intent(this, MainActivity.class);
PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "my_channel")
    .setContentIntent(pendingIntent)
    .addAction(R.drawable.ic_action, "Action", pendingIntent);
```

### Rich Notifications — با actions و styles

```java
NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "my_channel")
    .setSmallIcon(R.drawable.ic_notification)
    .setContentTitle("Title")
    .setContentText("Content")
    .setStyle(new NotificationCompat.BigTextStyle().bigText("Long text..."))
    .addAction(R.drawable.ic_action1, "Action 1", pendingIntent1)
    .addAction(R.drawable.ic_action2, "Action 2", pendingIntent2);
```

## کد مهم

`src/NotificationHelper.java` — notification utilities.
`src/NotificationActivity.java` — notification demo.

## خطاهای رایج
- `NotificationChannel` را برای Android 8+ فراموش کردن → notification نمایش داده نمی‌شود.
- `PendingIntent.FLAG_IMMUTABLE` را برای Android 12+ فراموش کردن → crash.
- `NotificationManagerCompat` را بدون `notify` استفاده کردن → notification ارسال نمی‌شود.
- `setSmallIcon` را بدون valid icon استفاده کردن → crash.

## جمع‌بندی
- `NotificationChannel` برای Android 8+, `NotificationCompat` برای compatibility.
- `PendingIntent` برای actions، `NotificationManagerCompat` برای notify.
- Rich notifications با styles و actions.

**جلسهٔ بعد:** Android Final Project Prep.
