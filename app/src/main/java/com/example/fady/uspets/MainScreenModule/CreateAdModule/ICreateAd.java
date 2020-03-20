package com.example.fady.uspets.MainScreenModule.CreateAdModule;

import android.app.Activity;

import com.example.fady.uspets.MainScreenModule.AdvertismentModule.*;

public interface ICreateAd {

    interface IView {
        void showPetNameErrorMessage(String message);

        void showPetPriceErrorMessage(String message);

        void showProgressView();

        void dismissProgressView();

        void showPetAgeErrorMessage(String message);

        void showPetDescErrorMessage(String message);

        void showPicErrorMessage(String message);

        void onShareAdSuccess();

        void onShareAdFailed(String message);

        void requestStoragePermission();
    }

    interface IPresenter {
        void onShareAdClicked(AdvertisementModel advertisementModel);

        boolean isStoragePermissionGranted(Activity context);

    }
}
