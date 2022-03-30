package com.example.cinemaworld.pages.collections;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.cinemaworld.R;
import com.example.cinemaworld.adapters.IconAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChoosingIcon extends AppCompatActivity {

    GridView gridView;
    private List<IconItem> iconList = new ArrayList<>();
    private IconAdapter iconAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_icon);
        gridView = findViewById(R.id.gv_icons);

        for (int i = 0; i < 36; i++) {
            iconList.add(new IconItem(R.drawable.music));
        }

        iconAdapter = new IconAdapter(this, R.layout.icon_item, iconList);
        gridView.setAdapter(iconAdapter);
    }
}