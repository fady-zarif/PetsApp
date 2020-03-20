package com.example.fady.uspets.PetDetailsModule;

import com.example.fady.uspets.Owner;

public interface IpetDetails {
    interface Iview {
        void onshowOwnerInfo(Owner owner);
    }

    interface Ipresenter {
        void getPetOwnerInfo(String ownerId);

        void onOwnerClickListner();

    }
}
