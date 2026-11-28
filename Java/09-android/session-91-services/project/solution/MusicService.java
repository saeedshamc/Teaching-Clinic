// MusicService.java — پاسخ پروژهٔ جلسه ۹۱
// نیازمند Android SDK
package com.example.myapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class MusicService extends Service {

    private boolean isPlaying = false;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if (action != null) {
            switch (action) {
                case "PLAY": isPlaying = true; break;
                case "PAUSE": isPlaying = false; break;
                case "STOP": stopSelf(); break;
            }
        }

        Notification notification = createNotification();
        startForeground(1, notification);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification createNotification() {
        NotificationChannel channel = new NotificationChannel(
            "music_channel", "Music", NotificationManager.IMPORTANCE_LOW);
        getSystemService(NotificationManager.class).createNotificationChannel(channel);

        return new Notification.Builder(this, "music_channel")
            .setContentTitle("Music Player")
            .setContentText(isPlaying ? "Playing" : "Paused")
            .setSmallIcon(android.R.drawable.ic_media_play)
            .build();
    }
}
