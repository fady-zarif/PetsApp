package com.example.fady.uspets.chatModule

import com.example.fady.uspets.messagesModule.MessageAdapterItemModel
import com.example.fady.uspets.messagesModule.MessageModel

interface IChatModule {
    interface IChatView {
        fun onChannelSuccess(messageModel: MessageModel)
        fun onSendMessageSuccess()

    }

    interface IChatPresenter {
        fun getChatChannel(channelId: String)
        fun setMessageItemModel(messageAdapterItemModel: MessageAdapterItemModel)
        fun onSendMessage(message: String, type: String)
    }
}