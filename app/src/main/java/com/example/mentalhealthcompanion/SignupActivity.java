package com.example.mentalhealthcompanion;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity {

    private EditText edtxtUsername, edtxtEmail, edtxtPassword, edtxtRePassword;
    private AppCompatButton registerBtn;
    private ProgressBar progressBar;
    private TextView loginHere;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        // Initialize Views
        edtxtUsername = findViewById(R.id.edtxt_name);
        edtxtEmail = findViewById(R.id.edtxt_email);
        edtxtPassword = findViewById(R.id.edtxt_password);
        edtxtRePassword = findViewById(R.id.edtxt_repassword);
        registerBtn = findViewById(R.id.registerbtn);
        progressBar = findViewById(R.id.progressbar);
        loginHere = findViewById(R.id.loginhere);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Set up Lottie Animation
        LottieAnimationView signupAnim = findViewById(R.id.signup_anim);
        signupAnim.setRepeatCount(ValueAnimator.INFINITE);

        // Handle Login Here click
        loginHere.setOnClickListener(v -> {
            Intent iLogin = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(iLogin);
            finish();
        });

        // Handle Register Button click
        registerBtn.setOnClickListener(v -> {
            // Show Progress Bar
            progressBar.setVisibility(View.VISIBLE);

            // Get user inputs
            String username = edtxtUsername.getText().toString().trim();
            String email = edtxtEmail.getText().toString().trim();
            String password = edtxtPassword.getText().toString().trim();
            String rePassword = edtxtRePassword.getText().toString().trim();

            // Validate inputs
            if (TextUtils.isEmpty(username)) {
                edtxtUsername.setError("Username is required");
                edtxtUsername.requestFocus();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (TextUtils.isEmpty(email)) {
                edtxtEmail.setError("Email is required");
                edtxtEmail.requestFocus();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edtxtEmail.setError("Enter a valid email");
                edtxtEmail.requestFocus();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (TextUtils.isEmpty(password)) {
                edtxtPassword.setError("Password is required");
                edtxtPassword.requestFocus();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (password.length() < 6) {
                edtxtPassword.setError("Password should be at least 6 characters");
                edtxtPassword.requestFocus();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (TextUtils.isEmpty(rePassword)) {
                edtxtRePassword.setError("Please re-enter password");
                edtxtRePassword.requestFocus();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (!password.equals(rePassword)) {
                edtxtRePassword.setError("Passwords do not match");
                edtxtRePassword.requestFocus();
                progressBar.setVisibility(View.GONE);
                return;
            }

            // Create user with email and password
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Registration successful, update user profile with username
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(username)
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(profileTask -> {
                                            if (profileTask.isSuccessful()) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(SignupActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                                                // Redirect to MainActivity or LoginActivity
                                                Intent iMain = new Intent(SignupActivity.this, MainActivity.class);
                                                startActivity(iMain);
                                                finish();
                                            } else {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(SignupActivity.this, "Failed to update profile: " + profileTask.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        });
                            }
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(SignupActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });

        // Handle Window Insets (Optional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
