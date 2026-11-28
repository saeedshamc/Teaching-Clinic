# پاسخ تمرین‌های جلسه ۸۴

## آسان

**۱ و ۲.**
```java
@Entity(tableName = "contacts")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    // getters/setters
}

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contacts")
    LiveData<List<Contact>> getAllContacts();
    @Insert void insert(Contact contact);
}
```

## متوسط

**۳ و ۴.**
```java
@Database(entities = {Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();
}

public class ContactRepository {
    private ContactDao dao;
    public LiveData<List<Contact>> getAllContacts() { return dao.getAllContacts(); }
    public void insert(Contact contact) {
        Executors.newSingleThreadExecutor().execute(() -> dao.insert(contact));
    }
}
```

## سخت

**۵ و ۶.**
```java
@Query("SELECT * FROM contacts WHERE category = :category")
LiveData<List<Contact>> getContactsByCategory(String category);

// background thread for database operations
Executors.newSingleThreadExecutor().execute(() -> dao.insert(contact));
```
