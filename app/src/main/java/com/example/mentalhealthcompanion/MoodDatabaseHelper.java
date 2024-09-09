package com.example.mentalhealthcompanion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MoodDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MoodTracker.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "mood_logs";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date";
    public static final String COLUMN_EMOTION = "emotion";
    private static final String COLUMN_THOUGHTS = "thoughts";
    private static final String COLUMN_ACTIVITIES = "activities";

    public MoodDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_EMOTION + " TEXT, " +
                COLUMN_THOUGHTS + " TEXT, " +
                COLUMN_ACTIVITIES + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertMoodLog(String date, String emotion, String thoughts, String activities) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_DATE, date);
            contentValues.put(COLUMN_EMOTION, emotion);
            contentValues.put(COLUMN_THOUGHTS, thoughts);
            contentValues.put(COLUMN_ACTIVITIES, activities);

            long result = db.insert(TABLE_NAME, null, contentValues);
            return result != -1; // Returns true if insertion is successful
        } finally {
            if (db != null) {
                db.close(); // Close the database to prevent leaks
            }
        }
    }

    public Cursor getMoodLogs() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
