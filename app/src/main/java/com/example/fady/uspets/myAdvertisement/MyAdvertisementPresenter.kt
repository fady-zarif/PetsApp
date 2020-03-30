package com.example.fady.uspets.myAdvertisement

import android.app.Activity
import android.content.Context
import com.example.fady.uspets.FirebaseDatabase.FirebaseAdvertismentClass
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementModel
import com.example.fady.uspets.USPetsMain.PetUiManager
import javax.inject.Inject

class MyAdvertisementPresenter @Inject constructor(myActivity: Activity) : IMyAdvertisements.Ipresenter {

    var iView: IMyAdvertisements.Iview = myActivity as IMyAdvertisements.Iview
    @Inject
    lateinit var firebaseAdvertisementclass: FirebaseAdvertismentClass
    @Inject
    lateinit var context: Context

    override fun getMyAdvertisement() {
        firebaseAdvertisementclass.getmyAdvertisment(PetUiManager.getInstance().currentUser.getoUid()) {
            if (it.isSuccessful) {
                val docsSnapShot = it.result?.documents
                if (docsSnapShot != null)
                    for (snapshot in docsSnapShot) {
                        val advertisement = snapshot.toObject(AdvertisementModel::class.java)
                        if (advertisement != null)
                            iView.onGetAdvertisementsSuccess(advertisement)
                    }
            } else
                iView.onGetAdvertisementsFailed("Failed because of ${it.exception.toString()}")
        }
    }

}