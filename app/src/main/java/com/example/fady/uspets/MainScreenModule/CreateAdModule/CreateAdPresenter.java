package com.example.fady.uspets.MainScreenModule.CreateAdModule;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fady.uspets.FirebaseDatabase.FirebaseStorageClass;
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementModel;

import com.example.fady.uspets.FirebaseDatabase.FirebaseAdvertismentClass;
import com.example.fady.uspets.R;
import com.example.fady.uspets.USPetsMain.PetUiManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.example.fady.uspets.FirebaseDatabase.FirebaseConstant.PET_IMAGES_FOLDER;


public class CreateAdPresenter implements ICreateAd.IPresenter {
    ICreateAd.IView iView;
    private static final String TAG = "CreateAdPresenterLog";
    ArrayList<String> petImagesArrayList = new ArrayList<>();
    List<Observable<Boolean>> observableList = new ArrayList<>();

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
    public void onShareAdClicked(AdvertisementModel advertisementModel) {
        if (!checkAdvertisementData(advertisementModel))
            return;
        iView.showProgressView();

        String documentID = CreateAdConstant.generateRandomID();
        advertisementModel.setAdId(documentID);
        advertisementModel.setOwnerUid(PetUiManager.getInstance().getCurrentUser().getoUid());

        for (int i = 0; i < advertisementModel.getPetImageArrayList().size(); i++) {
            sendMultiplePhotos(advertisementModel.getPetImageArrayList().get(i), getByteArray(advertisementModel.getPetImageArrayList().get(i)), advertisementModel);
        }


        Observable<Boolean> booleanObservable = Observable.zip(observableList, new Function<Object[], Boolean>() {
            @Override
            public Boolean apply(Object[] objects) throws Throwable {
                return true;
            }
        });

        booleanObservable.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
            Log.e("Every", "thing okay");
            advertisementModel.setPetImageArrayList(petImagesArrayList);
            uploadPetAd(advertisementModel);
        });
    }

    private byte[] getByteArray(String uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(uri));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
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



    private boolean checkAdvertisementData(AdvertisementModel advertisementModel) {
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
        if (advertisementModel.getPetImageArrayList().size() == 0) {
            iView.showPicErrorMessage(context.getString(R.string.imgError));
            isValid = false;
        }

        return isValid;

    }


    private void sendMultiplePhotos(String imageName, byte[] byteArray, AdvertisementModel advertisementModel) {
        Observable<Boolean> observable = Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@io.reactivex.rxjava3.annotations.NonNull ObservableEmitter<Boolean> emitter) throws Throwable {
                firebaseStorageClass.uploadImage(PET_IMAGES_FOLDER, imageName, byteArray, new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        petImagesArrayList.add(uri.toString());
                        emitter.onNext(true);
                        emitter.onComplete();
                    }
                }, new FirebaseStorageClass.onError() {
                    @Override
                    public void sendError(Exception message) {
                        iView.dismissProgressView();
                        emitter.onError(message);
                    }
                });
            }
        });
        observableList.add(observable);
        Log.e("KOKO", "Observable Added");

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
