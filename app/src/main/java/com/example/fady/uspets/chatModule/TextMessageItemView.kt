package com.example.fady.uspets.chatModule

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.RelativeLayout.ALIGN_PARENT_END
import android.widget.RelativeLayout.CENTER_HORIZONTAL
import com.example.fady.uspets.R
import com.example.fady.uspets.messagesModule.MessageModel
import kotlinx.android.synthetic.main.text_message_item.view.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class TextMessageItemView(context: Context) : LinearLayout(context) {
    init {
        LayoutInflater.from(context).inflate(R.layout.text_message_item, this)
    }


    @SuppressLint("RtlHardcoded")
    fun setTextAsSender(messageModel: MessageModel) {
        llMessageContainer.gravity = Gravity.RIGHT;
        rlMessageContainer.setBackgroundResource(R.drawable.chat_sender_background)
        tvChatMessage.text = messageModel.message
        setMessageTime(messageModel)
    }

    @SuppressLint("RtlHardcoded")
    fun setTextAsReceiver(messageModel: MessageModel) {
        llMessageContainer.gravity = Gravity.LEFT;
        rlMessageContainer.setBackgroundResource(R.drawable.chat_receiver_background)
        tvChatMessage.text = messageModel.message


        setMessageTime(messageModel)
    }

    private fun setMessageTime(messageModel: MessageModel) {
        val dateFormat = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.SHORT)
        tvMessageTime.text = dateFormat.format(messageModel.time)
    }

    fun clearText() {
        tvChatMessage.text = ""
    }
}
