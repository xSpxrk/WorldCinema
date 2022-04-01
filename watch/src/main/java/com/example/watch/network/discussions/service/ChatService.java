package com.example.watch.network.discussions.service;

import com.example.watch.network.discussions.models.ChatsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ChatService {
    @GET("/user/chats")
    Call<List<ChatsResponse>> getChats(@Header("Authorization") String token);
}
