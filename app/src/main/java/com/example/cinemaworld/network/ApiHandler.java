package com.example.cinemaworld.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import com.example.cinemaworld.network.auth.service.LoginService;
import com.example.cinemaworld.network.chats.service.ChatService;
import com.example.cinemaworld.network.movies.service.MovieService;
import com.example.cinemaworld.network.profile.service.GetProfileService;
import com.example.cinemaworld.network.registration.service.RegistrationService;

import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandler {

    private static ApiHandler mInstance;
    // Объявление строки подключения
    private static String BASE_URL = "http://cinema.areas.su/";
    // Объявление объекта класса Retrofit для запросов к API
    private Retrofit retrofit;

    public ApiHandler() {
        // Создание объектов для логгирования запросов
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        // Создание объекта для обращения к API
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create()) // Показываем, что будем получать и отдавать информацию в json-формате
                .build();
    }

    // Объявляем получение объекта класса ApiHandler
    public static ApiHandler getInstance() {
        if (mInstance == null) {
            mInstance = new ApiHandler();
        }
        return mInstance;
    }

    // Обращаемся к интерфейсу для отправки запроса авторизации
    public LoginService getLogin() {
        return retrofit.create(LoginService.class);
    }

    // Обращаемся к интерфейсу для отправки запроса регистрации
    public RegistrationService getRegistration() {
        return retrofit.create(RegistrationService.class);
    }

    // Обращаемся к интерфейсу для отправки запроса получения профиля
    public GetProfileService getProfileService() {
        return retrofit.create(GetProfileService.class);
    }

    // Обращаемся к интерфейсу для отправки запроса получение фильмов
    public MovieService getMovies() {
        return retrofit.create(MovieService.class);
    }

    // Обращаемся к интерфейсу для отправки запроса получение чата
    public ChatService getMessages() {
        return retrofit.create(ChatService.class);
    }
}