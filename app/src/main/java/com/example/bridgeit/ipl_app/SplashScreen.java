package com.example.bridgeit.ipl_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private final int M_SPLASH_TIME_OUT = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the app main activity
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                // close this activity
                finish();
            }
        }, M_SPLASH_TIME_OUT);

    }
}

