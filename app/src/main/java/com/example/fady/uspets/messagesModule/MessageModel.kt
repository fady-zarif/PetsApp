package com.example.fady.uspets.messagesModule

import java.util.*

open class MessageModel(val message: String, val senderId: String, val receiverId: String, val messageId: String, val time: Date, val messaegType: String) {
    constructor() : this("", "", "", "", Date(0), "")

}