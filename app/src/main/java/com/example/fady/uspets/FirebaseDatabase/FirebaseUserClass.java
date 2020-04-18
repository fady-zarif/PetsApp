package com.example.fady.uspets.FirebaseDatabase;

import com.example.fady.uspets.Owner;
import com.example.fady.uspets.USPetsMain.UserChannel;
import com.example.fady.uspets.messagesModule.MessageModel;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.QuerySnapshot;

import javax.inject.Singleton;

import static com.example.fady.uspets.FirebaseDatabase.FirebaseConstant.FIRESTORE_USER_CHANNELS_REFEREMCE;
import static com.example.fady.uspets.FirebaseDatabase.FirebaseConstant.FIRESTORE_USER_REFERENCE;
import static com.example.fady.uspets.FirebaseDatabase.FirebaseConstant.USER_PHOTO_FIELD;

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

    public void updateUserPic( String url) {
        getUserRef().document(getCurrentUser().getUid()).update(USER_PHOTO_FIELD, url);
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


    public void getUserChannels(EventListener<QuerySnapshot> eventListener) {
        getUserRef().document(getCurrentUser().getUid()).collection(FIRESTORE_USER_CHANNELS_REFEREMCE).addSnapshotListener(eventListener);
    }

    public void getAnyUserInfo(String uid, OnSuccessListener<DocumentSnapshot> successListener) {
        getUserRef().document(uid).get().addOnSuccessListener(successListener);
    }

    public void updateUserWithMessages(MessageModel messageModel, OnCompleteListener<Task> taskOnCompleteListener) {
//        Task task1 = getUserRef().document(messageModel.getSenderId()).collection(FIRESTORE_USER_CHANNELS_REFEREMCE)
//                .add(new UserChannel(messageModel.getReceiverId(), messageModel.getMessageId()));
        Task task1 = getUserRef().document(messageModel.getSenderId()).collection(FIRESTORE_USER_CHANNELS_REFEREMCE)
                .document(messageModel.getReceiverId()).set(new UserChannel(messageModel.getReceiverId(), messageModel.getMessageId()));

        Task task2 = getUserRef().document(messageModel.getReceiverId()).collection(FIRESTORE_USER_CHANNELS_REFEREMCE)
                .document(messageModel.getSenderId()).set(new UserChannel(messageModel.getSenderId(), messageModel.getMessageId()));
//                .add(new UserChannel(messageModel.getSenderId(), messageModel.getMessageId()));

        Task listTask = Tasks.whenAllComplete(task1, task2);
        listTask.addOnCompleteListener(taskOnCompleteListener);


    }


    FirebaseAuth getFirebaseAuthInstance() {
        if (firebaseAuth == null)
            firebaseAuth = FirebaseAuth.getInstance();
        return firebaseAuth;
    }

    FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }


    CollectionReference getUserRef() {
        if (userRef == null)
            userRef = getFirebaseFirestore().collection(FIRESTORE_USER_REFERENCE);
        return userRef;
    }
}
