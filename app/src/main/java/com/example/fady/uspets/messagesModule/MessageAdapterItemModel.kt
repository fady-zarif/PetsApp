package com.example.fady.uspets.messagesModule

import android.os.Parcel
import android.os.Parcelable
import com.example.fady.uspets.Owner

data class MessageAdapterItemModel(val channelId: String?, val userData: Owner?) :Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readParcelable(Owner::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(channelId)
        parcel.writeParcelable(userData, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MessageAdapterItemModel> {
        override fun createFromParcel(parcel: Parcel): MessageAdapterItemModel {
            return MessageAdapterItemModel(parcel)
        }

        override fun newArray(size: Int): Array<MessageAdapterItemModel?> {
            return arrayOfNulls(size)
        }
    }
}