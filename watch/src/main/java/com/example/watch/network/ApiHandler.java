package com.example.watch.network;


import com.example.watch.network.auth.service.LoginService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandler {

    private static ApiHandler mInstance;

    private static String BASE_URL = "http://cinema.areas.su/";

    private Retrofit retrofit;

    public ApiHandler() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiHandler getInstance() {
        if (mInstance == null)
            mInstance = new ApiHandler();
        return mInstance;
    }

    public LoginService getLogin() {
        return retrofit.create(LoginService.class);
    }
}
