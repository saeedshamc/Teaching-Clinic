// MyService.java — service با started/bound/foreground modes
// نیازمند Android SDK
package com.example.myapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class MyService extends Service {

    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // ---------- Foreground service ----------
        Notification notification = createNotification();
        startForeground(1, notification);

        // ---------- Background work ----------
        new Thread(() -> {
            while (true) {
                System.out.println("Service running at " + System.currentTimeMillis());
                try { Thread.sleep(5000); } catch (InterruptedException e) {}
            }
        }).start();

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public String getData() {
        return "Data from service";
    }

    private Notification createNotification() {
        NotificationChannel channel = new NotificationChannel(
            "service_channel", "Service", NotificationManager.IMPORTANCE_LOW);
        getSystemService(NotificationManager.class).createNotificationChannel(channel);

        return new Notification.Builder(this, "service_channel")
            .setContentTitle("My Service")
            .setContentText("Running in background")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }
}
