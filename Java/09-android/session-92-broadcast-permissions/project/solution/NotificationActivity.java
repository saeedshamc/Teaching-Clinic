// NotificationActivity.java — پاسخ پروژهٔ جلسه ۹۲
// نیازمند Android SDK
package com.example.myapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class NotificationActivity extends AppCompatActivity {

    private TextView statusText;

    private ActivityResultLauncher<String> permissionLauncher =
        registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                statusText.setText("Permission granted");
            } else {
                statusText.setText("Permission denied");
            }
        });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        statusText = findViewById(R.id.status_text);
        Button permissionBtn = findViewById(R.id.permission_button);
        Button notificationBtn = findViewById(R.id.notification_button);
        Button broadcastBtn = findViewById(R.id.broadcast_button);

        permissionBtn.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            } else {
                statusText.setText("Permission already granted");
            }
        });

        notificationBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationReceiver.class);
            intent.setAction("com.example.myapp.SHOW_NOTIFICATION");
            sendBroadcast(intent);
            statusText.setText("Broadcast sent");
        });

        broadcastBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_BATTERY_LOW);
            sendBroadcast(intent);
            statusText.setText("Battery low broadcast sent");
        });
    }
}
