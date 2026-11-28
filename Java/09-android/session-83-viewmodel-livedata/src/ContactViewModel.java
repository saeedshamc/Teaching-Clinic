// ContactViewModel.java — ViewModel با LiveData
// نیازمند Android SDK
package com.example.myapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class ContactViewModel extends ViewModel {

    private MutableLiveData<List<Contact>> contacts = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>(false);

    public LiveData<List<Contact>> getContacts() {
        return contacts;
    }

    public LiveData<String> getError() {
        return error;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public void loadContacts() {
        loading.setValue(true);
        // simulate loading
        List<Contact> list = new ArrayList<>();
        list.add(new Contact(1, "Ali", "123-4567", "ali@mail.com", "Friends"));
        list.add(new Contact(2, "Sara", "987-6543", "sara@mail.com", "Family"));
        contacts.setValue(list);
        loading.setValue(false);
    }

    public void addContact(Contact contact) {
        List<Contact> current = contacts.getValue();
        if (current != null) {
            current.add(contact);
            contacts.setValue(current);
        } else {
            List<Contact> list = new ArrayList<>();
            list.add(contact);
            contacts.setValue(list);
        }
    }

    public void deleteContact(int position) {
        List<Contact> current = contacts.getValue();
        if (current != null && position >= 0 && position < current.size()) {
            current.remove(position);
            contacts.setValue(current);
        }
    }

    public void showError(String message) {
        error.setValue(message);
    }
}
