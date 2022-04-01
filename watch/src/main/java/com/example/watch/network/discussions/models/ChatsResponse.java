package com.example.watch.network.discussions.models;

import com.google.gson.annotations.SerializedName;

public class ChatsResponse {
    @SerializedName("chatId")
    private String chatId;
    @SerializedName("name")
    private String name;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
