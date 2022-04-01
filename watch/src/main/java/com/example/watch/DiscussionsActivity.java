package com.example.watch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.watch.adapters.DiscusAdapter;
import com.example.watch.network.ApiHandler;
import com.example.watch.network.discussions.models.ChatsResponse;
import com.example.watch.network.discussions.service.ChatService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscussionsActivity extends AppCompatActivity {

    ChatService service = ApiHandler.getInstance().getChats();

    SharedPreferences sharedPreferences;

    RecyclerView recyclerView;
    DiscusAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    ArrayList<ChatsResponse> chatsResponses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussions);
        recyclerView = findViewById(R.id.rv_chats);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        getChats(token);
    }


    private void getChats(String token) {
        AsyncTask.execute(() -> {
            service.getChats(token).enqueue(new Callback<List<ChatsResponse>>() {
                @Override
                public void onResponse(Call<List<ChatsResponse>> call, Response<List<ChatsResponse>> response) {
                    chatsResponses = new ArrayList<>(response.body());
                    adapter = new DiscusAdapter(chatsResponses, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<ChatsResponse>> call, Throwable t) {

                }
            });
        });
    }
}