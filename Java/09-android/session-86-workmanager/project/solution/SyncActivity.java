// SyncActivity.java — پاسخ پروژهٔ جلسه ۸۶
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.*;
import java.util.concurrent.TimeUnit;

public class SyncActivity extends AppCompatActivity {

    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        statusText = findViewById(R.id.status_text);
        Button syncBtn = findViewById(R.id.sync_button);
        Button cancelBtn = findViewById(R.id.cancel_button);

        syncBtn.setOnClickListener(v -> scheduleSync());
        cancelBtn.setOnClickListener(v -> cancelSync());
    }

    private void scheduleSync() {
        Constraints constraints = new Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build();

        PeriodicWorkRequest syncWork = new PeriodicWorkRequest.Builder(
            SyncWorker.class,
            15, TimeUnit.MINUTES
        )
        .setConstraints(constraints)
        .build();

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "sync_work",
            ExistingPeriodicWorkPolicy.REPLACE,
            syncWork
        );

        statusText.setText("Sync scheduled every 15 min (requires network)");
    }

    private void cancelSync() {
        WorkManager.getInstance(this).cancelUniqueWork("sync_work");
        statusText.setText("Sync cancelled");
    }
}
