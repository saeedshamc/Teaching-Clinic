# پاسخ تمرین‌های جلسه ۷۷

## آسان

**۱ و ۲.**
```java
Intent intent = new Intent(this, SecondActivity.class);
startActivity(intent);
```
```xml
<activity android:name=".SecondActivity" android:exported="false"/>
```

## متوسط

**۳ و ۴.**
```java
startActivityForResult(intent, REQUEST_CODE);

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
        String result = data.getStringExtra("result");
    }
}
```

## سخت

**۵ و ۶.**
```java
intent.putExtra("key", "value");
String value = getIntent().getStringExtra("key");

ActivityResultLauncher<Intent> launcher = registerForActivityResult(
    new ActivityResultContracts.StartActivityForResult(),
    result -> { /* handle result */ }
);
launcher.launch(intent);
```
