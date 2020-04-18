package com.example.fady.uspets.FirebaseDatabase;

import com.example.fady.uspets.messagesModule.MessageModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import javax.inject.Singleton;

import static com.example.fady.uspets.FirebaseDatabase.FirebaseConstant.CHAT_MESSAGES_REFEREMCE;

@Singleton
public class FirebaseChatClass extends FirebaseBase {


    CollectionReference getFirebaseChatRef() {
        CollectionReference collectionReference = getFirebaseFirestore().collection("ChatChannels");
        return collectionReference;
    }

    public void updateUserWithMessages(MessageModel messageModel) {

    }

    public Query getChatFromChannel(String channelId) {
        return getFirebaseChatRef().document(channelId).collection(CHAT_MESSAGES_REFEREMCE).orderBy("time");
    }

    public String getNewDocument() {
        DocumentReference document = getFirebaseChatRef().document();
        return document.getId();
    }

    public void uploadMessage(String channelId, OnCompleteListener<DocumentReference> documentSnapshotOnCompleteListener, MessageModel messageModel) {
        getFirebaseChatRef().document(channelId).collection(CHAT_MESSAGES_REFEREMCE).add(messageModel).addOnCompleteListener(documentSnapshotOnCompleteListener);
    }

    @Override
    public FirebaseFirestore getFirebaseFirestore() {
        return super.getFirebaseFirestore();
    }
}
