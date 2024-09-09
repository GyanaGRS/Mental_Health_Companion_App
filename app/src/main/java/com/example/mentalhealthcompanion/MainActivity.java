package com.example.mentalhealthcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    TextView textView, usernametxt;
    FirebaseUser user;
    Toolbar toolbar;

    Menu opt_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.userdetails);
        user = auth.getCurrentUser();
        usernametxt= findViewById(R.id.username);
        toolbar= findViewById(R.id.toolbar);

        if (user == null) {
            // If the user is not authenticated, redirect to the LoginActivity
            Intent iLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(iLogin);
            finish();
        } else {
            // Retrieve and display the user's email and display name
            String email = user.getEmail();
            String displayName = user.getDisplayName();


                usernametxt.setText("Welcome, " + displayName);
                textView.setText(email);
        }

        setSupportActionBar(toolbar);

        if(getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setTitle("My ToolBar");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.opt_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId= item.getItemId();

        if(itemId==R.id.opt_profile) {
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
        }
        else if(itemId==R.id.opt_contactSupport) {
            Toast.makeText(this, "ContactSupport", Toast.LENGTH_SHORT).show();
        }
        else if(itemId==R.id.opt_about) {
            Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
        }
        else if(itemId==android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
        } else if (itemId==R.id.opt_logout) {

            auth.signOut();
            Intent iLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(iLogin);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }
}
