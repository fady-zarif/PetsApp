package com.example.fady.uspets.profilemodule;

import android.util.Log;

import com.example.fady.uspets.Owner;

public interface Iprofile {

    interface IView {
        void showUserProfilePic(String imgUrl);

        void showUserInfo(Owner owner);

        default void hello() {
            Log.e("geey", "aaa0");
        }

    }

    interface IPresenter {
        void getUserData();
    }
}
