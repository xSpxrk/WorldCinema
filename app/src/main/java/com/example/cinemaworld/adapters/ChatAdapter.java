package com.example.cinemaworld.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaworld.R;
import com.example.cinemaworld.network.chats.models.MessageResponse;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter {

    private ArrayList<MessageResponse> messages;


    public ChatAdapter(ArrayList<MessageResponse> chats ) {
        this.messages = chats;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_from_users, parent, false);
            return new FirstViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_from_user, parent, false);
            return new SecondViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageResponse message = messages.get(position);
        if (message.getViewType() == 0) {
            ((FirstViewHolder)holder).setTextMessage(message.getText());
            ((FirstViewHolder)holder).setTextName(message.getFirstName() + " " + message.getLastName());
            ((FirstViewHolder)holder).setTime(message.getCreationDateTime());
        } else {
            ((SecondViewHolder)holder).setTextMessage(message.getText());
            ((SecondViewHolder)holder).setTextName(message.getFirstName() + " " + message.getLastName());
            ((SecondViewHolder)holder).setTime(message.getCreationDateTime());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class FirstViewHolder extends RecyclerView.ViewHolder {
        final private TextView text;
        final private TextView name;
        final private TextView time;
        final private ImageView icon;

        private FirstViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.txt_name);
            this.text = view.findViewById(R.id.txt_text);
            this.time = view.findViewById(R.id.txt_time);
            this.icon = view.findViewById(R.id.icon);
        }

        public void setTextMessage(String text) {
            this.text.setText(text);
        }

        public void setTextName(String name) {
            this.name.setText(name);
        }

        public void setTime(String time) {
            this.time.setText(time);
        }
    }

    public class SecondViewHolder extends RecyclerView.ViewHolder {
        final private TextView text;
        final private TextView name;
        final private TextView time;
        final private ImageView icon;

        private SecondViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.txt_name);
            this.text = view.findViewById(R.id.txt_text);
            this.time = view.findViewById(R.id.txt_time);
            this.icon = view.findViewById(R.id.icon);
        }

        public void setTextMessage(String text) {
            this.text.setText(text);
        }

        public void setTextName(String name) {
            this.name.setText(name);
        }

        public void setTime(String time) {
            this.time.setText(time);
        }
    }
}
