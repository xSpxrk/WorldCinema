package com.example.cinemaworld.pages.collections;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.cinemaworld.R;

public class BuildingCollection extends AppCompatActivity {
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_collection);
        image = findViewById(R.id.icon_image);
        findViewById(R.id.choose_icon).setOnClickListener(v -> {
            startActivity(new Intent(this, ChoosingIcon.class));
        });
    }

}