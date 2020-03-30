package com.example.fady.uspets.PersonalAdModule;

import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementModel;

import java.util.ArrayList;

public interface IPersonalAd {

    interface Iview {
        void onImageError(String error, ArrayList<String> originalImageList);

        void onDescError(String descError, String originalDesc);

        void onPriceError(String error, String originalPrice);

        void onUpdateSuccess();

        void onUpdateFailed(String message);

    }

    interface Ipresenter {
        void onUpdateClick(AdvertisementModel advertisementModel);

        void setAdvetismentModel(AdvertisementModel advetismentModel);
    }


}
