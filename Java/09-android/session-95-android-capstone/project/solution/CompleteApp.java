// CompleteApp.java — پاسخ کامل capstone
// نیازمند Android SDK — ترکیب همهٔ مباحث Android
package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.*;
import java.util.concurrent.TimeUnit;

public class CompleteApp extends AppCompatActivity {

    private AppViewModel viewModel;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        // Navigation
        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager()
            .findFragmentById(R.id.nav_host);

        // ViewModel
        viewModel = new ViewModelProvider(this).get(AppViewModel.class);

        // RecyclerView
        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(new java.util.ArrayList<>());
        recycler.setAdapter(adapter);

        // Observe
        viewModel.getItems().observe(this, items -> {
            adapter.updateItems(items);
            TextView status = findViewById(R.id.status);
            status.setText(items.size() + " items");
        });

        // Buttons
        findViewById(R.id.add).setOnClickListener(v -> {
            viewModel.addItem(new Item("New", "Desc"));
        });
        findViewById(R.id.sync).setOnClickListener(v -> scheduleSync());
        findViewById(R.id.notify).setOnClickListener(v -> {
            NotificationHelper.showRichNotification(this, "App", "Rich notification");
        });

        viewModel.loadItems();
    }

    private void scheduleSync() {
        Constraints c = new Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED).build();
        PeriodicWorkRequest w = new PeriodicWorkRequest.Builder(
            SyncWorker.class, 15, TimeUnit.MINUTES).setConstraints(c).build();
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "sync", ExistingPeriodicWorkPolicy.REPLACE, w);
    }
}
