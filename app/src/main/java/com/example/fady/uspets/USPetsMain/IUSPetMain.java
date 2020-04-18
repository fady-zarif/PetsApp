package com.example.fady.uspets.USPetsMain;

import com.example.fady.uspets.Owner;

public interface IUSPetMain {
    interface IUserDataListner {
        void onUserDataSuccess(Owner owner);

        void onUserDataFailed(String message);
    }

}
