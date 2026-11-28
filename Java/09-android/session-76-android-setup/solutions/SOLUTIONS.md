# پاسخ تمرین‌های جلسه ۷۶

## آسان

**۱ و ۲.**
```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```

## متوسط

**۳ و ۴.**
```xml
<LinearLayout android:orientation="vertical">
    <TextView android:id="@+id/title" android:text="Hello"/>
    <Button android:id="@+id/button" android:text="Click"/>
</LinearLayout>
```
```java
TextView title = findViewById(R.id.title);
Button button = findViewById(R.id.button);
button.setOnClickListener(v -> title.setText("Clicked!"));
```

## سخت

**۵ و ۶.**
```xml
<activity android:name=".MainActivity" android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN"/>
        <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>
</activity>
```
