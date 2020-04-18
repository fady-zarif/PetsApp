package com.example.fady.uspets.messagesModule;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass;
import com.example.fady.uspets.Owner;
import com.example.fady.uspets.USPetsMain.UserChannel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MessageModuleRecyclerAdapter extends RecyclerView.Adapter<MessageModuleRecyclerAdapter.MessageModuleViewHolder> {
    private Context context;
    private List<MessageAdapterItemModel> userChannels;
    @Inject
    FirebaseUserClass firebaseUserClass;
    IMessageModule.IMessageChannelClick iMessageChannelClick;

    public MessageModuleRecyclerAdapter(Context context, List<MessageAdapterItemModel> userChannels, IMessageModule.IMessageChannelClick iMessageChannelClick) {
        this.context = context;
        this.userChannels = userChannels;
        this.iMessageChannelClick = iMessageChannelClick;
    }

    @NonNull
    @Override
    public MessageModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageModuleViewHolder(new ChannelItemView(context));
    }

    // passsing lamda as params JAVA
    @Override
    public void onBindViewHolder(@NonNull MessageModuleViewHolder holder, int position) {

        holder.channelItemView.setItemView(userChannels.get(position).getUserData(), userChannels.get(position).getChannelId());
        holder.channelItemView.setOnClickListener(it -> {
            iMessageChannelClick.onMessageChannelClickListner(userChannels.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return userChannels.size();
    }

    static class MessageModuleViewHolder extends RecyclerView.ViewHolder {
        ChannelItemView channelItemView;

        MessageModuleViewHolder(@NonNull ChannelItemView itemView) {
            super(itemView);
            this.channelItemView = itemView;
        }
    }

}
