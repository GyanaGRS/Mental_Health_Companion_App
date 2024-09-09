package com.example.mentalhealthcompanion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogMoodActivity extends AppCompatActivity {

    private MoodDatabaseHelper moodDatabaseHelper;
    private EditText editTextEmotion, editTextThoughts, editTextActivities;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_mood);

        moodDatabaseHelper = new MoodDatabaseHelper(this);

        editTextEmotion = findViewById(R.id.editTextEmotion);
        editTextThoughts = findViewById(R.id.editTextThoughts);
        editTextActivities = findViewById(R.id.editTextActivities);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emotion = editTextEmotion.getText().toString();
                String thoughts = editTextThoughts.getText().toString();
                String activities = editTextActivities.getText().toString();
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

                boolean isInserted = moodDatabaseHelper.insertMoodLog(date, emotion, thoughts, activities);
                if (isInserted) {
                    Toast.makeText(LogMoodActivity.this, "Mood logged successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LogMoodActivity.this, "Error logging mood.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
