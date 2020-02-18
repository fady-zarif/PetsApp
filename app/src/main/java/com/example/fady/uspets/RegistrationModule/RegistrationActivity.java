package com.example.fady.uspets.RegistrationModule;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fady.uspets.ControllerDI.ControllerComponent;
import com.example.fady.uspets.ControllerDI.ControllerModule;
import com.example.fady.uspets.ControllerDI.DaggerControllerComponent;
import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass;
import com.example.fady.uspets.MainScreenModule.MainScreenActivity;
import com.example.fady.uspets.Owner;
import com.example.fady.uspets.R;
import com.example.fady.uspets.USPetsMain.UsPetsMainView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends UsPetsMainView implements SignInDialog.SignInClick, IRegistration.IView {

    @BindView(R.id.etName)
    EditText etName;
    SignInDialog signInDialog;

    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.etPhone)
    EditText etPhone;

    @Inject
    RegistrationPresenter registrationPresenter;


    @Inject
    FirebaseUserClass firebaseUserClass;

    ControllerComponent controllerComponent;

    private static final String TAG = "RegistrationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbarSetup();

        controllerComponent = initDaggerController(this, null);
        controllerComponent.inject(this);
    }


    @OnClick(R.id.btnSignUp)
    void onSignUpClicked() {
        showProgressView();
        registrationPresenter.onSignUpClickListner(new Owner(etName.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString(), etPhone.getText().toString()));

    }

    @OnClick(R.id.tvSignIn)
    void onSignInClick() {
        signInDialog = new SignInDialog(this, this);
        signInDialog.getWindow().setGravity(Gravity.TOP);
        signInDialog.show();

    }

    private void toolbarSetup() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("\n" + getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
    }

    @Override
    public void onUserRegisterSuccess() {
        /**/
        IRegistration.IView runnable = new IRegistration.IView() {
            @Override
            public void onUserRegisterSuccess() {

            }

            @Override
            public void onUserRegisterFailed() {

            }

            @Override
            public void onUserLoginSuccess() {

            }

            @Override
            public void onUserLoginFailed(String errorMessage) {

            }

            @Override
            public void showUserEmailErrorMessage(String message) {

            }

            @Override
            public void showPasswordErrorMessage(String message) {

            }
        };
        dismisProgressView();
        Toast.makeText(this, getString(R.string.userUploadedSuccessfully), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserRegisterFailed() {
        dismisProgressView();
        Toast.makeText(this, getString(R.string.userUploadedFailed), Toast.LENGTH_SHORT).show();
    }

    // TODO: 2019-09-30  Dismiss the dialog and login the user to MainScreen
    @Override
    public void onUserLoginSuccess() {
        dismisProgressView();
//        Toast.makeText(this, "User Login Success", Toast.LENGTH_SHORT).show();
        startMainScreenActivity();
    }

    // TODO: 2019-09-30   show the error message
    @Override
    public void onUserLoginFailed(String errorMessage) {
        dismisProgressView();
        Toast.makeText(this, getString(R.string.userLoginFailed) + errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserEmailErrorMessage(String message) {
        signInDialog.showUserEmailErrorMessage(message);
    }

    @Override
    public void showPasswordErrorMessage(String message) {
        signInDialog.showUserPasswordErrorMessage(message);
    }

    @Override
    public void onSignClickListner(String email, String password) {
        registrationPresenter.onSignInClickListner(email, password);
    }


    private void startMainScreenActivity() {
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
    }
}
