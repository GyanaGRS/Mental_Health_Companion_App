package com.example.mentalhealthcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText edtxtemail, edtxt_password;
    AppCompatButton loginbtn;
    TextView signuptxt;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent iDashboard= new Intent(LoginActivity.this, MainActivity.class);
            startActivity(iDashboard);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtxtemail= findViewById(R.id.edtxtemail);
        edtxt_password= findViewById(R.id.edtxt_password);
        loginbtn= findViewById(R.id.loginid);
        mAuth= FirebaseAuth.getInstance();
        signuptxt= findViewById(R.id.signup_txtid);
        progressBar= findViewById(R.id.progressbar);

        Intent iDashboard= new Intent(LoginActivity.this, MainActivity.class);
        Intent iSignup= new Intent(getApplicationContext(),SignupActivity.class);

        signuptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(iSignup);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;

                email= String.valueOf(edtxtemail.getText());
                password= String.valueOf(edtxt_password.getText());

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this, "Please enter email-id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                    startActivity(iDashboard);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

    }
}