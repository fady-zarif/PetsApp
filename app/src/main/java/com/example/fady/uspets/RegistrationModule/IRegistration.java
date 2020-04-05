package com.example.fady.uspets.RegistrationModule;

import com.example.fady.uspets.Owner;

public interface IRegistration {
    interface IView {
        public void onUserRegisterSuccess();

        void onUserRegisterFailed(String errorMessage);

        void onUserLoginSuccess();

        void onUserLoginFailed(String errorMessage);

        void showUserEmailErrorMessage(String message);

        void showPasswordErrorMessage(String message);
        // SignUp fields

        void dismissProgressDialog();

        void showSignUpNameErrorMessage(String message);
        void showSignUpEmailErrorMessage(String message);
        void showSignUpPasswordErrorMessage(String message);
        void showSignUpPhoneErrorMessage(String message);


    }


    interface IPresenter {
        void onSignUpClickListner(Owner owner);

        void onSignInClickListner(String email, String password);


        RegistrationPresenter getInstance();
    }

}
