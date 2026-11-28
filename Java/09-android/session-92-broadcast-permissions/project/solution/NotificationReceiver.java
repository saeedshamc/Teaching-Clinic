// NotificationReceiver.java — پاسخ پروژهٔ جلسه ۹۲
// نیازمند Android SDK
package com.example.myapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            switch (action) {
                case Intent.ACTION_BATTERY_LOW:
                    showNotification(context, "Battery Low", "Your battery is low!");
                    break;
                case Intent.ACTION_BATTERY_OKAY:
                    Toast.makeText(context, "Battery OK", Toast.LENGTH_SHORT).show();
                    break;
                case "com.example.myapp.SHOW_NOTIFICATION":
                    showNotification(context, "Custom Notification", "This is a custom notification");
                    break;
            }
        }
    }

    private void showNotification(Context context, String title, String text) {
        NotificationChannel channel = new NotificationChannel(
            "notification_channel", "Notifications", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = context.getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        Intent intent = new Intent(context, PermissionActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new Notification.Builder(context, "notification_channel")
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .build();

        manager.notify((int) System.currentTimeMillis(), notification);
    }
}
