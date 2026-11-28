# پروژهٔ جلسه ۹۲ — سیستم Notification با Permissions

یک سیستم notification بسازید که:
- `BroadcastReceiver` برای system events
- `PermissionActivity` برای runtime permissions
- `ActivityResultLauncher` برای permission request
- `NotificationManager` برای notifications
- `sendBroadcast` برای custom broadcast
- `checkSelfPermission` برای permission check

نمونه:
```
[Request Permission] → [Granted/Denied]
[Send Broadcast] → [Receiver handles]
[Show Notification] → [Notification displayed]
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
