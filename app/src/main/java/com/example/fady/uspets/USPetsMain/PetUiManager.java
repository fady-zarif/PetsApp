package com.example.fady.uspets.USPetsMain;

import android.widget.ImageView;

import com.example.fady.uspets.Owner;
import com.example.fady.uspets.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

public class PetUiManager {
    private static PetUiManager petUiManager;
    private Owner currentUser;
    private HashMap<String, String> userChannels;

    public PetUiManager() {
        userChannels = new HashMap<>();
    }

    public static PetUiManager getInstance() {
        if (petUiManager == null) {
            petUiManager = new PetUiManager();
        }
        return petUiManager;
    }

    public String getCurrencySymbol() {
        return Currency.getInstance(Locale.US).getSymbol();
    }

    public void setPicassoImage(String url, ImageView imageView) {
        Picasso.get().load(url).placeholder(R.drawable.loading_image).into(imageView);
    }

    public void setCurrentUser(Owner currentUser) {
        this.currentUser = currentUser;
    }

    public void addUserChannel(UserChannel userChannel) {
        userChannels.put(userChannel.getUid(), userChannel.getChannelId());
    }

    public HashMap<String, String> getUserChannels() {
        return userChannels;
    }

    public Owner getCurrentUser() {
        return currentUser;
    }

}
