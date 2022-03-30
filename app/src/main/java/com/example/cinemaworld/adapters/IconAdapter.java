package com.example.cinemaworld.adapters;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cinemaworld.R;
import com.example.cinemaworld.pages.collections.IconItem;

import java.util.ArrayList;
import java.util.List;

public class IconAdapter extends ArrayAdapter<IconItem> {

    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;

    private List<IconItem> iconList = new ArrayList<>();
    private int customLayoutId;
    private Context context;

    public IconAdapter(@NonNull Context context, int resource, @NonNull List<IconItem> iconList) {
        super(context, resource, iconList);
        this.context = context;
        this.iconList = iconList;
        customLayoutId = resource;
        preferences = context.getSharedPreferences("iconId", Context.MODE_PRIVATE);
        editor = context.getSharedPreferences("iconId", Context.MODE_PRIVATE).edit();
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(customLayoutId, null);
        }

        ImageView imageView = v.findViewById(R.id.image);
        IconItem iconItem  =  iconList.get(position);
        imageView.setImageResource(iconItem.getIcon_id());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("iconId", iconItem.getIcon_id()).apply();
                Log.d(TAG, "IconId = " + iconItem.getIcon_id());
                ((Activity)context).finish();
            }
        });
        return v;
    }
}
