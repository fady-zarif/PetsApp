package com.example.fady.uspets.MainScreenModule.AdvertismentModule;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import com.example.fady.uspets.FirebaseDatabase.DaggerApplicationComponent;
import com.example.fady.uspets.FirebaseDatabase.FirebaseAdvertismentClass;
import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass;
import com.example.fady.uspets.USPetsMain.PetUiManager;
import com.example.fady.uspets.USPetsMain.UserChannel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

    private void setUserListener() {
        firebaseUserClass.getUserChannels(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e == null && queryDocumentSnapshots.size() > 0) {
                    for (DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()) {
                        if (documentChange.getType() == DocumentChange.Type.ADDED)
                            PetUiManager.getInstance().addUserChannel(documentChange.getDocument().toObject(UserChannel.class));
                    }
                }
            }
        });
    }

    @Override
    public void getAdvertisement() {
        advertisementModelArrayList = new ArrayList<>();
        firebaseAdvertismentClass.getAdvertismentList(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    iAdvertismentView.onRetrieveAdvertisementsFailed(e.getLocalizedMessage()
                    );
                    return;
                }
                if (queryDocumentSnapshots.getDocumentChanges().size() == 0) {// TODO: 2020-01-07 move to string file
                    iAdvertismentView.onRetrieveAdvertisementsFailed("No Data");
                    return;
                }
//                if (!queryDocumentSnapshots.getMetadata().isFromCache()) {
                for (DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()) {
                    Log.e("HOWMANYDOCS", " aa" + queryDocumentSnapshots.getDocumentChanges().size());
                    if (documentChange.getType().equals(DocumentChange.Type.ADDED)) {
                        AdvertisementModel advertisementModel = documentChange.getDocument().toObject(AdvertisementModel.class);
//                        getUserInfo(advertisementModel);
                        iAdvertismentView.onRetrieveAdvertisementsSuccess(advertisementModel);
                    }
                }


            }
        });
    }

    @Override
    public void onRefreshGetAdvertisement() {
        getAdvertismentOnce();
    }

    @Override
    public void listenToUserChannels() {
        setUserListener();
    }


    private void getAdvertismentOnce() {
        advertisementModelArrayList = new ArrayList<>();
        firebaseAdvertismentClass.getAdvertismentListOnce(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        AdvertisementModel advertisementModel = documentSnapshot.toObject(AdvertisementModel.class);
                        iAdvertismentView.onRetrieveAdvertisementsSuccess(advertisementModel);
                    }
                }
            }
        });

    }
}
