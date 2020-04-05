package com.example.fady.uspets.PetDetailsModule;

import com.example.fady.uspets.Owner;

public interface IpetDetails {
    interface Iview {
        void onshowOwnerInfo(Owner owner);

        void onshowError(String errorMessage);
    }

    interface Ipresenter {
        void getPetOwnerInfo(String ownerId);

        void onOwnerClickListner();

        void onSendMessageClick(String message, String petOwnerUid);

    }
}
