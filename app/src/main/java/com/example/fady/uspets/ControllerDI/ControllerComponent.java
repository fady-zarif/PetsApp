package com.example.fady.uspets.ControllerDI;

import com.example.fady.uspets.ApplicationDI.ApplicationComponent;
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementFragment;
import com.example.fady.uspets.MainScreenModule.CreateAdModule.CreateAdFragment;
import com.example.fady.uspets.PetDetailsModule.PetDetailsActiviy;
import com.example.fady.uspets.RegistrationModule.RegistrationActivity;
import com.example.fady.uspets.SplashScreen.SplashScreenActivity;
import com.example.fady.uspets.profilemodule.ProfileActivity;

import dagger.Component;

@ControllerScope
@Component(dependencies = ApplicationComponent.class, modules = ControllerModule.class)
public interface ControllerComponent {

    void inject(RegistrationActivity registrationActivity);

    void inject(CreateAdFragment createAdFragment);

    void inject(SplashScreenActivity splashScreenActivity);

    void inject(AdvertisementFragment advertisementFragment);

    void inject(ProfileActivity profileActivity);

    void inject(PetDetailsActiviy petDetailsActiviy);

}
