// ServiceActivity.java — start/bind/stop service
// نیازمند Android SDK
package com.example.myapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ServiceActivity extends AppCompatActivity {

    private MyService myService;
    private boolean isBound = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            myService = binder.getService();
            isBound = true;
            updateStatus("Service bound");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            myService = null;
            updateStatus("Service disconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        TextView statusText = findViewById(R.id.status_text);
        Button startBtn = findViewById(R.id.start_button);
        Button stopBtn = findViewById(R.id.stop_button);
        Button bindBtn = findViewById(R.id.bind_button);
        Button unbindBtn = findViewById(R.id.unbind_button);
        Button getDataBtn = findViewById(R.id.get_data_button);

        startBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MyService.class);
            startForegroundService(intent);
            updateStatus("Service started");
        });

        stopBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MyService.class);
            stopService(intent);
            updateStatus("Service stopped");
        });

        bindBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MyService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        });

        unbindBtn.setOnClickListener(v -> {
            if (isBound) {
                unbindService(connection);
                isBound = false;
            }
        });

        getDataBtn.setOnClickListener(v -> {
            if (isBound && myService != null) {
                updateStatus("Data: " + myService.getData());
            } else {
                updateStatus("Service not bound");
            }
        });
    }

    private void updateStatus(String text) {
        TextView statusText = findViewById(R.id.status_text);
        statusText.setText(text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
    }
}
