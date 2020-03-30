package com.example.fady.uspets.PersonalAdModule;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.fady.uspets.FirebaseDatabase.FirebaseAdvertismentClass;
import com.example.fady.uspets.FirebaseDatabase.FirebaseStorageClass;
import com.example.fady.uspets.MainScreenModule.AdvertismentModule.AdvertisementModel;
import com.example.fady.uspets.MainScreenModule.CreateAdModule.CreateAdConstant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PersonalAdPresenter implements IPersonalAd.Ipresenter {
    IPersonalAd.Iview iview;
    AdvertisementModel advertisementModel;
    ArrayList<String> finalImagesArrayList;
    ArrayList<Observable<Boolean>> observables;
    private boolean isPriceChanged = false;
    private boolean isDescChanged = false;
    private boolean isImagesChanged = false;

    public FirebaseAdvertismentClass getFirebaseAdvertismentClass() {
        return firebaseAdvertismentClass;
    }

    @Inject
    FirebaseStorageClass firebaseStorageClass;
    @Inject
    Context context;
    @Inject
    FirebaseAdvertismentClass firebaseAdvertismentClass;

    @Inject
    public PersonalAdPresenter(Activity activity) {
        iview = (IPersonalAd.Iview) activity;

    }

    @Override
    public void onUpdateClick(AdvertisementModel modifiedAdvertismentModel) {
        if (checkPetDesc(modifiedAdvertismentModel.getDescription()) && checkPetPrice(modifiedAdvertismentModel.getPrice())
                && checkPetImages(modifiedAdvertismentModel.getPetImageArrayList())) {
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            if (isPriceChanged)
                stringObjectHashMap.put(PersonalAdConstant.PET_PRICE_CONSTANT, modifiedAdvertismentModel.getPrice());
            if (isDescChanged)
                stringObjectHashMap.put(PersonalAdConstant.PET_DESC_CONSTANT, modifiedAdvertismentModel.getDescription());
            if (isImagesChanged) {
                stringObjectHashMap.put(PersonalAdConstant.PET_IMAGES_CONSTANT, finalImagesArrayList);
                if (observables.size() > 0) uploadImagesShareAdvertisment(stringObjectHashMap);
                else updateAdvertisment(stringObjectHashMap);
            } else
                updateAdvertisment(stringObjectHashMap);
        }
    }

    @Override
    public void setAdvetismentModel(AdvertisementModel advetismentModel) {
        this.advertisementModel = advetismentModel;
    }

    // TODO: 3/22/20 move all string to string file
    private boolean checkPetDesc(String desc) {
        if (desc == null || desc.length() < 5) {
            iview.onDescError("Descrition Error", advertisementModel.getDescription());
            return false;
        } else {
            if (!desc.equals(advertisementModel.getDescription())) {
                advertisementModel.setDescription(desc);
                isDescChanged = true;
            }
            return true;
        }
    }

    private boolean checkPetPrice(String price) {
        if (price == null || Integer.parseInt(price) < 10) {
            iview.onPriceError("Price Error", advertisementModel.getPrice());
            return false;
        } else {
            if (!price.equals(advertisementModel.getPrice())) {
                advertisementModel.setDescription(price);
                isPriceChanged = true;
            }
            return true;
        }
    }

    private boolean checkPetImages(ArrayList<String> modifiedImagesArrayList) {
        if (modifiedImagesArrayList == null || modifiedImagesArrayList.size() == 0) {
            iview.onImageError("Images Error", advertisementModel.getPetImageArrayList());
            return false;
        } else {
            finalImagesArrayList = new ArrayList<>();
            observables = new ArrayList<>();
            for (int i = 0; i < modifiedImagesArrayList.size(); i++) {
                if (modifiedImagesArrayList.get(i).substring(0, 3).equals("htt")) {
                    finalImagesArrayList.add(modifiedImagesArrayList.get(i));
                } else {
                    isImagesChanged = true;

                    String currentUri = modifiedImagesArrayList.get(i);
                    sendPic(currentUri, getByteArray(currentUri));
                }
            }
            if (!isImagesChanged && modifiedImagesArrayList.size() < advertisementModel.getPetImageArrayList().size())
                isImagesChanged = true;

            return true;
        }

    }

    private void uploadImagesShareAdvertisment(HashMap<String, Object> hashMap) {
        Observable<Boolean> observable = Observable.zip(observables, new Function<Object[], Boolean>() {
            @Override
            public Boolean apply(Object[] objects) throws Throwable {
                return true;
            }
        });

        observable.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(q -> {
            Log.e("Fadyaa", "Success");
            updateAdvertisment(hashMap);
        });
    }

    private void updateAdvertisment(HashMap<String, Object> hashMap) {
        firebaseAdvertismentClass.updateAdvertisment(advertisementModel.getAdId(), hashMap, new OnCompleteListener() {
            @Override
            public void onComplete(@androidx.annotation.NonNull Task task) {
                if (task.isSuccessful())
                    iview.onUpdateSuccess();
            }
        });
    }


    private void sendPic(String picName, byte[] byteArray) {
        Observable<Boolean> observable = Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> emitter) throws Throwable {
                firebaseStorageClass.uploadImage(CreateAdConstant.PET_IMAGES_FOLDER, picName, byteArray, new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        finalImagesArrayList.add(uri.toString());
                        Log.e("Fadyaa", uri.toString());
                        emitter.onNext(true);
                        emitter.onComplete();
                    }
                }, new FirebaseStorageClass.onError() {
                    @Override
                    public void sendError(Exception message) {
                        Log.e("Fadyaa", message.getLocalizedMessage());
                        emitter.onError(message);

                        iview.onImageError("Connection Error Try Again Later", advertisementModel.getPetImageArrayList());
                    }
                });
            }
        });
        observables.add(observable);

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
}
