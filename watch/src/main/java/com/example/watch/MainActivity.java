package com.example.watch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.watch.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.favorite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenFavorites();
            }
        });
        findViewById(R.id.discussions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDiscuss();
            }
        });


    }

    private void OpenFavorites()  {
        Intent intent = new Intent(this, MoviesActivity.class);
        startActivity(intent);
    }
    private void OpenDiscuss() {
        Intent intent = new Intent(this, DiscussionsActivity.class);
        startActivity(intent);
    }
}