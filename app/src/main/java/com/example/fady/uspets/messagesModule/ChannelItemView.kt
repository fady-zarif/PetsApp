package com.example.fady.uspets.messagesModule

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.fady.uspets.Owner
import com.example.fady.uspets.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.channel_item_view.view.*

class ChannelItemView(context: Context) : LinearLayout(context) {


    init {
        LayoutInflater.from(context).inflate(R.layout.channel_item_view, this)
    }

    fun setItemView(user: Owner?, channelId: String?) {
        setImageView(user?.getoPhoto())
        setUserName(user?.getoName())
    }


    private fun setUserName(name: String?) {
        tvChannelUserName.text = name
    }

    //
    private fun setUserRate() {

    }

    private fun setImageView(imageUrl: String?) {
        if (imageUrl != null)
            Picasso.get()
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.loading_image)
                    .resize(120, 120)
                    .into(imgChannelUser)

    }
}