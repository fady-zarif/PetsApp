package com.example.fady.uspets.PetDetailsModule;

import android.app.Activity;

import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass;
import com.example.fady.uspets.Owner;

import javax.inject.Inject;

public class PetDetailsPresenter implements IpetDetails.Ipresenter {
    IpetDetails.Iview iview;
    @Inject
    FirebaseUserClass firebaseUserClass;


    @Inject
    public PetDetailsPresenter(Activity activity) {
        iview = (IpetDetails.Iview) activity;
    }

    @Override
    public void getPetOwnerInfo(String ownerId) {
        firebaseUserClass.getAnyUserInfo(ownerId, dataSnapShot -> {
            Owner owner = dataSnapShot.toObject(Owner.class);
            iview.onshowOwnerInfo(owner);
        });
    }

    @Override
    public void onOwnerClickListner() {

    }
}
