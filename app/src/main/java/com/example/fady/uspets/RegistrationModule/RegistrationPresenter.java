package com.example.fady.uspets.RegistrationModule;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.fady.uspets.FirebaseDatabase.FirebaseStorageClass;
import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass;
import com.example.fady.uspets.FirebaseDatabase.SharedPreference.SharedPreferencesClass;
import com.example.fady.uspets.Owner;

import com.example.fady.uspets.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;


public class RegistrationPresenter extends UserManager implements IRegistration.IPresenter {


    @Inject
    FirebaseUserClass firebaseUserClass;

    IRegistration.IView iView;

    @Inject
    Context context;
    @Inject
    Application application;
    @Inject
    SharedPreferencesClass sharedPreferencesClass;

    @Inject
    FirebaseStorageClass firebaseStorageClass;

    @Inject
    public RegistrationPresenter(Activity iView) {
        this.iView = (IRegistration.IView) iView;
    }

    @Override
    public void onSignUpClickListner(Owner owner) {
        if (iView == null) {
            Log.e("ggea", "null");
        } else
            Log.e("ggea", "not null");

        if (firebaseUserClass == null) {
            Log.e("firebaseUser", "null");
        } else
            Log.e("firebaseUser", "not null");

        if (application == null) {
            Log.e("Application is", " Null");
        } else {
            Log.e("Application is", " not Null");
        }
        if (context == null) {
            Log.e("context is", " Null");
        } else {
            Log.e("context is", " not Null");
        }
        // create new user with email and password
        firebaseUserClass.registerUser(owner, task -> {
            if (task.isSuccessful()) {
                uploadUser(owner);
            } else
                iView.onUserRegisterFailed();

        });

    }

    // save User as a document in the database
    private void uploadUser(Owner owner) {
        firebaseUserClass.uploadUserTask(owner, task -> {
            if (task.isSuccessful()) {
                iView.onUserRegisterSuccess();
                signInUser(owner.getoEmail(), owner.getoPassword());
            } else
                iView.onUserRegisterSuccess();


        });
    }

    @Override
    public void onSignInClickListner(String email, String password) {
        boolean isValid = true;
        if (email.isEmpty()) {
            iView.showPasswordErrorMessage(context.getString(R.string.email_error_message));
            isValid = false;
        }
        if (password.isEmpty()) {
            iView.showPasswordErrorMessage(context.getString(R.string.password_error_message));
            isValid = false;
        }
        if (!isValid)
            return;
        signInUser(email, password);

    }

    private void tty() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        MyPersonalRunnable myPersonalRunnable = x -> x * 3;
    }



    interface MyPersonalRunnable {

        int run2(int x);
    }

    private void signInUser(String email, String password) {
        firebaseUserClass.signInUser(email, password, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    sharedPreferencesClass.putStringInPref(SharedPreferencesClass.USERNAME_PREF, email);
                    sharedPreferencesClass.putStringInPref(SharedPreferencesClass.PASSWORD_PREF, password);
                    getUserInfo();
                    iView.onUserLoginSuccess();
                } else
                    iView.onUserLoginFailed(task.getException().getMessage());
            }
        });
    }


    @Override
    public RegistrationPresenter getInstance() {
        return this;
    }

}