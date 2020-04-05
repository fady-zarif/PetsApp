package com.example.fady.uspets.MainScreenModule.AdvertismentModule;

public interface IAdvertisement {
    interface IAdvertismentView {

        void onRetriveAdvertismentsSuccess(AdvertisementModel advertisementModel);
//        void onRetriveAdvertismentsOnceSuccess(AdvertisementModel advertisementModel);

        void onRetriveAdvertismentsFailed(String errorMessage);

    }

    interface IAdvertismentPresenter {
        void getAdvertisment();
        void onRefreshGetAdvertisement();

        void listenToUserChannels();
    }

    interface IAdvertismentClick {
        void onAdvertismentClicListner(int pos);
    }
}
