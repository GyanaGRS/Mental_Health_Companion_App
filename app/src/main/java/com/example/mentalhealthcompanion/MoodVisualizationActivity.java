//package com.example.mentalhealthcompanion;
//
//
//import android.database.Cursor;
//
//import android.os.Bundle;
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import com.androidplot.xy.BarFormatter;
//import com.androidplot.xy.BarRenderer;
//import com.androidplot.xy.BoundaryMode;
//import com.androidplot.xy.SimpleXYSeries;
//import com.androidplot.xy.XYPlot;
//import com.androidplot.xy.XYSeries;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class MoodVisualizationActivity extends AppCompatActivity {
//
//    private XYPlot xyPlot;
//    private MoodDatabaseHelper moodDatabaseHelper;
//
//    private void createHistogram() {
//        List<Integer> moodCounts = new ArrayList<>();
//        List<String> moodLabels = new ArrayList<>();
//
//        // Retrieve mood data from the database
//        Cursor cursor = moodDatabaseHelper.getMoodLogs();
//        if (cursor != null && cursor.moveToFirst()) {
//            try {
//                int happyCount = 0;
//                int contentCount = 0;
//                int neutralCount = 0;
//                int sadCount = 0;
//                int angryCount = 0;
//
//                do {
//                    String emotion = cursor.getString(cursor.getColumnIndexOrThrow(MoodDatabaseHelper.COLUMN_EMOTION));
//                    switch (emotion.toLowerCase()) {
//                        case "happy":
//                            happyCount++;
//                            break;
//                        case "content":
//                            contentCount++;
//                            break;
//                        case "neutral":
//                            neutralCount++;
//                            break;
//                        case "sad":
//                            sadCount++;
//                            break;
//                        case "angry":
//                            angryCount++;
//                            break;
//                    }
//                } while (cursor.moveToNext());
//
//                // Populate mood counts and labels
//                moodLabels.add("Happy");
//                moodLabels.add("Content");
//                moodLabels.add("Neutral");
//                moodLabels.add("Sad");
//                moodLabels.add("Angry");
//
//                moodCounts.add(happyCount);
//                moodCounts.add(contentCount);
//                moodCounts.add(neutralCount);
//                moodCounts.add(sadCount);
//                moodCounts.add(angryCount);
//
//            } finally {
//                cursor.close(); // Close the cursor to prevent leaks
//            }
//        }
//
//        // Create a series for the histogram
//        XYSeries series = new SimpleXYSeries(
//                moodCounts,
//                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
//                "Mood Counts"
//        );
//
//        // Create a formatter for the histogram
//        BarFormatter barFormatter = new BarFormatter(
//                getResources().getColor(android.R.color.holo_blue_light),
//                getResources().getColor(android.R.color.holo_blue_dark)
//        );
//
//        // Add the series to the plot
//        xyPlot.addSeries(series, barFormatter);
//
//        // Set plot properties
//        xyPlot.setDomainLabel("Mood");
//        xyPlot.setRangeLabel("Count");
//
//        // Set domain and range bounds
//        xyPlot.setDomainBoundaries(0, moodLabels.size() - 1, BoundaryMode.FIXED);
//        xyPlot.setRangeBoundaries(0, Collections.max(moodCounts), BoundaryMode.FIXED);
//
//        // Refresh the plot
//        xyPlot.redraw();
//    }
//
//}
