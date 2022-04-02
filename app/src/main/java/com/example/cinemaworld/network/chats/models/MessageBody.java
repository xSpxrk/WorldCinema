package com.example.cinemaworld.network.chats.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
// Класс для сериализации текста сообщения и отправки
public class MessageBody {
    @SerializedName("text")
    @Expose
    private String text;

    public MessageBody(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
