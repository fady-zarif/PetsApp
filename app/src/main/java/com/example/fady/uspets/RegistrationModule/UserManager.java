package com.example.fady.uspets.RegistrationModule;

import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass;
import com.example.fady.uspets.Owner;
import com.example.fady.uspets.USPetsMain.IUSPetMain;
import com.example.fady.uspets.USPetsMain.PetUiManager;
import com.google.firebase.firestore.DocumentSnapshot;

import javax.inject.Inject;

public abstract class UserManager {
    @Inject
    FirebaseUserClass firebaseUserClass;

    protected void getUserInfo(IUSPetMain.IUserDataListner iUserDataListner) {
        firebaseUserClass.getCurrentUserInfo(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                Owner owner = documentSnapshot.toObject(Owner.class);
                PetUiManager.getInstance().setCurrentUser(owner);
                iUserDataListner.onUserDataSuccess(owner);
            } else {
                iUserDataListner.onUserDataFailed(task.getException().getLocalizedMessage());
            }
        });
    }


}


/*
    protected void getUserInfo() {
        firebaseUserClass.getCurrentUserInfo(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    Owner owner = documentSnapshot.toObject(Owner.class);
//                    Owner owner = task.getResult().toObject(Owner.class);
                    PetUiManager.getInstance().setCurrentUser(owner);
                }
            }
        });
    }*/