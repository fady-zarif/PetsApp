package com.example.fady.uspets.MainScreenModule.AdvertismentModule;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import com.example.fady.uspets.FirebaseDatabase.DaggerApplicationComponent;
import com.example.fady.uspets.FirebaseDatabase.FirebaseAdvertismentClass;
import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass;
import com.example.fady.uspets.Owner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.inject.Inject;

public class AdvertisementPresenter implements IAdvertisement.IAdvertismentPresenter {
    IAdvertisement.IAdvertismentView iAdvertismentView;
    @Inject
    FirebaseAdvertismentClass firebaseAdvertismentClass;
    @Inject
    FirebaseUserClass firebaseUserClass;
    ArrayList<AdvertisementModel> advertisementModelArrayList;

    @Inject
    public AdvertisementPresenter(Fragment advertismentFragment) {
        this.iAdvertismentView = (IAdvertisement.IAdvertismentView) advertismentFragment;

    }

    @Override
    public void getAdvertisment() {
        advertisementModelArrayList = new ArrayList<>();
        firebaseAdvertismentClass.getAdvertismentList(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    iAdvertismentView.onRetriveAdvertismentsFailed(e.getLocalizedMessage()
                    );
                    return;
                }
                if (queryDocumentSnapshots.getDocumentChanges().size() == 0) {// TODO: 2020-01-07 move to string file
                    iAdvertismentView.onRetriveAdvertismentsFailed("No Data");
                    return;
                }
                for (DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()) {
                    AdvertisementModel advertisementModel = documentChange.getDocument().toObject(AdvertisementModel.class);
                    getUserInfo(advertisementModel);
                }

            }
        });
    }

    private void getUserInfo(AdvertisementModel advertisementModel) {
        firebaseUserClass.getAnyUserInfo(advertisementModel.getOwnerUid(), task -> {
            if (task.isSuccessful()) {
                Owner owner = task.getResult().toObject(Owner.class);
                advertisementModel.setOwner(owner);
                iAdvertismentView.onRetriveAdvertismentsSuccess(advertisementModel);

            }
        });
    }

}
