package com.example.fady.uspets.RegistrationModule;

import android.content.Context;

import com.example.fady.uspets.Owner;

public interface IRegistration {
    interface IView {
        public void onUserRegisterSuccess();

        void onUserRegisterFailed();

        void onUserLoginSuccess();

        void onUserLoginFailed(String errorMessage);

        void showUserEmailErrorMessage(String message);

        void showPasswordErrorMessage(String message);


    }


    interface IPresenter {
        void onSignUpClickListner(Owner owner);

        void onSignInClickListner(String email, String password);


        RegistrationPresenter getInstance();
    }

}
