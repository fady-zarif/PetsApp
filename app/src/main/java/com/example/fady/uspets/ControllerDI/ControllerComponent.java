package com.example.fady.uspets.ControllerDI;

import com.example.fady.uspets.ApplicationDI.ApplicationComponent;
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementFragment;
import com.example.fady.uspets.MainScreenModule.CreateAdModule.CreateAdFragment;
import com.example.fady.uspets.PersonalAdModule.PersonalAdActivity;
import com.example.fady.uspets.PetDetailsModule.PetDetailsActiviy;
import com.example.fady.uspets.RegistrationModule.RegistrationActivity;
import com.example.fady.uspets.SplashScreen.SplashScreenActivity;
import com.example.fady.uspets.chatModule.ChatActivity;
import com.example.fady.uspets.messagesModule.MessagesActivity;
import com.example.fady.uspets.myAdvertisement.MyAdvertisementActivity;
import com.example.fady.uspets.profileModule.ProfileActivity;

import org.jetbrains.annotations.NotNull;

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

    void inject(ChatActivity chatActivity);

    void inject(MessagesActivity messagesActivity);

    void inject(PersonalAdActivity personalAdActivity);

    void inject(@NotNull MyAdvertisementActivity myAdvertisementActivity);
}
