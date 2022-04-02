package com.example.cinemaworld.network.auth.service;

import com.example.cinemaworld.network.auth.models.LoginBody;
import com.example.cinemaworld.network.auth.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
// Интерфейс для отправки запросов и получения данных API
public interface LoginService {
    // Указываем метод запроса и ссылку к подключению
    @POST("/auth/login")
    Call<LoginResponse> getData(@Body LoginBody loginBody);
}
