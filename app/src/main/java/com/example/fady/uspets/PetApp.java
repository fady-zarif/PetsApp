package com.example.fady.uspets;


//import com.example.fady.uspets.FirebaseDatabase.DaggerApplicationComponent;

import com.example.fady.uspets.ApplicationDI.ApplicationComponent;
import com.example.fady.uspets.ApplicationDI.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class PetApp extends DaggerApplication {
    public ApplicationComponent daggerApplicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        getDaggerApplicationComponent();

    }


    public ApplicationComponent getDaggerApplicationComponent() {
        if (daggerApplicationComponent == null)
            daggerApplicationComponent = DaggerApplicationComponent.builder().application(this).provideContext(getApplicationContext()).build();
        return daggerApplicationComponent;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return getDaggerApplicationComponent();
    }
}

