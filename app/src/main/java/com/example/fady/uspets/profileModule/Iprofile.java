package com.example.fady.uspets.profileModule;

import android.util.Log;

import com.example.fady.uspets.Owner;

public interface Iprofile {

    interface IView {
        void showUserProfilePic(String imgUrl);

        void showUserInfo(Owner owner);

        void onUpdatePhotoSuccess(String url);

        void onUpdatePhotoFailed(String errorMessage);

    }

    interface IPresenter {
        void getUserData();

        void uploadUserImage(String image);
    }
}
