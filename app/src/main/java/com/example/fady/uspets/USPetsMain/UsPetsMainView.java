package com.example.fady.uspets.USPetsMain;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.fady.uspets.ApplicationDI.ApplicationComponent;
import com.example.fady.uspets.ControllerDI.ControllerComponent;
import com.example.fady.uspets.ControllerDI.ControllerModule;
import com.example.fady.uspets.ControllerDI.DaggerControllerComponent;
import com.example.fady.uspets.PetApp;
import com.example.fady.uspets.R;


public class UsPetsMainView extends AppCompatActivity {

    ProgressDialog progressDialog;


    public void showProgressView() {
        progressDialog = ProgressDialog.show(this, null, getString(R.string.progress_dialog), true, false);
    }

    public void dismisProgressView() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    public ApplicationComponent getApplicationComponent() {
        return ((PetApp) getApplicationContext()).getDaggerApplicationComponent();
    }

    public ControllerComponent  initDaggerController(Activity activity, Fragment fragment) {
        ControllerComponent daggerControllerComponent;
        if (activity == null)
            daggerControllerComponent = DaggerControllerComponent.builder().applicationComponent(getApplicationComponent()).controllerModule(new ControllerModule(fragment)).build();
        else
            daggerControllerComponent = DaggerControllerComponent.builder().applicationComponent(getApplicationComponent()).controllerModule(new ControllerModule(activity)).build();
        return daggerControllerComponent;

    }
}
