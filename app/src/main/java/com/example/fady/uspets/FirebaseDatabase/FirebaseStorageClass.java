package com.example.fady.uspets.FirebaseDatabase;

import android.net.Uri;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FirebaseStorageClass extends FirebaseBase {
    FirebaseStorage firebaseStorage;

    public void uploadImage(String ref, String imageName, byte[] file, OnCompleteListener taskSnapshotOnCompleteListener) {
        StorageReference storageReference = getFirebaseStorage().getReference(ref).child(imageName);
        storageReference.putBytes(file).addOnCompleteListener(taskSnapshotOnCompleteListener);

    }


    FirebaseStorage getFirebaseStorage() {
        if (firebaseStorage == null)
            firebaseStorage = FirebaseStorage.getInstance();
        return firebaseStorage;
    }
}
