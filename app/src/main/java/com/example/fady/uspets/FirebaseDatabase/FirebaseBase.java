package com.example.fady.uspets.FirebaseDatabase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import static com.example.fady.uspets.FirebaseDatabase.FirebaseConstant.FIRESTORE_ADVERTISEMENT_REFERENCE;
import static com.example.fady.uspets.FirebaseDatabase.FirebaseConstant.FIRESTORE_USER_REFERENCE;

public abstract class FirebaseBase {
    FirebaseFirestore firebaseFirestore;

    public FirebaseFirestore getFirebaseFirestore() {
        if (firebaseFirestore == null)
            firebaseFirestore = FirebaseFirestore.getInstance();
        return firebaseFirestore;
    }
}
