package com.example.watch;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");
                Intent intent;
                if (token.equals("")) {
                    intent = new Intent(SplashScreen.this, Auth.class);
                } else
                    intent = new Intent(SplashScreen.this, MainActivity.class);

                SplashScreen.this.startActivity(intent);
                SplashScreen.this.finish();
            }
        }, 1000);
    }
}