package com.example.fady.uspets.PetDetailsModule;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.example.fady.uspets.FirebaseDatabase.FirebaseChatClass;
import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass;
import com.example.fady.uspets.Owner;
import com.example.fady.uspets.USPetsMain.PetUiManager;
import com.example.fady.uspets.messagesModule.MessageModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import java.util.Calendar;

import javax.inject.Inject;

import static com.example.fady.uspets.FirebaseDatabase.FirebaseConstant.MESSAGE_TYPE_TEXT;

public class PetDetailsPresenter implements IpetDetails.Ipresenter {
    IpetDetails.Iview iview;
    @Inject
    FirebaseUserClass firebaseUserClass;
    @Inject
    FirebaseChatClass firebaseChatClass;


    @Inject
    PetDetailsPresenter(Activity activity) {
        iview = (IpetDetails.Iview) activity;
    }

    @Override
    public void getPetOwnerInfo(String ownerId) {
        firebaseUserClass.getAnyUserInfo(ownerId, dataSnapShot -> {
            Owner owner = dataSnapShot.toObject(Owner.class);
            iview.onshowOwnerInfo(owner);
        });
    }

    @Override
    public void onOwnerClickListner() {

    }

    @Override
    public void onSendMessageClick(String message, String petOwnerUid) {
        // TODO: 4/4/20 will check if they talked before if yes will send message directly to messageRefe ....
        //  if no will put the channel id in both users

        MessageModel messageModel = new MessageModel(message, PetUiManager.getInstance().getCurrentUser().getoUid(),
                petOwnerUid, firebaseChatClass.getNewDocument(),
                Calendar.getInstance().getTime(), MESSAGE_TYPE_TEXT);
        // check if petOwnerId is already in my channelList
        if (!PetUiManager.getInstance().getUserChannels().containsKey(petOwnerUid)) {
            firebaseUserClass.updateUserWithMessages(messageModel, new OnCompleteListener<Task>() {
                @Override
                public void onComplete(@NonNull Task<Task> task) {
                    if (task.isSuccessful()) {
                        uploadMessage(messageModel.getMessageId(),messageModel);
                    } else {
                        iview.onshowError("uploading to useres failed");
                    }
                    // TODO: 4/4/20  update view with error message
                }
            });
        } else {

            uploadMessage(PetUiManager.getInstance().getUserChannels().get(petOwnerUid),messageModel);
        }

    }

    private void uploadMessage(String channelId, MessageModel messageModel) {
        firebaseChatClass.uploadMessage(channelId, new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                // update view message sent
                if (task.isSuccessful())
                    iview.onshowError("uploading message success2");
                else
                    iview.onshowError("uploading message failed2");
            }
        }, messageModel);
    }
}
