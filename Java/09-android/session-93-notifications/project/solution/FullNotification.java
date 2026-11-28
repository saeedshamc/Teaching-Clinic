// FullNotification.java — پاسخ کامل پروژهٔ جلسه ۹۳
// نیازمند Android SDK
package com.example.myapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class FullNotification {

    private static final String CHANNEL_ID = "full_channel";

    public static void createChannel(Context context) {
        NotificationChannel channel = new NotificationChannel(
            CHANNEL_ID, "Full Notifications", NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription("Full notification channel");
        channel.enableVibration(true);
        channel.setShowBadge(true);
        context.getSystemService(NotificationManager.class).createNotificationChannel(channel);
    }

    public static void showSimple(Context context) {
        createChannel(context);
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Simple")
            .setContentText("Simple notification")
            .setContentIntent(pi)
            .setAutoCancel(true);

        NotificationManagerCompat.from(context).notify(1, builder.build());
    }

    public static void showRich(Context context) {
        createChannel(context);
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        PendingIntent action1 = PendingIntent.getActivity(context, 1, intent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        PendingIntent action2 = PendingIntent.getActivity(context, 2, intent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Rich Notification")
            .setContentText("This is a rich notification")
            .setStyle(new NotificationCompat.BigTextStyle().bigText("Long text content..."))
            .addAction(android.R.drawable.ic_menu_share, "Share", action1)
            .addAction(android.R.drawable.ic_menu_save, "Save", action2)
            .setContentIntent(pi)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat.from(context).notify(2, builder.build());
    }
}
