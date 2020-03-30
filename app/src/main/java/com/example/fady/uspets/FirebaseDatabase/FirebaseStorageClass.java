package com.example.fady.uspets.FirebaseDatabase;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Random;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FirebaseStorageClass extends FirebaseBase {
    FirebaseStorage firebaseStorage;

    public void uploadImage(String ref, String imageName, byte[] file, OnSuccessListener<Uri> uriOnSuccessListener, onError onError) {
        imageName = randomizeName(imageName);
        StorageReference storageReference = getFirebaseStorage().getReference(ref).child(imageName);
        String finalImageName = imageName;
        storageReference.putBytes(file).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference(ref).child(finalImageName);
                    storageReference.getDownloadUrl().addOnSuccessListener(uriOnSuccessListener);
                } else {
                    onError.sendError(task.getException());
                }
            }
        });

    }

    private String randomizeName(String s) {
        Random random = new Random();
        int x = random.nextInt(99999999);
        return s + x;
    }

    public interface onError {
        void sendError(Exception message);
    }

    FirebaseStorage getFirebaseStorage() {
        if (firebaseStorage == null)
            firebaseStorage = FirebaseStorage.getInstance();
        return firebaseStorage;
    }
}
