# پاسخ تمرین‌های جلسه ۹۴

## آسان

**۱ و ۲.**
```gradle
dependencies {
    implementation 'androidx.room:room-runtime:2.5.2'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.google.dagger:hilt-android:2.48'
}
```

## متوسط

**۳ و ۴.**
```java
// data/
//   local/ (Room)
//   remote/ (Retrofit)
//   repository/ (Repository)

public class ItemRepository {
    private ItemDao dao;
    private ApiService api;
    public LiveData<List<Item>> getAllItems() { return dao.getAllItems(); }
}
```

## سخت

**۵ و ۶.**
```java
@HiltAndroidApp
public class MyApplication extends Application {}

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject ItemViewModel viewModel;
}

WorkRequest work = new OneTimeWorkRequest.Builder(SyncWorker.class).build();
WorkManager.getInstance(context).enqueue(work);
```
