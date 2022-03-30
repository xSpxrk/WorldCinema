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

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private ArrayList<MessageResponse> messages;
    private LayoutInflater inflater;
    private Context context;

    public ChatAdapter(ArrayList<MessageResponse> chats, Context context) {
        this.messages = chats;
        this.inflater = LayoutInflater.from(context);
        this.context = context;

    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = inflater.inflate(R.layout.message_from_users, parent, false);
        return new ChatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        MessageResponse message = messages.get(position);

        holder.setTextMessage(message.getText());
        holder.setTextName(message.getFirstName() + " " + message.getLastName());
        holder.setTime(message.getCreationDateTime());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final private TextView text;
        final private TextView name;
        final private TextView time;
        final private ImageView icon;

        private ViewHolder(View view) {
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
