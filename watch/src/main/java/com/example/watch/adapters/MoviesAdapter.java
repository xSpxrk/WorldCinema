package com.example.watch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watch.R;
import com.example.watch.network.movies.models.MoviesResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MoviesAdapter  extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    ArrayList<MoviesResponse> movies;
    Context context;
    LayoutInflater inflater;

    public MoviesAdapter(ArrayList<MoviesResponse> movies, Context context) {
        this.movies = movies;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = inflater.inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int position) {
        MoviesResponse movie = movies.get(position);
        holder.setTitle(movie.getName());
        Picasso.with(context).load("http://cinema.areas.su/up/images/" + movie.getPoster()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.iv_movie);
            title = itemView.findViewById(R.id.tv_title);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }
    }
}
