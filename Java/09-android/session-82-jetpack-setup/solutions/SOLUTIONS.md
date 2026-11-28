# پاسخ تمرین‌های جلسه ۸۲

## آسان

**۱ و ۲.**
```java
public class MyViewModel extends ViewModel {
    private MutableLiveData<String> message = new MutableLiveData<>();
    public LiveData<String> getMessage() { return message; }
    public void setMessage(String msg) { message.setValue(msg); }
}

viewModel.getMessage().observe(this, msg -> textView.setText(msg));
```

## متوسط

**۳ و ۴.**
```java
MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);
viewModel.setMessage("Hello");
```

## سخت

**۵ و ۶.**
```java
// postValue برای background thread
viewModel.getMessage().postValue("From background");

// Repository pattern
public class ContactRepository {
    private ContactDao dao;
    public List<Contact> getAll() { return dao.getAll(); }
    public void insert(Contact contact) { dao.insert(contact); }
}
```
