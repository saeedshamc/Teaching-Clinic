# جلسه ۷۶ — Android Setup و اولین برنامه

## اهداف یادگیری
- Android Studio را نصب و راه‌اندازی کنید
- Android SDK و Emulator را بشناسید
- اولین Android project را بسازید
- `Activity`, `Layout`, `Manifest` را بفهمید

## پیش‌نیازها
- فازهای ۱–۵

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### Android Studio نصب

1. `https://developer.android.com/studio` را دانلود کنید
2. نصب کنید — SDK، Emulator، Build Tools را انتخاب کنید
3. `File > New > New Project` → `Empty Activity`

### ساختار پروژهٔ Android

```
app/
├── src/main/
│   ├── java/com/example/myapp/
│   │   └── MainActivity.java
│   ├── res/
│   │   ├── layout/activity_main.xml
│   │   ├── values/strings.xml
│   │   ├── values/colors.xml
│   │   └── values/themes.xml
│   └── AndroidManifest.xml
└── build.gradle
```

### AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/Theme.MyApp">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

### MainActivity.java

```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```

### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello Android!"
        android:textSize="24sp" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Click Me" />
</LinearLayout>
```

## کد مهم

`src/MainActivity.java` — basic activity.
`src/activity_main.xml` — basic layout.
`src/AndroidManifest.xml` — manifest.

## خطاهای رایج
- `setContentView` را فراموش کردن → صفحه خالی.
- `R.layout.activity_main` را پیدا نکردن → XML syntax error.
- `AndroidManifest` را update نکردن → app اجرا نمی‌شود.
- `android:exported` را برای Android 12+ فراموش کردن → crash.

## جمع‌بندی
- Android Studio برای development، Emulator برای testing.
- `Activity` → `Layout` → `Manifest`.
- `setContentView(R.layout.activity_main)` برای اتصال.

**جلسهٔ بعد:** Activities و Intents.
