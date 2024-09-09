package com.example.mentalhealthcompanion;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class MoodReminderReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "mood_reminder_channel";
    private static final String CHANNEL_NAME = "Mood Reminder";

    @Override
    public void onReceive(Context context, Intent intent) {
        // Create the NotificationManager
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Create the NotificationChannel for Android 8.0 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Channel for mood logging reminders");
            notificationManager.createNotificationChannel(channel);
        }

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.outline_notifications_24)  // Use your notification icon
                .setContentTitle("Time to Log Your Mood")
                .setContentText("How are you feeling today? Log your emotions now.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);  // Closes the notification after it's tapped

        // Set up the PendingIntent to open the LogMoodActivity
        Intent notificationIntent = new Intent(context, LogMoodActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(notificationIntent);
        PendingIntent notificationPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(notificationPendingIntent);

        // Use a unique notification ID (e.g., current time in milliseconds)
        int notificationId = (int) System.currentTimeMillis();

        // Show the notification
        notificationManager.notify(notificationId, builder.build());
    }
}
