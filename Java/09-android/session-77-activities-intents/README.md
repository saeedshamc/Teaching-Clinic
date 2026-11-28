# جلسه ۷۷ — Activities و Intents

## اهداف یادگیری
- `Activity` lifecycle را بشناسید (`onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onDestroy`)
- `Intent` برای navigation بین activities را بفهمید
- `startActivity` و `startActivityForResult` را به‌کار ببرید
- `Activity` stack و back button را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۷۶ (Android Setup)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Activity Lifecycle

```
onCreate() → onStart() → onResume() → [visible]
                ↓
        onPause() → onStop() → [not visible]
                ↓
        onRestart() → onStart() → onResume()
                ↓
        onDestroy() → [destroyed]
```

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
}

@Override
protected void onStart() {
    super.onStart();
    // activity becoming visible
}

@Override
protected void onResume() {
    super.onResume();
    // activity in foreground
}

@Override
protected void onPause() {
    super.onPause();
    // activity losing focus
}

@Override
protected void onStop() {
    super.onStop();
    // activity no longer visible
}

@Override
protected void onDestroy() {
    super.onDestroy();
    // activity being destroyed
}
```

### Intent — برای navigation

```java
// Explicit intent — برای activity مشخص
Intent intent = new Intent(this, SecondActivity.class);
startActivity(intent);

// Implicit intent — برای system actions
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.setData(Uri.parse("https://www.google.com"));
startActivity(intent);
```

### startActivityForResult — برای نتیجه

```java
// FirstActivity
Intent intent = new Intent(this, SecondActivity.class);
startActivityForResult(intent, REQUEST_CODE);

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
        String result = data.getStringExtra("result");
        // handle result
    }
}

// SecondActivity
Intent resultIntent = new Intent();
resultIntent.putExtra("result", "Hello from Second!");
setResult(RESULT_OK, resultIntent);
finish();
```

## کد مهم

`src/MainActivity.java` — first activity با intent.
`src/SecondActivity.java` — second activity با result.

## خطاهای رایج
- `SecondActivity` را در manifest فراموش کردن → `ActivityNotFoundException`.
- `startActivityForResult` را deprecated استفاده کردن → `ActivityResultLauncher` بهتر است.
- `onActivityResult` را بدون `super` صدا زدن → crash.
- `finish()` را فراموش کردن → back button به activity قبلی برمی‌گردد.

## جمع‌بندی
- Activity lifecycle برای resource management.
- `Intent` برای navigation، `startActivityForResult` برای result.
- `ActivityResultLauncher` برای modern approach.

**جلسهٔ بعد:** Layouts و XML.
