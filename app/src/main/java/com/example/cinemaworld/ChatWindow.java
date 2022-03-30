package com.example.cinemaworld;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.cinemaworld.adapters.ChatAdapter;
import com.example.cinemaworld.network.ApiHandler;
import com.example.cinemaworld.network.chats.models.MessageResponse;
import com.example.cinemaworld.network.chats.service.ChatService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatWindow extends AppCompatActivity {

    RecyclerView recyclerView;
    ChatService serviceMessages = ApiHandler.getInstance().getMessages();
    ChatService serviceChatId

    String movieId, token;

    private ArrayList<MessageResponse> messages;
    private ChatAdapter adapter;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        Intent intent = getIntent();
        recyclerView = findViewById(R.id.rv_messages);

        preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        movieId = intent.getStringExtra("movieId");
        Log.d(TAG, "onCreate: " + token);

        findViewById(R.id.arrow_back).setOnClickListener(view -> {
            goBack();
        });
        getMessages();


    }

    private void goBack() {
        finish();
    }

    private String getChatId() {
        AsyncTask.execute(() -> {

        });
    }


    private void getMessages(String messageId) {
        AsyncTask.execute(() -> {
            serviceMessages.getMessages(token, messageId).enqueue(new Callback<List<MessageResponse>>() {
                @Override
                public void onResponse(Call<List<MessageResponse>> call, Response<List<MessageResponse>> response) {
                    Log.d(TAG, "" + response.body());
                }
                @Override
                public void onFailure(Call<List<MessageResponse>> call, Throwable t) {

                }
            });
        });
    }
}