package com.example.fady.uspets.ApplicationDI;

import android.app.Application;
import android.content.Context;

import com.example.fady.uspets.FirebaseDatabase.FirebaseAdvertismentClass;
import com.example.fady.uspets.FirebaseDatabase.FirebaseStorageClass;
import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass;
import com.example.fady.uspets.FirebaseDatabase.SharedPreference.SharedPreferencesClass;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ApplicationModule {


    @Singleton
    @Provides
    static FirebaseUserClass provideFirebaseUserClass() {
        return new FirebaseUserClass();
    }

    @Singleton
    @Provides
    static FirebaseAdvertismentClass provideFirebaseAdvertisementClass() {
        return new FirebaseAdvertismentClass();
    }

    //
    @Singleton
    @Provides
    static FirebaseStorageClass provideFirebaseStorageClass() {
        return new FirebaseStorageClass();
    }



//    @Provides
//    static Context provideApplication(Application application) {
//        return application.getApplicationContext();
//    }
//

}
