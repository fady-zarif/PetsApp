package com.example.fady.uspets.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.fady.uspets.ControllerDI.ControllerComponent;
import com.example.fady.uspets.ControllerDI.ControllerModule;
import com.example.fady.uspets.ControllerDI.DaggerControllerComponent;
import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass;
import com.example.fady.uspets.MainScreenModule.MainScreenActivity;
import com.example.fady.uspets.R;
import com.example.fady.uspets.RegistrationModule.RegistrationActivity;
import com.example.fady.uspets.USPetsMain.UsPetsMainView;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import static java.lang.Thread.sleep;

public class SplashScreenActivity extends UsPetsMainView implements ISplashScreen.ISpleashView {
    @Inject
    FirebaseUserClass firebaseUserClass;
    ControllerComponent controllerComponent;

    @Inject
    SplashScreenPresenter splashScreenPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        decorview();
        controllerComponent = DaggerControllerComponent.builder().applicationComponent(getApplicationComponent()).controllerModule(new ControllerModule(this)).build();
        controllerComponent.inject(this);
        splashScreenPresenter.checkUserExist();

    }

    private void decorview() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Override
    public void startRegistrationActivity() {
        startActivity(new Intent(SplashScreenActivity.this, RegistrationActivity.class));
    }

    @Override
    public void startMainScreenActivity() {
        startActivity(new Intent(SplashScreenActivity.this, MainScreenActivity.class));
    }
}
