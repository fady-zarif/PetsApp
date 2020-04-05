package com.example.fady.uspets.PetDetailsModule

import android.content.Context
import com.example.fady.uspets.R
import com.example.fady.uspets.USPetsMain.USPetMainAlertDialog
import kotlinx.android.synthetic.main.send_message_dialog.*

class SendMessageDialogView(context: Context, click: ISendMessage) : USPetMainAlertDialog(context, R.style.CustomDialog) {
    var sendclick = click;

    override fun getDialogContent(): Int = R.layout.send_message_dialog

    override fun onCreateDialog() {
        btnSendMessage.setOnClickListener()
        {
            if (etMessageDialog.text == null || etMessageDialog.text.toString().isEmpty())
                etMessageDialog.error = context.getString(R.string.required_error)
            else {
                sendclick.onSendMessage(etMessageDialog.text.toString())
                this.dismiss()

            }
        }

    }

    fun showDialog() {
        etMessageDialog?.text?.clear()
        etMessageDialog?.error = null
        this.show()
    }

    interface ISendMessage {
        fun onSendMessage(message: String)
    }
}