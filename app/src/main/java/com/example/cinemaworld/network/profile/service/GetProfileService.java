package com.example.cinemaworld.network.profile.service;


import com.example.cinemaworld.network.profile.models.GetProfileResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface GetProfileService {
    @GET("/user")
    Call<List<GetProfileResponse>> getData(@Header("Authorization") String token);
}
