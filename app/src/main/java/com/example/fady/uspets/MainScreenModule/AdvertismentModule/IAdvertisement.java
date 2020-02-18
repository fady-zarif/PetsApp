package com.example.fady.uspets.MainScreenModule.AdvertismentModule;

public interface IAdvertisement {
    interface IAdvertismentView {

        void onRetriveAdvertismentsSuccess(AdvertisementModel advertisementModel);

        void onRetriveAdvertismentsFailed(String errorMessage);

    }

    interface IAdvertismentPresenter {
        void getAdvertisment();
    }

    interface IAdvertismentClick {
        void onAdvertismentClicListner(int pos);
    }
}
