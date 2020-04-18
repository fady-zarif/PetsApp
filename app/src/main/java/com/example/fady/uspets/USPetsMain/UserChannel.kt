package com.example.fady.uspets.USPetsMain

import android.os.Parcel
import android.os.Parcelable

data class UserChannel(var uid: String?, var channelId: String?):Parcelable
{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    constructor():this("","")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uid)
        parcel.writeString(channelId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserChannel> {
        override fun createFromParcel(parcel: Parcel): UserChannel {
            return UserChannel(parcel)
        }

        override fun newArray(size: Int): Array<UserChannel?> {
            return arrayOfNulls(size)
        }
    }
}