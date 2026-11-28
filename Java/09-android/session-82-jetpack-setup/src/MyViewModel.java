// MyViewModel.java — ViewModel با LiveData
// نیازمند Android SDK
package com.example.myapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<String> message = new MutableLiveData<>();
    private MutableLiveData<Integer> counter = new MutableLiveData<>(0);

    public LiveData<String> getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        message.setValue(msg);
    }

    public LiveData<Integer> getCounter() {
        return counter;
    }

    public void incrementCounter() {
        Integer current = counter.getValue();
        if (current != null) {
            counter.setValue(current + 1);
        }
    }
}
