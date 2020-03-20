package com.example.fady.uspets.FirebaseDatabase;

import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.example.fady.uspets.FirebaseDatabase.FirebaseConstant.FIRESTORE_ADVERTISEMENT_REFERENCE;


@Singleton
public class FirebaseAdvertismentClass extends FirebaseBase {
    CollectionReference advertismentRef;

    public void getAdvertismentList(EventListener<QuerySnapshot> eventListener) {
        getAdvertismentRef().orderBy("date", Query.Direction.ASCENDING).addSnapshotListener(eventListener);
    }

    public void getAdvertismentListOnce(OnCompleteListener<QuerySnapshot> onCompleteListener)
    {
        getAdvertismentRef().orderBy("date",Query.Direction.ASCENDING).get().addOnCompleteListener(onCompleteListener);
    }

    public CollectionReference getAdvertismentRef() {
        if (advertismentRef == null)
            advertismentRef = getFirebaseFirestore().collection(FIRESTORE_ADVERTISEMENT_REFERENCE);
        return advertismentRef;
    }

    public void updatePetImage(String imgUrl, String documentUid, OnCompleteListener onCompleteListener) {
        getAdvertismentRef().document(documentUid).update("petImage", imgUrl).addOnCompleteListener(onCompleteListener);
    }

    public void pushAdvertisement(String id, AdvertisementModel advertisementModel, OnCompleteListener onCompleteListener) {
        getAdvertismentRef().document(id).set(advertisementModel).addOnCompleteListener(onCompleteListener);
    }
}
