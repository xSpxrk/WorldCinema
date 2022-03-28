package com.example.cinemaworld.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaworld.R;
import com.example.cinemaworld.pages.collections.Collection;

import java.util.ArrayList;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private ArrayList<Collection> collections;
    private LayoutInflater inflater;
    private Context context;

    public CollectionAdapter(ArrayList<Collection> collections, Context context) {
    this.collections = collections;
    this.inflater = LayoutInflater.from(context);
    this.context = context;

    }

    @NonNull
    @Override
    public CollectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = inflater.inflate(R.layout.collection_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.ViewHolder holder, int position) {
        Collection collection = collections.get(position);
        holder.setTextCollection(collection.getBrand());
        holder.setIconCollection(collection.getImage());
    }

    @Override
    public int getItemCount() {
        return collections.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final private ImageView iconCollection;
        final private TextView txtCollection;

        private ViewHolder(View view) {

            super(view);
            this.iconCollection = (ImageView) view.findViewById(R.id.iv_brand);
            this.txtCollection = (TextView) view.findViewById(R.id.tv_brand);
        }

        public void setTextCollection(String text) {
            this.txtCollection.setText(text);
        }

        public void setIconCollection(int icon) {
            this.iconCollection.setImageResource(icon);
        }
    }
}
