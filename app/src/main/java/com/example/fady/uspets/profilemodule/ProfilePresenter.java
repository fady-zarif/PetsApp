package com.example.fady.uspets.profilemodule;

import android.app.Activity;

import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass;
import com.example.fady.uspets.USPetsMain.PetUiManager;

import javax.inject.Inject;

public class ProfilePresenter implements Iprofile.IPresenter {
    Iprofile.IView iView;


    @Inject
    FirebaseUserClass firebaseUserClass;

    @Inject
    public ProfilePresenter(Activity activity) {
        iView = (Iprofile.IView) activity;
    }

    @Override
    public void getUserData() {
        String userPic = PetUiManager.getInstance().getCurrentUser().getoPhoto();
        if (!userPic.isEmpty() && userPic != null) {
            iView.showUserProfilePic(userPic);
        }
        iView.showUserInfo(PetUiManager.getInstance().getCurrentUser());
    }


}


