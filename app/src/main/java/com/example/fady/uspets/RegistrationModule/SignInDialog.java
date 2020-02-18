package com.example.fady.uspets.RegistrationModule;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.fady.uspets.R;
import com.example.fady.uspets.USPetsMain.USPetMainAlertDialog;

import javax.annotation.Nonnull;

public class SignInDialog extends USPetMainAlertDialog {
    EditText etEmail, etPassword;
    Button btnSignIn;
    SignInClick signInClick;

    public SignInDialog(@Nonnull Context context, SignInClick signInClick) {
        super(context, R.style.CustomDialog);
        this.signInClick = signInClick;
    }

    @Override
    protected void onCreateDialog() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInClick.onSignClickListner(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etEmail.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    public void showUserEmailErrorMessage(String errorMessage) {
        etEmail.setError(errorMessage);
    }

    public void showUserPasswordErrorMessage(String errorMessage) {
        etPassword.setError(errorMessage);
    }

    interface SignInClick {
        void onSignClickListner(String email, String password);
    }


    @Override
    protected int getDialogContent() {
        return R.layout.sign_in_dialog;
    }

    public void dismissDialog() {
        this.dismiss();
    }

    void showDialog() {
        this.show();
    }
}
