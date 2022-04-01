package com.example.watch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watch.R;
import com.example.watch.network.discussions.models.ChatsResponse;
import com.google.android.material.divider.MaterialDivider;

import java.util.ArrayList;

public class DiscusAdapter extends RecyclerView.Adapter<DiscusAdapter.ViewHolder> {

    private ArrayList<ChatsResponse> chats;
    private Context context;
    private LayoutInflater inflater;

    public DiscusAdapter(ArrayList<ChatsResponse> chats, Context context) {
        this.chats = chats;
        this.context = context;
        this.inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public DiscusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = inflater.inflate(R.layout.discussion_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscusAdapter.ViewHolder holder, int position) {
        ChatsResponse chat = chats.get(position);
        holder.SetText(chat.getName());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private LinearLayout divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.text_chat);
            this.divider = itemView.findViewById(R.id.divider);
        }

        public void SetText(String text) {
            this.textView.setText(text);
        }
    }
}
