// NotificationActivity.java — notification demo
// نیازمند Android SDK
package com.example.myapp;

import android.Manifest;
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
        Button simpleBtn = findViewById(R.id.simple_button);
        Button richBtn = findViewById(R.id.rich_button);

        permissionBtn.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            } else {
                statusText.setText("Permission already granted");
            }
        });

        simpleBtn.setOnClickListener(v -> {
            NotificationHelper.showNotification(this, "Simple Notification", "This is a simple notification");
            statusText.setText("Simple notification shown");
        });

        richBtn.setOnClickListener(v -> {
            NotificationHelper.showRichNotification(this, "Rich Notification", "This is a rich notification with actions");
            statusText.setText("Rich notification shown");
        });
    }
}
