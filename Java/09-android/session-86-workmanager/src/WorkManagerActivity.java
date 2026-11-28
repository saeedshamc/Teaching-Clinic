// WorkManagerActivity.java — enqueue work با WorkManager
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.*;
import java.util.concurrent.TimeUnit;

public class WorkManagerActivity extends AppCompatActivity {

    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workmanager);

        statusText = findViewById(R.id.status_text);
        Button oneTimeBtn = findViewById(R.id.one_time_button);
        Button periodicBtn = findViewById(R.id.periodic_button);
        Button constrainedBtn = findViewById(R.id.constrained_button);
        Button cancelBtn = findViewById(R.id.cancel_button);

        oneTimeBtn.setOnClickListener(v -> enqueueOneTimeWork());
        periodicBtn.setOnClickListener(v -> enqueuePeriodicWork());
        constrainedBtn.setOnClickListener(v -> enqueueConstrainedWork());
        cancelBtn.setOnClickListener(v -> cancelWork());
    }

    private void enqueueOneTimeWork() {
        Data inputData = new Data.Builder()
            .putString("key", "value")
            .putInt("number", 42)
            .build();

        WorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
            .setInputData(inputData)
            .build();

        WorkManager.getInstance(this).enqueue(workRequest);
        statusText.setText("One-time work enqueued");
    }

    private void enqueuePeriodicWork() {
        PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(
            MyWorker.class,
            15, TimeUnit.MINUTES
        ).build();

        WorkManager.getInstance(this).enqueue(periodicWork);
        statusText.setText("Periodic work enqueued (15 min)");
    }

    private void enqueueConstrainedWork() {
        Constraints constraints = new Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build();

        WorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
            .setConstraints(constraints)
            .build();

        WorkManager.getInstance(this).enqueue(workRequest);
        statusText.setText("Constrained work enqueued");
    }

    private void cancelWork() {
        WorkManager.getInstance(this).cancelAllWork();
        statusText.setText("All work cancelled");
    }
}
