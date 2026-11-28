// PermissionActivity.java — runtime permission request
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

public class PermissionActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_permission);

        statusText = findViewById(R.id.status_text);
        Button cameraBtn = findViewById(R.id.camera_button);
        Button locationBtn = findViewById(R.id.location_button);
        Button storageBtn = findViewById(R.id.storage_button);

        cameraBtn.setOnClickListener(v -> requestPermission(Manifest.permission.CAMERA));
        locationBtn.setOnClickListener(v -> requestPermission(Manifest.permission.ACCESS_FINE_LOCATION));
        storageBtn.setOnClickListener(v -> requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE));
    }

    private void requestPermission(String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            statusText.setText("Permission already granted");
        } else {
            permissionLauncher.launch(permission);
        }
    }
}
