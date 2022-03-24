package com.example.cinemaworld.network.registration.service;

import com.example.cinemaworld.network.registration.models.RegistrationBody;
import com.example.cinemaworld.network.registration.models.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegistrationService {
    @POST("/auth/register")
    Call<RegistrationResponse> getData(@Body RegistrationBody registrationBody);
}
