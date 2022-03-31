package com.example.watch.network.auth.service;

import com.example.watch.network.auth.models.LoginBody;
import com.example.watch.network.auth.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/auth/login")
    Call<LoginResponse> getData(@Body LoginBody loginBody);
}
