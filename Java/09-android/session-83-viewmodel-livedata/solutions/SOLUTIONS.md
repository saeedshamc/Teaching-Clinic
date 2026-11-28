# پاسخ تمرین‌های جلسه ۸۳

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
// setValue برای main thread
message.setValue("Hello");

// postValue برای background thread
message.postValue("From background");

// observe با lifecycle — automatic cleanup
viewModel.getMessage().observe(this, msg -> { /* ... */ });
```

## سخت

**۵ و ۶.**
```java
// Transformations.map
LiveData<String> upperCase = Transformations.map(message, String::toUpperCase);

// MediatorLiveData
MediatorLiveData<String> combined = new MediatorLiveData<>();
combined.addSource(liveData1, value -> combined.setValue(value));
combined.addSource(liveData2, value -> combined.setValue(value));
```
