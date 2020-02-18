package com.example.fady.uspets.SplashScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass;
import com.example.fady.uspets.FirebaseDatabase.SharedPreference.SharedPreferencesClass;

import com.example.fady.uspets.RegistrationModule.UserManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import static java.lang.Thread.sleep;

public class SplashScreenPresenter extends UserManager implements ISplashScreen.ISplashPresenter {

    ISplashScreen.ISpleashView iSpleashView;
    @Inject
    FirebaseUserClass firebaseUser;
    @Inject
    SharedPreferencesClass sharedPreferencesClass;

    @Inject
    Context context;

    @Inject
    public SplashScreenPresenter(Activity activity) {
        iSpleashView = (ISplashScreen.ISpleashView) activity;
    }

    @Override
    public void checkUserExist() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                    if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                        iSpleashView.startRegistrationActivity();
                    } else {
//                        iSpleashView.startRegistrationActivity();
                        sigbInUser();
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void sigbInUser() {
//        SharedPreferences sharedPreferences = context.getSharedPreferences("PET_SHARED_PREF", Context.MODE_PRIVATE);
//        String name = sharedPreferences.getString(SharedPreferencesClass.USERNAME_PREF, "gee");
//        Log.e("aas", name);
        Log.e("SPLASH_SIGN_IN", "" + sharedPreferencesClass.getStringFromPref(SharedPreferencesClass.USERNAME_PREF) + "" + sharedPreferencesClass.getStringFromPref(SharedPreferencesClass.PASSWORD_PREF));
        firebaseUser.signInUser(sharedPreferencesClass.getStringFromPref(SharedPreferencesClass.USERNAME_PREF), sharedPreferencesClass.getStringFromPref(SharedPreferencesClass.PASSWORD_PREF), new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    getUserInfo();
                    iSpleashView.startMainScreenActivity();
                } else {
                    iSpleashView.startRegistrationActivity();
                }
            }
        });
    }

}
