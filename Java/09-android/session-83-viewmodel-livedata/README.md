# جلسه ۸۳ — ViewModel و LiveData

## اهداف یادگیری
- `ViewModel` را برای UI state management بفهمید
- `LiveData` را برای observable data به‌کار ببرید
- `MutableLiveData` vs `LiveData` را تشخیص دهید
- `observe` و lifecycle awareness را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۸۲ (Jetpack Setup)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### ViewModel — برای UI state

```java
public class ContactViewModel extends ViewModel {
    private MutableLiveData<List<Contact>> contacts = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public LiveData<List<Contact>> getContacts() {
        return contacts;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void loadContacts() {
        // load from repository
        contacts.setValue(contactList);
    }

    public void addContact(Contact contact) {
        List<Contact> current = contacts.getValue();
        if (current != null) {
            current.add(contact);
            contacts.setValue(current);
        }
    }
}
```

### LiveData — observable data

```java
// In Activity
ContactViewModel viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

viewModel.getContacts().observe(this, contacts -> {
    // update UI when contacts change
    adapter.notifyDataSetChanged();
});

viewModel.getError().observe(this, error -> {
    Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
});
```

### MutableLiveData vs LiveData

| MutableLiveData | LiveData |
|---|---|
| `setValue()`, `postValue()` | read-only |
| for ViewModel | for Activity/Fragment |
| mutable | immutable |

### Lifecycle Awareness

- `LiveData` فقط وقتی observers active هستند notify می‌کند.
- `observe` با `LifecycleOwner` — automatic cleanup.
- `postValue` برای background thread، `setValue` برای main thread.

## کد مهم

`src/ContactViewModel.java` — ViewModel با LiveData.
`src/ContactActivity.java` — Activity با observe.

## خطاهای رایج
- `setValue` را روی background thread صدا زدن → `IllegalStateException` — `postValue` استفاده کنید.
- `LiveData` را بدون `observe` استفاده کردن → UI update نمی‌شود.
- `ViewModel` را بدون `ViewModelProvider` instantiate کردن → lifecycle issues.
- `getValue()` را بدون null check استفاده کردن → NPE.

## جمع‌بندی
- `ViewModel` برای UI state، `LiveData` برای observable data.
- `MutableLiveData` برای ViewModel، `LiveData` برای consumers.
- `observe` با lifecycle awareness — automatic cleanup.

**جلسهٔ بعد:** Room — local database.
