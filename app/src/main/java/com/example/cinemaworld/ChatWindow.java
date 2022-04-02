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
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cinemaworld.adapters.ChatAdapter;
import com.example.cinemaworld.network.ApiHandler;
import com.example.cinemaworld.network.chats.models.ChatResponse;
import com.example.cinemaworld.network.chats.models.MessageBody;
import com.example.cinemaworld.network.chats.models.MessageResponse;
import com.example.cinemaworld.network.chats.service.ChatService;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    private String profileName;



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
        profileName = preferences.getString("ProfileName", "");


        findViewById(R.id.arrow_back).setOnClickListener(view -> {
            goBack();
        });
        getChatId();
        EditText editText = findViewById(R.id.textMessage);
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(token, chatId, new MessageBody(editText.getText().toString()));
                getMessages();
            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getMessages();
            }
        }, 0, 1000);



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
                        for (int i = 0; i < response.body().size(); i++) {
                            String name = messages.get(i).getFirstName() + " " + messages.get(i).getLastName();
                            if (name.equals(profileName))
                                messages.get(i).setViewType(1);
                        }
                        adapter = new ChatAdapter(messages);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.scrollToPosition(messages.size() - 1);
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


    private void sendMessage(String token, String chatId, MessageBody text) {
        AsyncTask.execute(() -> {
            service.sendMessage(token, chatId, text).enqueue(new Callback<ChatResponse>() {
                @Override
                public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {

                }

                @Override
                public void onFailure(Call<ChatResponse> call, Throwable t) {

                }
            });
        });
    }
}