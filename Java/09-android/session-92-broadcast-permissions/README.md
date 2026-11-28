# جلسه ۹۲ — BroadcastReceiver و Permissions

## اهداف یادگیری
- `BroadcastReceiver` را برای system events بشناسید
- `sendBroadcast` و `registerReceiver` را بفهمید
- Runtime permissions را برای Android 6+ یاد بگیرید
- `ActivityResultLauncher` برای permission requests را به‌کار ببرید

## پیش‌نیازها
- جلسهٔ ۹۱ (Services)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### BroadcastReceiver — برای system events

```xml
<!-- AndroidManifest.xml -->
<receiver android:name=".MyReceiver" android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.BATTERY_LOW" />
        <action android:name="android.intent.action.BOOT_COMPLETED" />
    </intent-filter>
</receiver>
```

### BroadcastReceiver Implementation

```java
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            switch (action) {
                case Intent.ACTION_BATTERY_LOW:
                    // handle battery low
                    break;
                case Intent.ACTION_BOOT_COMPLETED:
                    // handle boot completed
                    break;
            }
        }
    }
}
```

### Send Broadcast — custom broadcast

```java
Intent intent = new Intent("com.example.myapp.CUSTOM_ACTION");
intent.putExtra("key", "value");
sendBroadcast(intent);
```

### Register Receiver — dynamic registration

```java
BroadcastReceiver receiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        // handle broadcast
    }
};

IntentFilter filter = new IntentFilter("com.example.myapp.CUSTOM_ACTION");
registerReceiver(receiver, filter);

// Unregister
unregisterReceiver(receiver);
```

### Runtime Permissions — Android 6+

```xml
<!-- AndroidManifest.xml -->
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

```java
// Request permission
private static final int REQUEST_CODE = 100;

private void requestPermission(String permission) {
    if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_CODE);
    }
}

@Override
public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == REQUEST_CODE) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // permission granted
        } else {
            // permission denied
        }
    }
}
```

### ActivityResultLauncher — modern permission request

```java
private ActivityResultLauncher<String> permissionLauncher =
    registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            // permission granted
        } else {
            // permission denied
        }
    });

// Request permission
permissionLauncher.launch(Manifest.permission.CAMERA);
```

## کد مهم

`src/MyReceiver.java` — BroadcastReceiver.
`src/PermissionActivity.java` — permission request.

## خطاهای رایج
- `BroadcastReceiver` را بدون `intent-filter` در manifest استفاده کردن → کار نمی‌کند.
- `registerReceiver` را بدون `unregisterReceiver` استفاده کردن → memory leak.
- `onReceive` را روی main thread اجرا کردن → ANR — background thread لازم است.
- `requestPermissions` را بدون `checkSelfPermission` استفاده کردن → crash.

## جمع‌بندی
- BroadcastReceiver برای system events و custom broadcasts.
- Runtime permissions برای sensitive operations — `checkSelfPermission`, `requestPermissions`.
- `ActivityResultLauncher` برای modern permission requests.

**جلسهٔ بعد:** Notifications.
