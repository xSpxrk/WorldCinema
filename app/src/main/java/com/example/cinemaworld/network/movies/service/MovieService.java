package com.example.cinemaworld.network.movies.service;

import com.example.cinemaworld.network.movies.models.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {
    @GET("movies?filter=new")
    Call<List<MovieResponse>> getMovies();
}
