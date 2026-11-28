// FinalApp.java — capstone اپ نهایی Android
// نیازمند Android SDK — ترکیب همهٔ مباحث
package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.*;
import java.util.concurrent.TimeUnit;

public class FinalApp extends AppCompatActivity {

    private AppViewModel viewModel;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_app);

        // ---------- ViewModel ----------
        viewModel = new ViewModelProvider(this).get(AppViewModel.class);

        // ---------- RecyclerView ----------
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(new java.util.ArrayList<>());
        recyclerView.setAdapter(adapter);

        // ---------- Observe ----------
        viewModel.getItems().observe(this, items -> {
            adapter.updateItems(items);
            TextView status = findViewById(R.id.status);
            status.setText(items.size() + " items");
        });

        // ---------- Buttons ----------
        Button addBtn = findViewById(R.id.add_button);
        Button syncBtn = findViewById(R.id.sync_button);
        Button notifyBtn = findViewById(R.id.notify_button);

        addBtn.setOnClickListener(v -> {
            Item item = new Item("Item " + System.currentTimeMillis(), "Description");
            viewModel.addItem(item);
        });

        syncBtn.setOnClickListener(v -> {
            Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
            PeriodicWorkRequest sync = new PeriodicWorkRequest.Builder(
                SyncWorker.class, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build();
            WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "sync", ExistingPeriodicWorkPolicy.REPLACE, sync);
        });

        notifyBtn.setOnClickListener(v -> {
            NotificationHelper.showNotification(this, "App", "Notification from app");
        });

        // ---------- Load ----------
        viewModel.loadItems();
    }
}
