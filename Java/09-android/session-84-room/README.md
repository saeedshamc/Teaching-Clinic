# جلسه ۸۴ — Room: Local Database

## اهداف یادگیری
- `Room` را برای local database بشناسید
- `@Entity`, `@Dao`, `@Database` را بفهمید
- `LiveData` برای reactive queries را به‌کار ببرید
- `RoomDatabase` و `Room.inMemoryDatabaseBuilder` را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۸۳ (ViewModel و LiveData)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Room — SQLite abstraction

```gradle
dependencies {
    implementation 'androidx.room:room-runtime:2.5.2'
    annotationProcessor 'androidx.room:room-compiler:2.5.2'
}
```

### Entity — table definition

```java
@Entity(tableName = "contacts")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phone;
    private String email;
    private String category;

    // getters/setters
}
```

### DAO — data access object

```java
@Dao
public interface ContactDao {
    @Query("SELECT * FROM contacts")
    LiveData<List<Contact>> getAllContacts();

    @Query("SELECT * FROM contacts WHERE category = :category")
    LiveData<List<Contact>> getContactsByCategory(String category);

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM contacts WHERE id = :id")
    void deleteById(int id);
}
```

### Database — RoomDatabase

```java
@Database(entities = {Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class,
                        "app_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
```

### Repository — برای data layer

```java
public class ContactRepository {
    private ContactDao dao;

    public ContactRepository(Context context) {
        dao = AppDatabase.getInstance(context).contactDao();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return dao.getAllContacts();
    }

    public void insert(Contact contact) {
        // run on background thread
        Executors.newSingleThreadExecutor().execute(() -> dao.insert(contact));
    }
}
```

## کد مهم

`src/Contact.java` — entity.
`src/ContactDao.java` — DAO.
`src/AppDatabase.java` — database.
`src/ContactRepository.java` — repository.

## خطاهای رایج
- `annotationProcessor` را فراموش کردن → compile error.
- `@Entity` را بدون `@PrimaryKey` استفاده کردن → compile error.
- `LiveData` را بدون `observe` استفاده کردن → UI update نمی‌شود.
- Database operations را روی main thread اجرا کردن → `IllegalStateException` — background thread لازم است.

## جمع‌بندی
- Room برای local database — SQLite abstraction.
- `@Entity` برای table، `@Dao` برای queries، `@Database` برای database.
- `LiveData` برای reactive queries، `Repository` برای data layer.

**جلسهٔ بعد:** Retrofit — REST API client.
