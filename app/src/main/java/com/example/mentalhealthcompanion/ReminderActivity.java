package com.example.mentalhealthcompanion;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ReminderActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private Button buttonSetReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        timePicker = findViewById(R.id.timePicker);
        buttonSetReminder = findViewById(R.id.buttonSetReminder);

        buttonSetReminder.setOnClickListener(v -> {
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);

            setReminder(calendar.getTimeInMillis());
        });
    }

    private void setReminder(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MoodReminderReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis,
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }
}
