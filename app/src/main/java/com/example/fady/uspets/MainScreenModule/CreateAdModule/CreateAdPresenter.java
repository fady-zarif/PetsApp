package com.example.fady.uspets.MainScreenModule.CreateAdModule;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fady.uspets.FirebaseDatabase.FirebaseStorageClass;
import com.example.fady.uspets.FirebaseDatabase.FirebaseUserClass;
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementModel;

import com.example.fady.uspets.FirebaseDatabase.FirebaseAdvertismentClass;
import com.example.fady.uspets.PetApp;
import com.example.fady.uspets.R;
import com.example.fady.uspets.USPetsMain.PetUiManager;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.inject.Inject;

import static com.example.fady.uspets.MainScreenModule.CreateAdModule.CreateAdConstant.PET_IMAGES_FOLDER;

public class CreateAdPresenter implements ICreateAd.IPresenter {
    ICreateAd.IView iView;
    private static final String TAG = "CreateAdPresenterLog";

    @Inject
    FirebaseAdvertismentClass firebaseAdvertismentClass;

    @Inject
    FirebaseStorageClass firebaseStorageClass;

    @Inject
    Context context;

    @Inject
    public CreateAdPresenter(Fragment iView) {
        this.iView = (ICreateAd.IView) iView;

    }

    @Override
    public void onShareAdClicked(AdvertisementModel advertisementModel, Bitmap bitmap) {
        if (!checkAdvertisementData(advertisementModel, bitmap))
            return;
        iView.showProgressView();
        String petPicName = randomizeName(advertisementModel.getPetImage());
        String documentID = CreateAdConstant.generateRandomID();
        advertisementModel.setAdId(documentID);
        advertisementModel.setOwnerUid(PetUiManager.getInstance().getCurrentUser().getoUid());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();


        uploadPetImage(petPicName, byteArray, advertisementModel);


    }

    private void uploadPetAd(AdvertisementModel advertisementModel) {
        firebaseAdvertismentClass.pushAdvertisement(advertisementModel.getAdId(), advertisementModel, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    iView.onShareAdSuccess();
                } else {
                    iView.onShareAdFailed(task.getException().getLocalizedMessage());
                }
            }

        });
    }

    private void uploadPetImage(String imageName, byte[] byteArray, AdvertisementModel advertisementModel) {
        firebaseStorageClass.uploadImage(PET_IMAGES_FOLDER, imageName, byteArray, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task1) {
                if (task1.isSuccessful()) {
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference(PET_IMAGES_FOLDER).child(imageName);
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            advertisementModel.setPetImage(uri.toString());
                            uploadPetAd(advertisementModel);
                        }
                    });


                } else
                    iView.dismissProgressView();
            }
        });
    }

    private String randomizeName(String s) {
        Random random = new Random();
        int x = random.nextInt(99999999);
        return s + x;
    }

    private boolean checkAdvertisementData(AdvertisementModel advertisementModel, Bitmap bitmap) {
        boolean isValid = true;

        if (advertisementModel.getName().isEmpty()) {
            iView.showPetNameErrorMessage(context.getString(R.string.tvNameError));
            isValid = false;
        }
        if (advertisementModel.getAge().isEmpty()) {
            iView.showPetAgeErrorMessage(context.getString(R.string.tvAgeError));
            isValid = false;
        }
        if (advertisementModel.getDescription().isEmpty()) {
            iView.showPetDescErrorMessage(context.getString(R.string.tvDescError));
            isValid = false;
        }
        if (advertisementModel.getPrice().isEmpty()) {
            iView.showPetPriceErrorMessage(context.getString(R.string.tvPriceError));
            isValid = false;
        }
        if (bitmap == null) {
            iView.showPicErrorMessage(context.getString(R.string.imgError));
            isValid = false;
        }

        return isValid;

    }

    @Override
    public boolean isStoragePermissionGranted(Activity context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            ) {
                return true;
            } else {
                iView.requestStoragePermission();
                return false;
            }
        } else {
            return true;
        }
    }
}
