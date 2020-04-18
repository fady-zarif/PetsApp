package com.example.fady.uspets.chatModule;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fady.uspets.USPetsMain.PetUiManager;
import com.example.fady.uspets.messagesModule.MessageModel;

import java.util.ArrayList;
import java.util.List;

public  class ChatModuleRecyclerAdapter extends RecyclerView.Adapter<ChatModuleRecyclerAdapter.MessagesViewHolder> {

    private List<MessageModel> messageModelsArrayList;
    private Context context;

    public ChatModuleRecyclerAdapter(Context context, List<MessageModel> messageModelsArrayList) {
        this.messageModelsArrayList = messageModelsArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessagesViewHolder(new TextMessageItemView(context));
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesViewHolder holder, int position) {
        if (messageModelsArrayList.get(position).getSenderId().equals(PetUiManager.getInstance().getCurrentUser().getoUid()))
            holder.itemView.setTextAsSender(messageModelsArrayList.get(position));
        else
            holder.itemView.setTextAsReceiver(messageModelsArrayList.get(position));


    }

    @Override
    public int getItemCount() {
        return messageModelsArrayList.size();
    }

    static class MessagesViewHolder extends RecyclerView.ViewHolder {
        TextMessageItemView itemView;

        MessagesViewHolder(@NonNull TextMessageItemView itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
