package com.example.cinemaworld.network.chats.service;

import com.example.cinemaworld.network.chats.models.ChatResponse;
import com.example.cinemaworld.network.chats.models.MessageBody;
import com.example.cinemaworld.network.chats.models.MessageResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChatService {
    // Получение сообщений
    @GET("/chats/{chatId}/messages")
    Call<List<MessageResponse>> getMessages(@Header("Authorization") String token, @Path("chatId") String chatId);
    // Получние чата по id фильма
    @GET("/chats/{movieId}")
    Call<List<ChatResponse>> getChatId(@Path("movieId") String chatId);
    // Отправка сообщения
    @POST("/chats/{chatId}/messages")
    Call<ChatResponse> sendMessage(@Header("Authorization") String token, @Path("chatId") String chatId, @Body MessageBody message);
}
