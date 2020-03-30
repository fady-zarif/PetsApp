package com.example.fady.uspets.FirebaseDatabase;

import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

import javax.inject.Singleton;

import static com.example.fady.uspets.FirebaseDatabase.FirebaseConstant.FIRESTORE_ADVERTISEMENT_REFERENCE;
import static com.example.fady.uspets.FirebaseDatabase.FirebaseConstant.ORDER_BY_DATE;
import static com.example.fady.uspets.FirebaseDatabase.FirebaseConstant.OWNER_KEY;


@Singleton
public class FirebaseAdvertismentClass extends FirebaseBase {
    CollectionReference advertismentRef;

    public void getAdvertismentList(EventListener<QuerySnapshot> eventListener) {
        getAdvertismentRef().orderBy(ORDER_BY_DATE, Query.Direction.ASCENDING).addSnapshotListener(eventListener);
    }

    public void getAdvertismentListOnce(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        getAdvertismentRef().orderBy(ORDER_BY_DATE, Query.Direction.ASCENDING).get().addOnCompleteListener(onCompleteListener);
    }

    public void getmyAdvertisment(String userUid, OnCompleteListener<QuerySnapshot> onCompleteListener) {
        getAdvertismentRef().whereEqualTo(OWNER_KEY, userUid).orderBy(ORDER_BY_DATE, Query.Direction.ASCENDING)
                .get().addOnCompleteListener(onCompleteListener);
    }

    public CollectionReference getAdvertismentRef() {
        if (advertismentRef == null)
            advertismentRef = getFirebaseFirestore().collection(FIRESTORE_ADVERTISEMENT_REFERENCE);
        return advertismentRef;
    }

    public void updateAdvertisment(String docId, HashMap<String, Object> hashMap, OnCompleteListener OnCompleteListener) {
        getAdvertismentRef().document(docId).update(hashMap).addOnCompleteListener(OnCompleteListener);
    }

    public void updatePetImage(String imgUrl, String documentUid, OnCompleteListener<Void> onCompleteListener) {
        getAdvertismentRef().document(documentUid).update("petImage", imgUrl).addOnCompleteListener(onCompleteListener);
    }

    public void pushAdvertisement(String id, AdvertisementModel advertisementModel, OnCompleteListener onCompleteListener) {
        getAdvertismentRef().document(id).set(advertisementModel).addOnCompleteListener(onCompleteListener);
    }
}
