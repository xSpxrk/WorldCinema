package com.example.cinemaworld.network.chats.models;

import com.google.gson.annotations.SerializedName;
// Класс для сериализации кода и имени чата
public class ChatResponse {
    @SerializedName("chatId")
    String chatId;
    @SerializedName("name")
    String name;

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
