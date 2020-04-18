package com.example.fady.uspets.chatModule

import android.app.Activity
import android.util.Log
import com.example.fady.uspets.FirebaseDatabase.FirebaseChatClass
import com.example.fady.uspets.FirebaseDatabase.FirebaseConstant.MESSAGE_TYPE_TEXT
import com.example.fady.uspets.USPetsMain.PetUiManager
import com.example.fady.uspets.messagesModule.MessageAdapterItemModel
import com.example.fady.uspets.messagesModule.MessageModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import java.util.*
import javax.inject.Inject

class ChatPresenter @Inject constructor(activity: Activity) : IChatModule.IChatPresenter {
    private var myActivity: Activity = activity;
    private val iChatView = activity as IChatModule.IChatView
    private lateinit var messageAdapterItemModel: MessageAdapterItemModel


    @Inject
    lateinit var firebaseChatClass: FirebaseChatClass


    override fun getChatChannel(channelId: String) {
        firebaseChatClass.getChatFromChannel(channelId).addSnapshotListener(myActivity) { queryDocumentSnapShot, error ->
            run {
                if (error != null)
                    return@run
                if (queryDocumentSnapShot != null) {
                    queryDocumentSnapShot.size()
                    queryDocumentSnapShot.documentChanges
                    for (documentSnapShot in queryDocumentSnapShot.documentChanges) {
                        if (documentSnapShot.type == DocumentChange.Type.ADDED) {
                            val chatModel = documentSnapShot.document.toObject(MessageModel::class.java)
                            iChatView.onChannelSuccess(chatModel)
                        }
                    }
                }
            }
        }

    }

    override fun setMessageItemModel(messageAdapterItemModel: MessageAdapterItemModel) {
        this.messageAdapterItemModel = messageAdapterItemModel
    }

    override fun onSendMessage(message: String, type: String) {
        val messageModel = messageAdapterItemModel.userData?.getoUid()?.let {
            MessageModel(message, PetUiManager.getInstance().currentUser.getoUid(), it,
                    firebaseChatClass.newDocument, Calendar.getInstance().time, type)
        }
        firebaseChatClass.uploadMessage(messageAdapterItemModel.channelId, {

            Log.d("ChatPresenter", "onSendMessage:" + "Success")
        }, messageModel)
        iChatView.onSendMessageSuccess()

    }

    private fun getMessageItemModel(): MessageAdapterItemModel {
        return messageAdapterItemModel
    }
}