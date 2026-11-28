// SyncWorker.java — پاسخ پروژهٔ جلسه ۸۶
// نیازمند Android SDK
package com.example.myapp;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class SyncWorker extends Worker {

    public SyncWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            // simulate sync
            Thread.sleep(3000);
            Data output = new Data.Builder()
                .putString("status", "Synced")
                .putLong("timestamp", System.currentTimeMillis())
                .build();
            return Result.success(output);
        } catch (Exception e) {
            return Result.retry();
        }
    }
}
