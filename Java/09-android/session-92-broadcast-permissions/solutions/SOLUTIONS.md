# پاسخ تمرین‌های جلسه ۹۲

## آسان

**۱ و ۲.**
```java
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_BATTERY_LOW)) {
            // handle battery low
        }
    }
}

registerReceiver(receiver, new IntentFilter(Intent.ACTION_BATTERY_LOW));
```

## متوسط

**۳ و ۴.**
```java
Intent intent = new Intent("com.example.myapp.CUSTOM_ACTION");
sendBroadcast(intent);

if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_CODE);
}
```

## سخت

**۵ و ۶.**
```java
private ActivityResultLauncher<String> permissionLauncher =
    registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) { /* granted */ }
    });

permissionLauncher.launch(Manifest.permission.CAMERA);
```
