# جلسه ۸۹ — Hilt و DataStore

## اهداف یادگیری
- `Hilt` را برای dependency injection بشناسید
- `@HiltAndroidApp`, `@AndroidEntryPoint`, `@Inject` را بفهمید
- `DataStore` را برای preferences بشناسید
- `SharedPreferences` در برابر `DataStore` را تشخیص دهید

## پیش‌نیازها
- جلسهٔ ۸۸ (Coroutines و Flow)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Hilt — dependency injection

```gradle
dependencies {
    implementation 'com.google.dagger:hilt-android:2.48'
    annotationProcessor 'com.google.dagger:hilt-compiler:2.48'
    implementation 'androidx.hilt:hilt-navigation-compose:1.1.0'
}
```

### Hilt Setup

```java
// Application class
@HiltAndroidApp
public class MyApplication extends Application {
}

// Activity
@AndroidEntryPoint
public class MyActivity extends AppCompatActivity {
    @Inject
    MyRepository repository;
}
```

### Module — برای dependencies

```java
@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public ContactDao provideContactDao(AppDatabase db) {
        return db.contactDao();
    }
}
```

### DataStore — preferences

```gradle
dependencies {
    implementation 'androidx.datastore:datastore-preferences:1.0.0'
}
```

### DataStore Usage

```java
// Preferences DataStore
val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsRepository(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "settings")

    val theme: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[THEME_KEY] ?: "light"
    }

    suspend fun setTheme(theme: String) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme
        }
    }

    companion object {
        val THEME_KEY = stringPreferencesKey("theme")
    }
}
```

## کد مهم

`src/MyApplication.java` — Hilt application.
`src/AppModule.java` — Hilt module.
`src/SettingsRepository.kt` — DataStore repository.
`src/SettingsActivity.java` — Activity با Hilt.

## خطاهای رایج
- `@HiltAndroidApp` را در Application فراموش کردن → Hilt crash.
- `@AndroidEntryPoint` را در Activity فراموش کردن → `@Inject` کار نمی‌کند.
- `annotationProcessor` را فراموش کردن → compile error.
- `DataStore` را روی main thread استفاده کردن → ANR — `suspend`/`Flow` لازم است.

## جمع‌بندی
- Hilt برای dependency injection — `@HiltAndroidApp`, `@AndroidEntryPoint`, `@Inject`.
- DataStore برای preferences — `preferencesDataStore`, `Flow`, `edit`.
- `SharedPreferences` deprecated — DataStore preferable.

**جلسهٔ بعد:** Jetpack Capstone.
