// CounterViewModel.java — پاسخ پروژهٔ جلسه ۸۲
// نیازمند Android SDK
package com.example.myapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {

    private MutableLiveData<Integer> counter = new MutableLiveData<>(0);

    public LiveData<Integer> getCounter() {
        return counter;
    }

    public void increment() {
        Integer current = counter.getValue();
        if (current != null) {
            counter.setValue(current + 1);
        }
    }

    public void decrement() {
        Integer current = counter.getValue();
        if (current != null && current > 0) {
            counter.setValue(current - 1);
        }
    }

    public void reset() {
        counter.setValue(0);
    }
}
