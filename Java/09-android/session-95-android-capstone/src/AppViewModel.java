// AppViewModel.java — ViewModel برای اپ نهایی
// نیازمند Android SDK
package com.example.myapp;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

public class AppViewModel extends AndroidViewModel {

    private ItemRepository repository;
    private LiveData<List<Item>> items;
    private MutableLiveData<Boolean> loading = new MutableLiveData<>(false);

    public AppViewModel(Application application) {
        super(application);
        repository = ItemRepository.getInstance(application);
        items = repository.getAllItems();
    }

    public LiveData<List<Item>> getItems() { return items; }
    public LiveData<Boolean> getLoading() { return loading; }

    public void loadItems() { repository.syncItems(); }
    public void addItem(Item item) { repository.insert(item); }
    public void deleteItem(Item item) { repository.delete(item); }
}
