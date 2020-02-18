package com.example.fady.uspets.MainScreenModule.CreateAdModule;

import dagger.Module;

@Module
public class CreateAdModule {
    ICreateAd.IView iView;

    public CreateAdModule(ICreateAd.IView iView) {
        this.iView = iView;
    }


}
