package com.example.mentalhealthcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class OnboardingScreen extends AppCompatActivity {

    private ViewPager2 viewpager;
    private Button previousBtn;
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onboarding_screen);

        viewpager = findViewById(R.id.viewpager);
        Button skipBtn = findViewById(R.id.skipbtn);
        previousBtn = findViewById(R.id.previousbtn);
        nextBtn = findViewById(R.id.nextbtn);



        // Set up the ViewPager2 with the adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewpager.setAdapter(adapter);

        // Set click listeners for the buttons
        skipBtn.setOnClickListener(v -> {
            // Skip to the main activity
            Intent intent = new Intent(OnboardingScreen.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        previousBtn.setOnClickListener(v -> {
            // Go to the previous page
            int currentItem = viewpager.getCurrentItem();
            if (currentItem > 0) {
                viewpager.setCurrentItem(currentItem - 1);
            }
        });

        nextBtn.setOnClickListener(v -> {
            // Go to the next page or finish the onboarding
            int currentItem = viewpager.getCurrentItem();
            if (currentItem < adapter.getItemCount() - 1) {
                viewpager.setCurrentItem(currentItem + 1);
            } else {
                // Last page, move to the main activity
                Intent intent = new Intent(OnboardingScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Disable previous button on the first page
        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                previousBtn.setEnabled(position != 0);
                nextBtn.setText(position == adapter.getItemCount() - 1 ? "Finish" : "Next");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void navigateToLoginActivity() {
        Intent intent = new Intent(OnboardingScreen.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
