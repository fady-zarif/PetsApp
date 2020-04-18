package com.example.fady.uspets.messagesModule

import android.app.Activity
import android.util.Log
import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass
import com.example.fady.uspets.Owner
import com.example.fady.uspets.USPetsMain.PetUiManager
import com.google.android.gms.tasks.OnSuccessListener
import javax.inject.Inject

class MessageModulePresenter @Inject constructor(activity: Activity) : IMessageModule.IMessagePresenter {
    private var messageView = activity as IMessageModule.IMessageView
    @Inject
    lateinit var firebaseUserClass: FirebaseUserClass

    // passing lamda as param in Kotlin
    override fun getUserForEachChannel() {
        messageView.showProgressDialog()
        if (PetUiManager.getInstance().currentUser.userChannels != null && PetUiManager.getInstance().currentUser.userChannels.size != 0)
            for (channel in PetUiManager.getInstance().currentUser.userChannels) {

                Log.e("USER_CHANNELS ", "" + channel.uid)
                firebaseUserClass.getAnyUserInfo(channel.uid) {
                    val owner = it.toObject(Owner::class.java)
                    if (owner != null) messageView.displayChannel(channel.channelId!!, owner)
                }
            }
        messageView.dismissProgressDialog()
    }


}