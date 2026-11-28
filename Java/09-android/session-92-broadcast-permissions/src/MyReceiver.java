// MyReceiver.java — BroadcastReceiver برای system events
// نیازمند Android SDK
package com.example.myapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            switch (action) {
                case Intent.ACTION_BATTERY_LOW:
                    Toast.makeText(context, "Battery low!", Toast.LENGTH_SHORT).show();
                    break;
                case Intent.ACTION_BATTERY_OKAY:
                    Toast.makeText(context, "Battery OK", Toast.LENGTH_SHORT).show();
                    break;
                case Intent.ACTION_BOOT_COMPLETED:
                    Toast.makeText(context, "Device booted", Toast.LENGTH_SHORT).show();
                    break;
                case "com.example.myapp.CUSTOM_ACTION":
                    String data = intent.getStringExtra("key");
                    Toast.makeText(context, "Custom: " + data, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
