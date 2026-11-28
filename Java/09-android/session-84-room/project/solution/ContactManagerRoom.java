// ContactManagerRoom.java — پاسخ کامل پروژهٔ جلسه ۸۴
// نیازمند Android SDK
package com.example.myapp;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.*;
import java.util.List;
import java.util.concurrent.Executors;

// ---------- Entity ----------
@Entity(tableName = "contacts")
class Contact {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name, phone, email, category;

    Contact(String name, String phone, String email, String category) {
        this.name = name; this.phone = phone; this.email = email; this.category = category;
    }
}

// ---------- DAO ----------
@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    LiveData<List<Contact>> getAllContacts();

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM contacts WHERE id = :id")
    void deleteById(int id);
}

// ---------- Database ----------
@Database(entities = {Contact.class}, version = 1, exportSchema = false)
abstract class AppDatabase extends RoomDatabase {
    abstract ContactDao contactDao();

    private static volatile AppDatabase INSTANCE;
    static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "app_database").build();
                }
            }
        }
        return INSTANCE;
    }
}

// ---------- Repository ----------
class ContactRepository {
    private ContactDao dao;
    private static ContactRepository instance;

    private ContactRepository(Context context) {
        dao = AppDatabase.getInstance(context).contactDao();
    }

    static synchronized ContactRepository getInstance(Context context) {
        if (instance == null) instance = new ContactRepository(context);
        return instance;
    }

    LiveData<List<Contact>> getAllContacts() { return dao.getAllContacts(); }
    void insert(Contact contact) {
        Executors.newSingleThreadExecutor().execute(() -> dao.insert(contact));
    }
    void delete(Contact contact) {
        Executors.newSingleThreadExecutor().execute(() -> dao.delete(contact));
    }
}

// ---------- ViewModel ----------
class ContactViewModel extends ViewModel {
    private ContactRepository repository;
    private LiveData<List<Contact>> contacts;

    ContactViewModel(Context context) {
        repository = ContactRepository.getInstance(context);
        contacts = repository.getAllContacts();
    }

    LiveData<List<Contact>> getContacts() { return contacts; }
    void addContact(Contact contact) { repository.insert(contact); }
    void deleteContact(Contact contact) { repository.delete(contact); }
}

// ---------- Activity ----------
public class ContactManagerRoom extends androidx.appcompat.app.AppCompatActivity {
    private ContactViewModel viewModel;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_manager);

        viewModel = new ViewModelProvider(this,
            new ViewModelProvider.NewInstanceFactory() {
                @Override
                public <T extends ViewModel> T create(Class<T> modelClass) {
                    return (T) new ContactViewModel(getApplicationContext());
                }
            }).get(ContactViewModel.class);

        viewModel.getContacts().observe(this, contacts -> {
            // update RecyclerView
        });
    }
}
