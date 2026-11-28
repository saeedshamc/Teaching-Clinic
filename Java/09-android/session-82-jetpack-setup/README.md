# جلسه ۸۲ — Jetpack Setup و Modern Android

## اهداف یادگیری
- Jetpack libraries را بشناسید
- `build.gradle` dependencies را برای Jetpack تنظیم کنید
- `AppCompatActivity`, `ViewModel`, `LiveData` را بفهمید
- modern Android architecture را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۸۱ (Legacy Capstone)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Jetpack Libraries

```gradle
// build.gradle (Module: app)
dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.9.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.recyclerview:recyclerview:1.3.0'
    implementation 'androidx.lifecycle:lifecycle-viewmodel:2.6.1'
    implementation 'androidx.lifecycle:lifecycle-livedata:2.6.1'
    implementation 'androidx.navigation:navigation-fragment:2.6.0'
    implementation 'androidx.navigation:navigation-ui:2.6.0'
    implementation 'androidx.room:room-runtime:2.5.2'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'androidx.work:work-runtime:2.8.1'
}
```

### Modern Architecture — MVVM

```
UI (Activity/Fragment)
    ↓ observes
ViewModel
    ↓ exposes
LiveData/StateFlow
    ↓ uses
Repository
    ↓ calls
Data Source (Room/Retrofit)
```

### ViewModel — برای UI state

```java
public class MyViewModel extends ViewModel {
    private MutableLiveData<String> message = new MutableLiveData<>();

    public LiveData<String> getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        message.setValue(msg);
    }
}
```

### LiveData — observable data

```java
// In Activity
MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);
viewModel.getMessage().observe(this, msg -> {
    textView.setText(msg);
});
```

## کد مهم

`src/MyViewModel.java` — ViewModel با LiveData.
`src/MainActivity.java` — Activity با ViewModel.

## خطاهای رایج
- `ViewModelProvider` را فراموش کردن → `ViewModel` instantiate نمی‌شود.
- `LiveData` را بدون `observe` استفاده کردن → UI update نمی‌شود.
- `setValue` را روی wrong thread صدا زدن → `IllegalStateException` — `postValue` برای background.
- `build.gradle` dependencies را فراموش کردن → compile error.

## جمع‌بندی
- Jetpack برای modern Android — MVVM architecture.
- `ViewModel` برای UI state، `LiveData` برای observable data.
- `Repository` pattern برای data layer.

**جلسهٔ بعد:** ViewModel و LiveData.
