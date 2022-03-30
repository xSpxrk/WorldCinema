package com.example.cinemaworld;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.cinemaworld.adapters.ChatAdapter;
import com.example.cinemaworld.network.ApiHandler;
import com.example.cinemaworld.network.chats.models.ChatResponse;
import com.example.cinemaworld.network.chats.models.MessageResponse;
import com.example.cinemaworld.network.chats.service.ChatService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatWindow extends AppCompatActivity {

    RecyclerView recyclerView;
    ChatService service = ApiHandler.getInstance().getMessages();
    private LinearLayoutManager linearLayoutManager;

    String token, movieId, chatId;

    private ArrayList<MessageResponse> messages;
    private ChatAdapter adapter;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        Intent intent = getIntent();
        recyclerView = findViewById(R.id.rv_messages);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        movieId = intent.getStringExtra("movieId");
        Log.d(TAG, "onCreate: " + token);

        findViewById(R.id.arrow_back).setOnClickListener(view -> {
            goBack();
        });
        getChatId();

    }

    private void goBack() {
        finish();
    }

    private void getChatId() {
        AsyncTask.execute(() -> {
            service.getChatId(movieId).enqueue(new Callback<List<ChatResponse>>() {
                @Override
                public void onResponse(Call<List<ChatResponse>> call, Response<List<ChatResponse>> response) {
                    chatId = response.body().get(0).getChatId();
                    Log.d(TAG, "chatId: " + chatId);
                    getMessages();
                }
                @Override
                public void onFailure(Call<List<ChatResponse>> call, Throwable t) {

                }
            });
        });
    }


    private void getMessages() {
        AsyncTask.execute(() -> {
            service.getMessages(token, chatId).enqueue(new Callback<List<MessageResponse>>() {
                @Override
                public void onResponse(Call<List<MessageResponse>> call, Response<List<MessageResponse>> response) {
                    if (response.isSuccessful()) {
                        messages = new ArrayList<>(response.body());
                        Log.d(TAG, "onResponse: " + messages.get(3));
                        adapter = new ChatAdapter(messages, getApplicationContext());
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        adapter.notifyDataSetChanged();
                    }

                }
                @Override
                public void onFailure(Call<List<MessageResponse>> call, Throwable t) {
                    Log.d(TAG, "GG" + t.getLocalizedMessage());
                }
            });
        });
    }
}