package com.example.fady.uspets.profileModule

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.fady.uspets.FirebaseDatabase.FirebaseConstant
import com.example.fady.uspets.FirebaseDatabase.FirebaseStorageClass
import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass
import com.example.fady.uspets.USPetsMain.PetUiManager
import com.google.android.gms.tasks.OnSuccessListener
import java.lang.Exception
import javax.inject.Inject

class ProfilePresenter @Inject internal constructor(activity: Activity) : Iprofile.IPresenter {
    private val iView: Iprofile.IView = activity as Iprofile.IView
    @Inject
    lateinit var firebaseUserClass: FirebaseUserClass

    @Inject
    lateinit var firebaseStorageClass: FirebaseStorageClass

    @Inject
    lateinit var context: Context


    override fun getUserData() {
        val userPic:String? = PetUiManager.getInstance().currentUser?.getoPhoto()
        if (userPic!=null) {
            iView.showUserProfilePic(userPic)
        }
        iView.showUserInfo(PetUiManager.getInstance().currentUser)
    }

    override fun uploadUserImage(image: String?) {
        firebaseStorageClass.uploadImage(FirebaseConstant.USER_IMAGES_FOLDER, PetUiManager.getInstance().currentUser.getoName(),
                PetUiManager.getInstance().getByteArray(context, image), object : OnSuccessListener<Uri> {
            override fun onSuccess(uri: Uri?) {
                updateUserPic(uri.toString())
                PetUiManager.getInstance().currentUser.setoPhoto(uri.toString())
                iView.onUpdatePhotoSuccess(uri.toString())
            }

        }, object : FirebaseStorageClass.onError {
            override fun sendError(message: Exception?) {
                iView.onUpdatePhotoFailed(message?.localizedMessage)
            }

        })

    }

    private fun updateUserPic(url: String) {
        firebaseUserClass.updateUserPic(url)
    }
}