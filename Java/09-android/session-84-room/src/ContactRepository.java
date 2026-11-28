// ContactRepository.java — data layer برای contacts
// نیازمند Android SDK
package com.example.myapp;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executors;

public class ContactRepository {

    private ContactDao dao;
    private static ContactRepository instance;

    private ContactRepository(Context context) {
        dao = AppDatabase.getInstance(context).contactDao();
    }

    public static synchronized ContactRepository getInstance(Context context) {
        if (instance == null) {
            instance = new ContactRepository(context);
        }
        return instance;
    }

    public LiveData<List<Contact>> getAllContacts() {
        return dao.getAllContacts();
    }

    public LiveData<List<Contact>> getContactsByCategory(String category) {
        return dao.getContactsByCategory(category);
    }

    public void insert(Contact contact) {
        Executors.newSingleThreadExecutor().execute(() -> dao.insert(contact));
    }

    public void update(Contact contact) {
        Executors.newSingleThreadExecutor().execute(() -> dao.update(contact));
    }

    public void delete(Contact contact) {
        Executors.newSingleThreadExecutor().execute(() -> dao.delete(contact));
    }

    public void deleteById(int id) {
        Executors.newSingleThreadExecutor().execute(() -> dao.deleteById(id));
    }
}
