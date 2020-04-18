package com.example.fady.uspets.ApplicationDI;

import android.app.Application;
import android.content.Context;

import com.example.fady.uspets.FirebaseDatabase.FirebaseAdvertismentClass;
import com.example.fady.uspets.FirebaseDatabase.FirebaseChatClass;
import com.example.fady.uspets.FirebaseDatabase.FirebaseStorageClass;
import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass;
import com.example.fady.uspets.FirebaseDatabase.SharedPreference.SharedPreferenceModule;
import com.example.fady.uspets.FirebaseDatabase.SharedPreference.SharedPreferencesClass;
import com.example.fady.uspets.MainScreenModule.MainScreenActivity;
import com.example.fady.uspets.PetApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ApplicationModule.class, SharedPreferenceModule.class, AndroidSupportInjectionModule.class})
public interface ApplicationComponent extends AndroidInjector<PetApp> {

    FirebaseAdvertismentClass getFirebaseAdvertisementClass();

    Application getApplication();

    Context getContext();

    FirebaseStorageClass getFirebaseStorageClass();

    SharedPreferencesClass getSharedPreference();

    FirebaseUserClass getFirebaseUserClass();

    FirebaseChatClass getFirebaseChatClass();

    void injectMainScreenActivty(MainScreenActivity mainScreenActivity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder provideContext(Context context);

        ApplicationComponent build();
    }


}
