package com.example.watch.network.movies.service;

import com.example.watch.network.movies.models.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesService {
    @GET("/movies?filter=new")
    Call<List<MoviesResponse>> getMovies();
}
