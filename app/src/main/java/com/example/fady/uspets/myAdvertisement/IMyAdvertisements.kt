package com.example.fady.uspets.myAdvertisement

import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementModel

interface IMyAdvertisements {
    interface Iview {
        fun onGetAdvertisementsSuccess(advertismentModel: AdvertisementModel)
        fun onGetAdvertisementsFailed(message: String)
    }

    interface Ipresenter {
        fun getMyAdvertisement()
    }


}