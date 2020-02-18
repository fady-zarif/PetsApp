package com.example.fady.uspets.FirebaseDatabase;

import com.example.fady.uspets.Owner;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Singleton;

import static com.example.fady.uspets.FirebaseDatabase.FirebaseConstant.FIRESTORE_USER_REFERENCE;

@Singleton
public class FirebaseUserClass extends FirebaseBase {
    CollectionReference userRef;
    FirebaseAuth firebaseAuth;

    public void uploadUserTask(Owner owner, OnCompleteListener onCompleteListener) {

        owner.setoUid(getCurrentUser().getUid());
        getUserRef().document(getCurrentUser().getUid()).set(owner).addOnCompleteListener(onCompleteListener);
    }

    public void registerUser(Owner owner, OnCompleteListener onCompleteListener) {
        getFirebaseAuthInstance().createUserWithEmailAndPassword(owner.getoEmail(), owner.getoPassword()).addOnCompleteListener(onCompleteListener);
    }

    public void signInUser(String userEmail, String userPassword, OnCompleteListener onCompleteListener) {
        getFirebaseAuthInstance().signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(onCompleteListener);
    }

    public void logOutUser() {
        getFirebaseAuthInstance().signOut();
    }

    public void getCurrentUserInfo(OnCompleteListener<DocumentSnapshot> onCompleteListener) {
//        getAnyUserInfo(, onCompleteListener);
        getUserRef().document(getCurrentUser().getUid()).get().addOnCompleteListener(onCompleteListener);
    }

    public void getAnyUserInfo(String uid, OnCompleteListener<DocumentSnapshot> onCompleteListener) {
        getUserRef().document(uid).get().addOnCompleteListener(onCompleteListener);
    }


    FirebaseAuth getFirebaseAuthInstance() {
        if (firebaseAuth == null)
            firebaseAuth = FirebaseAuth.getInstance();
        return firebaseAuth;
    }

    FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }


    CollectionReference getUserRef() {
        if (userRef == null)
            userRef = getFirebaseFirestore().collection(FIRESTORE_USER_REFERENCE);
        return userRef;
    }
}
