package com.example.watch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.watch.adapters.MoviesAdapter;
import com.example.watch.network.ApiHandler;
import com.example.watch.network.movies.models.MoviesResponse;
import com.example.watch.network.movies.service.MoviesService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity {
    private static final String TAG = "MoviesActivity";

    MoviesService service = ApiHandler.getInstance().getMovies();
    RecyclerView recyclerView;
    ArrayList<MoviesResponse> moviesResponses;
    MoviesAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        recyclerView = findViewById(R.id.rv_movies);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        getMovies();
    }

    public void getMovies() {
        AsyncTask.execute(() -> {
            service.getMovies().enqueue(new Callback<List<MoviesResponse>>() {
                @Override
                public void onResponse(Call<List<MoviesResponse>> call, Response<List<MoviesResponse>> response) {
                    moviesResponses = new ArrayList<>(response.body());
                    adapter = new MoviesAdapter(moviesResponses, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }

                @Override
                public void onFailure(Call<List<MoviesResponse>> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });
        });
    }



}