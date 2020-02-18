package com.example.fady.uspets.FirebaseDatabase.SharedPreference;

import android.content.Context;

import javax.inject.Inject;

import dagger.Module;

@Module
public class SharedPreferenceModule {
    Context context;

    @Inject
    public SharedPreferenceModule(Context context) {
        this.context = context;
    }
}
