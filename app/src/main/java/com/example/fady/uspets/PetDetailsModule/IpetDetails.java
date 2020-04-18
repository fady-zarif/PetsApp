package com.example.fady.uspets.PetDetailsModule;

import com.example.fady.uspets.Owner;

public interface IpetDetails {
    interface Iview {
        void onshowOwnerInfo(Owner owner);

        void showOwnerPhoto(String url);

        void userHasNoNumber();

        void onshowError(String errorMessage);
    }

    interface Ipresenter {
        void getPetOwnerInfo(String ownerId);

        String getOwnerPhoneNumber();

        void onOwnerClickListner();

        void onSendMessageClick(String message, String petOwnerUid);

    }
}
