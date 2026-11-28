# پاسخ تمرین‌های جلسه ۹۵

## آسان

**۱ و ۲.**
```java
public class AppViewModel extends AndroidViewModel {
    private ItemRepository repository;
    private LiveData<List<Item>> items;
}

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    // ViewHolder + onBindViewHolder
}
```

## متوسط

**۳ و ۴.**
```java
PeriodicWorkRequest sync = new PeriodicWorkRequest.Builder(
    SyncWorker.class, 15, TimeUnit.MINUTES).build();
WorkManager.getInstance(context).enqueue(sync);

NotificationHelper.showNotification(context, "Title", "Text");
```

## سخت

**۵ و ۶.**
```java
@HiltAndroidApp
public class MyApplication extends Application {}

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject AppViewModel viewModel;
}

// nav_graph.xml for Navigation Component
```
