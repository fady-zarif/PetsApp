package com.example.fady.uspets.MainScreenModule.AdvertismentModule;

public interface IAdvertisement {
    interface IAdvertismentView {

        void onRetrieveAdvertisementsSuccess(AdvertisementModel advertisementModel);
//        void onRetriveAdvertismentsOnceSuccess(AdvertisementModel advertisementModel);

        void onRetrieveAdvertisementsFailed(String errorMessage);

    }

    interface IAdvertismentPresenter {
        void getAdvertisement();
        void onRefreshGetAdvertisement();

        void listenToUserChannels();
    }

    interface IAdvertismentClick {
        void onAdvertisementClickListener(int pos);
    }
}
