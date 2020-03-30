package com.example.fady.uspets.ControllerDI;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.example.fady.uspets.Owner;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ControllerModule {

    Activity mActivity;
    Fragment fragment;


    public ControllerModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public ControllerModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    public Activity provideAcitivy() {
        return mActivity;
    }

    @Provides
    public Fragment provideFragment() {
        return fragment;
    }
}
