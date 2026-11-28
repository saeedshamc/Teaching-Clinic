# پاسخ تمرین‌های جلسه ۸۹

## آسان

**۱ و ۲.**
```java
@HiltAndroidApp
public class MyApplication extends Application {}

@AndroidEntryPoint
public class MyActivity extends AppCompatActivity {
    @Inject MyRepository repository;
}
```

## متوسط

**۳ و ۴.**
```java
@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides @Singleton
    public ContactDao provideContactDao(AppDatabase db) { return db.contactDao(); }
}

// DataStore
val Context.dataStore by preferencesDataStore(name = "settings")
```

## سخت

**۵ و ۶.**
```kotlin
val theme: Flow<String> = context.dataStore.data.map { preferences ->
    preferences[THEME_KEY] ?: "light"
}

suspend fun setTheme(theme: String) {
    context.dataStore.edit { preferences ->
        preferences[THEME_KEY] = theme
    }
}
```
