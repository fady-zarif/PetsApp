package com.example.fady.uspets.USPetsMain;

import android.widget.ImageView;

import com.example.fady.uspets.Owner;
import com.example.fady.uspets.R;
import com.squareup.picasso.Picasso;

public class PetUiManager {
    private static PetUiManager petUiManager;
    private Owner currentUser;

    public static PetUiManager getInstance() {
        if (petUiManager == null)
            petUiManager = new PetUiManager();
        return petUiManager;
    }

    public void setPicassoImage(String url, ImageView imageView) {
        Picasso.get().load(url).placeholder(R.drawable.loading_image).into(imageView);
    }

    public void setCurrentUser(Owner currentUser) {
        this.currentUser = currentUser;
    }

    public Owner getCurrentUser() {
        return currentUser;
    }

}
