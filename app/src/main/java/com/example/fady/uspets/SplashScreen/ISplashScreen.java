package com.example.fady.uspets.SplashScreen;

public interface ISplashScreen {
    interface ISpleashView {
        void startRegistrationActivity();

        void startMainScreenActivity();
    }

    interface ISplashPresenter {
        void checkUserExist();
    }
}
