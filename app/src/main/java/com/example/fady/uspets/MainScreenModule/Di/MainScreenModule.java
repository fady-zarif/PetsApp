package com.example.fady.uspets.MainScreenModule.Di;

import com.example.fady.uspets.MainScreenModule.MainScreenActivity;
import com.example.fady.uspets.MainScreenModule.CreateAdModule.*;
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.*;

import dagger.Module;

@Module
public class MainScreenModule {
    ICreateAd.IView iview;
    IAdvertisement.IAdvertismentView iAdvertismentView;

    public MainScreenModule(ICreateAd.IView iView) {
        this.iview = iView;
    }

    public MainScreenModule(IAdvertisement.IAdvertismentView iView) {

    }

}
