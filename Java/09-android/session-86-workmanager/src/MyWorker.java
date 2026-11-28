// MyWorker.java — background worker برای WorkManager
// نیازمند Android SDK
package com.example.myapp;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        // ---------- Get input data ----------
        String key = getInputData().getString("key");
        int number = getInputData().getInt("number", 0);

        // ---------- Background work ----------
        try {
            // simulate work
            Thread.sleep(2000);
            System.out.println("Worker: key=" + key + ", number=" + number);

            // ---------- Return result ----------
            Data outputData = new Data.Builder()
                .putString("result", "Success")
                .putInt("processed", number * 2)
                .build();

            return Result.success(outputData);
        } catch (Exception e) {
            return Result.failure();
        }
    }
}
