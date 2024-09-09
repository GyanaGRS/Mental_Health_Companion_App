package com.example.mentalhealthcompanion;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    TextView splash_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        splash_text= findViewById(R.id.splash_text);

        LottieAnimationView loadingAnimation = findViewById(R.id.loading_animation);
        LottieAnimationView splashAnim= findViewById(R.id.splash_anim);
        loadingAnimation.setRepeatCount(ValueAnimator.INFINITE); // This makes the animation loop indefinitely
        splashAnim.setRepeatCount(ValueAnimator.INFINITE);

        Animation alpha= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
        splash_text.setAnimation(alpha);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent iHome= new Intent(SplashScreen.this, OnboardingScreen.class);
                startActivity(iHome);
                finish();
            }
        },4200);

    }
}