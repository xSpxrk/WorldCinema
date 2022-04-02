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

    // Конструктор
    public ChatAdapter(ArrayList<MessageResponse> chats) {
        this.messages = chats;

    }
    // Определение, при создании элемента, к какому типу он относиться
    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_from_users, parent, false);

        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_from_user, parent, false);

        }
        return new ViewHolder(view);
    }
    // Заполение элементов компоновки, которые нам необходимы
    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        MessageResponse message = messages.get(position);

        holder.setTextMessage(message.getText());
        holder.setTextName(message.getFirstName() + " " + message.getLastName());
        holder.setTime(message.getCreationDateTime());

    }
    // Получение типа элемента адаптера
    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getViewType();
    }
    // Получение количество элементов в адаптере
    @Override
    public int getItemCount() {
        return messages.size();
    }

    // Класс, где мы инициализируем объекты компоновки
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
