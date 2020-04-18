package com.example.fady.uspets.messagesModule

import com.example.fady.uspets.Owner

interface IMessageModule {
    interface IMessageView {
        fun displayChannel(channelId: String, user: Owner)
        fun showProgressDialog()
        fun dismissProgressDialog()
    }

    interface IMessagePresenter {
        fun getUserForEachChannel()
    }

    interface IMessageChannelClick {
        fun onMessageChannelClickListner(messageItemModel: MessageAdapterItemModel)
    }
}