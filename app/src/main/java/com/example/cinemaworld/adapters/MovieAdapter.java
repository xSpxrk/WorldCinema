package com.example.cinemaworld.adapters;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cinemaworld.ChatWindow;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaworld.R;
import com.example.cinemaworld.network.movies.models.MovieResponse;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<MovieResponse> movieResponses;
    private LayoutInflater inflater;
    private Context context;

    public MovieAdapter(ArrayList<MovieResponse> movieResponse, Context context) {
        this.movieResponses = movieResponse;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = inflater.inflate(R.layout.cover_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        MovieResponse movieResponse = movieResponses.get(position);
        holder.setTextCinema(movieResponse.getName());
        holder.layout.setOnClickListener(view -> {
            holder.startChat(movieResponse.getMovieId());
        });
        Picasso.with(context).load("http://cinema.areas.su/up/images/" + movieResponse.getPoster()).into(holder.coverCinema);
    }

    @Override
    public int getItemCount() {
        return movieResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final private ImageView coverCinema;
        final private TextView txtCinema;
        final private LinearLayout layout;

        private ViewHolder(View view) {
            super(view);
            this.coverCinema = (ImageView) view.findViewById(R.id.image_cinema_cover);
            this.txtCinema = (TextView) view.findViewById(R.id.txt_cinema_cover);
            this.layout = (LinearLayout) view.findViewById(R.id.linear);
        }

        public void setTextCinema(String text){
            this.txtCinema.setText(text);
        }
        public void startChat(String movieId) {
            Intent intent = new Intent(context, ChatWindow.class);
            intent.putExtra("movieId", movieId);
            context.startActivity(intent);
        }
    }
}